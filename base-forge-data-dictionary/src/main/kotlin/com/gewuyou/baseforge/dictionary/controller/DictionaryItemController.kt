package com.gewuyou.baseforge.dictionary.controller


import com.gewuyou.baseforge.autoconfigure.async.launcher.CoroutineLauncher
import com.gewuyou.baseforge.core.extension.log
import com.gewuyou.baseforge.dictionary.constants.CacheConstant
import com.gewuyou.baseforge.dictionary.dto.DictionaryItemDto
import com.gewuyou.baseforge.dictionary.model.DictionaryItem
import com.gewuyou.baseforge.dictionary.query.DictionaryItemQuery
import com.gewuyou.baseforge.dictionary.request.DictionaryItemSaveReq
import com.gewuyou.baseforge.dictionary.service.DictionaryCategoryService
import com.gewuyou.baseforge.dictionary.service.DictionaryItemService
import com.gewuyou.baseforge.entities.web.annotation.MethodRecording
import com.gewuyou.baseforge.entities.web.entity.DeleteByIdsReq
import com.gewuyou.baseforge.entities.web.entity.PageQuery
import com.gewuyou.baseforge.entities.web.entity.PageResult
import com.gewuyou.baseforge.entities.web.entity.Result
import com.gewuyou.baseforge.entities.web.validation.AddValidationGroup
import com.gewuyou.baseforge.entities.web.validation.UpdateValidationGroup
import jakarta.validation.Valid
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.MessageSource
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 *
 * 字典项表 前端控制器
 *
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@RestController
@RequestMapping("/dictionary/item")
class DictionaryItemController(
    private val dictionaryItemService: DictionaryItemService,
    private val dictionaryCategoryService: DictionaryCategoryService,
    private val i18nMessageSource: MessageSource,
    @Qualifier("redisCacheManager") private val cacheManager: CacheManager,
    @Qualifier("defaultCoroutineDispatcher")
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    @Qualifier("ioCoroutineDispatcher")
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    /**
     * 获取字典项列表
     * @param query 查询条件
     * @return 字典项列表
     */
    @GetMapping("/list")
    @MethodRecording
    @Cacheable(value = [CacheConstant.DICTIONARY_ITEM], key = "#query.hashCode()")
    fun getDictionaryItemList(@Valid @RequestBody query: PageQuery<DictionaryItemQuery>): Result<PageResult<DictionaryItemDto>> {
        return Result.success(dictionaryItemService.getDictionaryItemList(query), i18nMessageSource)
    }

    /**
     * 新增字典项
     * @param req 新增字典项请求
     * @return 新增字典项结果
     */
    @PostMapping
    @MethodRecording
    // 新增时清空字典项缓存
    @CacheEvict(value = [CacheConstant.DICTIONARY_ITEM], allEntries = true)
    fun addDictionaryItem(@Validated(AddValidationGroup::class) @Valid @RequestBody req: DictionaryItemSaveReq): Result<String> {
        dictionaryItemService.saveDictionaryItem(req)
        return Result.success(i18nMessageSource)
    }

    /**
     * 修改字典项
     * @param req 修改字典项请求
     * @return 修改字典项结果
     */
    @PutMapping
    @MethodRecording
    fun updateDictionaryItem(@Validated(UpdateValidationGroup::class) @Valid @RequestBody req: DictionaryItemSaveReq): Result<String> {
        CoroutineLauncher.launchAsync(
            block = {
                // 使用 withContext 执行非挂起代码
                withContext(ioDispatcher) {
                    // 第一个任务：保存数据字典项
                    dictionaryItemService.saveDictionaryItem(req)
                }
                withContext(defaultDispatcher) {
                    // 第二个任务：获取分类名称并更新缓存
                    val categoryName = dictionaryCategoryService.getById(req.categoryId).name
                    cacheManager.getCache(CacheConstant.DICTIONARY_ITEM)?.evict("${req.code}_${categoryName}")
                    cacheManager.getCache(CacheConstant.DICTIONARY_ITEM)?.evict("${req.value}_${categoryName}")
                    cacheManager.getCache(CacheConstant.DICTIONARY_ITEM)?.evict("${req.name}_${req.name}")
                }
            },
            onError = { e ->
                log.error("任务执行过程中出现异常", e)
                throw e
            }
        )
        return Result.success(i18nMessageSource)
    }


    /**
     * 物理删除字典项
     * @param req 物理删除请求
     * @return 物理删除结果
     */
    @DeleteMapping("/physical")
    @MethodRecording
    // 只有物理删除才清空缓存
    @CacheEvict(value = [CacheConstant.DICTIONARY_ITEM], allEntries = true)
    fun physicalDeleteDictionaryItem(@Valid @RequestBody req: DeleteByIdsReq): Result<String> {
        dictionaryItemService.physicalDeleteDictionaryItem(req)
        return Result.success(i18nMessageSource)
    }

    /**
     * 逻辑删除字典项
     * @param req 逻辑删除请求
     * @return 逻辑删除结果
     */
    @DeleteMapping("/logical")
    @MethodRecording
    fun logicalDeleteDictionaryItem(@Valid @RequestBody req: DeleteByIdsReq): Result<String> {
        dictionaryItemService.logicalDeleteDictionaryItem(req)
        return Result.success(i18nMessageSource)
    }

    /**
     * 根据字典项代码与类别名获取字典项
     * @param code 字典代码
     * @param categoryName 字典项类别
     * @return 字典项
     */
    @GetMapping("/code")
    @MethodRecording
    @Cacheable(value = [CacheConstant.DICTIONARY_ITEM], key = "#code + '_' + #categoryName")
    fun getDictionaryItemById(@RequestParam code: String, @RequestParam categoryName: String): DictionaryItem? {
        return dictionaryItemService.getDictionaryItemByCodeAndCategoryName(code, categoryName)
    }

    /**
     * 根据字典项值与类别名获取字典项
     * @param value 字典项值
     * @param categoryName 字典项类别
     * @return 字典项
     */
    @GetMapping("/value")
    @MethodRecording
    @Cacheable(value = [CacheConstant.DICTIONARY_ITEM], key = "#value + '_' + #categoryName")
    fun getDictionaryItemByCode(@RequestParam value: String, @RequestParam categoryName: String): DictionaryItem? {
        return dictionaryItemService.getDictionaryItemByValueAndCategoryName(value, categoryName)
    }

    /**
     * 根据字典项代码与名称获取字典项
     * @param code 字典项代码
     * @param name 字典项名称
     * @return 字典项
     */
    @GetMapping
    @MethodRecording
    @Cacheable(value = [CacheConstant.DICTIONARY_ITEM], key = "#code + '_' + #name")
    fun getDictionaryItemByCodeAndName(@RequestParam code: String, @RequestParam name: String): DictionaryItem? {
        return dictionaryItemService.getDictionaryItemByCodeAndName(code, name)
    }
}
