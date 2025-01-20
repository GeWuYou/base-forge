package com.gewuyou.baseforge.dictionary.controller


import com.gewuyou.baseforge.dictionary.dto.DictionaryCategoryDto
import com.gewuyou.baseforge.dictionary.query.DictionaryCategoryQuery
import com.gewuyou.baseforge.dictionary.request.DictionaryCategoryDeleteReq
import com.gewuyou.baseforge.dictionary.request.DictionaryCategorySaveReq
import com.gewuyou.baseforge.dictionary.service.DictionaryCategoryService
import com.gewuyou.baseforge.entities.web.entity.PageQuery
import com.gewuyou.baseforge.entities.web.entity.PageResult
import com.gewuyou.baseforge.entities.web.entity.Result
import com.gewuyou.baseforge.entities.web.validation.AddValidationGroup
import com.gewuyou.baseforge.entities.web.validation.UpdateValidationGroup
import jakarta.validation.Valid
import org.springframework.context.MessageSource
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 字典类别 前端控制器
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@RestController
@RequestMapping("/dictionary/category")
class DictionaryCategoryController(
    private val dictionaryCategoryService: DictionaryCategoryService,
    private val i18nMessageSource: MessageSource
) {
    /**
     * 获取字典类别列表
     * @param query 查询条件
     * @return 字典类别列表
     */
    @GetMapping("/list")
    fun getCategoryList(@Valid @RequestBody query: PageQuery<DictionaryCategoryQuery>): Result<PageResult<DictionaryCategoryDto>> {
        return Result.success(dictionaryCategoryService.getCategoryList(query), i18nMessageSource)
    }

    /**
     * 新增字典类别
     * @param req 新增字典类别请求
     * @return 新增结果
     */
    @PostMapping
    fun addDictionaryCategory(@Validated(AddValidationGroup::class) @Valid @RequestBody req: DictionaryCategorySaveReq): Result<String> {
        dictionaryCategoryService.saveDictionaryCategory(req)
        return Result.success(i18nMessageSource)
    }

    /**
     * 修改字典类别
     * @param req 新增字典类别请求
     * @return 新增结果
     */
    @PutMapping
    fun updateDictionaryCategory(@Validated(UpdateValidationGroup::class) @Valid @RequestBody req: DictionaryCategorySaveReq): Result<String> {
        dictionaryCategoryService.saveDictionaryCategory(req)
        return Result.success(i18nMessageSource)
    }

    /**
     * 物理删除字典类别
     * @param req 删除字典类别请求
     * @return 删除结果
     */
    @DeleteMapping("/physical")
    fun physicalDeleteDictionaryCategory(@Valid @RequestBody req: DictionaryCategoryDeleteReq): Result<String> {
        dictionaryCategoryService.physicalDeleteDictionaryCategory(req)
        return Result.success(i18nMessageSource)
    }

    /**
     * 逻辑删除字典类别
     * @param req 删除字典类别请求
     * @return 删除结果
     */
    @DeleteMapping("/logical")
    fun logicalDeleteDictionaryCategory(@Valid @RequestBody req: DictionaryCategoryDeleteReq): Result<String> {
        dictionaryCategoryService.logicalDeleteDictionaryCategory(req)
        return Result.success(i18nMessageSource)
    }
}
