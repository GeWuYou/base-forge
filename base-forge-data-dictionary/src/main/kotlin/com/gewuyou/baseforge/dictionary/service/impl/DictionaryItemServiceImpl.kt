package com.gewuyou.baseforge.dictionary.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.autoconfigure.async.service.AsyncService
import com.gewuyou.baseforge.autoconfigure.util.BeanCopyUtil
import com.gewuyou.baseforge.core.exception.InternalException
import com.gewuyou.baseforge.dictionary.dto.DictionaryItemDto
import com.gewuyou.baseforge.dictionary.enums.OperationType
import com.gewuyou.baseforge.dictionary.exception.DataDictionaryException
import com.gewuyou.baseforge.dictionary.i18n.enums.DataDictionaryResponseInformation
import com.gewuyou.baseforge.dictionary.mapper.DictionaryItemMapper
import com.gewuyou.baseforge.dictionary.model.DictionaryItem
import com.gewuyou.baseforge.dictionary.query.DictionaryItemQuery
import com.gewuyou.baseforge.dictionary.repository.DictionaryItemRepository
import com.gewuyou.baseforge.dictionary.request.DictionaryItemSaveReq
import com.gewuyou.baseforge.dictionary.service.DictionaryItemHistoryService
import com.gewuyou.baseforge.dictionary.service.DictionaryItemService
import com.gewuyou.baseforge.entities.web.entity.DeleteByIdsReq
import com.gewuyou.baseforge.entities.web.entity.PageQuery
import com.gewuyou.baseforge.entities.web.entity.PageResult
import com.gewuyou.baseforge.entities.web.extension.getPredicates
import com.gewuyou.baseforge.entities.web.extension.toPageable
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

/**
 * <p>
 * 字典项表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Service
class DictionaryItemServiceImpl(
    private val dictionaryItemRepository: DictionaryItemRepository,
    private val dictionaryItemHistoryService: DictionaryItemHistoryService,
    private val asyncService: AsyncService
) : ServiceImpl<DictionaryItemMapper, DictionaryItem>(), DictionaryItemService {
    /**
     * 根据查询条件获取字典项列表
     * @param query 查询条件
     * @return 字典项列表
     */
    override fun getDictionaryItemList(query: PageQuery<DictionaryItemQuery>): PageResult<DictionaryItemDto> {
        log.debug("query: $query")
        try {
            val content = dictionaryItemRepository.findAll(
                { root, _, criteriaBuilder ->
                    getDictionaryItemPredicate(query, criteriaBuilder, root)
                },
                query.toPageable()
            )
            return PageResult.of(query.page, query.pageSize, content.totalElements, content.map { dictionaryItem ->
                BeanCopyUtil.shallowCopyObject(dictionaryItem, DictionaryItemDto::class.java).get()
            }.toList())
        } catch (e: Exception) {
            log.error("获取字典项列表失败!", e)
            throw InternalException("获取字典项列表失败!")
        }
    }

    /**
     * 根据查询条件获取字典项列表的查询条件
     * @param query 查询条件
     * @param criteriaBuilder 条件构造器
     * @param root 根
     * @return 查询条件
     */
    private fun getDictionaryItemPredicate(
        query: PageQuery<DictionaryItemQuery>,
        criteriaBuilder: CriteriaBuilder,
        root: Root<DictionaryItem>
    ): Predicate? {
        val predicates = query.getPredicates(criteriaBuilder, root)
        val filter = query.filter
        if (Objects.nonNull(filter?.categoryId)) {
            predicates.add(
                criteriaBuilder.equal(
                    root.get<Long>(DictionaryItem.FIELD_CATEGORY_ID),
                    filter.categoryId
                )
            )
        }
        if (StringUtils.isNotBlank(filter?.code)) {
            predicates.add(criteriaBuilder.equal(root.get<String>(DictionaryItem.FIELD_CODE), filter.code))
        }
        if (StringUtils.isNotBlank(filter?.name)) {
            predicates.add(criteriaBuilder.like(root[DictionaryItem.FIELD_NAME], "%${filter.name}%"))
        }
        if (StringUtils.isNotBlank(filter?.value)) {
            predicates.add(criteriaBuilder.like(root[DictionaryItem.FIELD_VALUE], "%${filter.value}%"))
        }
        if (StringUtils.isNotBlank(filter?.environment)) {
            predicates.add(
                criteriaBuilder.equal(
                    root.get<String>(DictionaryItem.FIELD_ENVIRONMENT),
                    filter.environment
                )
            )
        }
        if (StringUtils.isNotBlank(filter?.type)) {
            predicates.add(criteriaBuilder.equal(root.get<String>(DictionaryItem.FIELD_TYPE), filter.type))
        }
        return criteriaBuilder.and(*predicates.toTypedArray())
    }

    /**
     * 保存字典项
     * @param req 保存字典项请求
     */
    @Transactional(rollbackFor = [Exception::class])
    override fun saveDictionaryItem(req: DictionaryItemSaveReq) {
        try {
            // 检查是否存在同名的字典项
            val existItem = dictionaryItemRepository.findByCodeAndCategoryId(req.code, req.categoryId)
            val dictionaryItem = dictionaryItemRepository.save(
                req.id?.let {
                    // 修改
                    updateDictionaryItem(it, existItem, req)
                } ?:
                // 新增
                addDictionaryItem(req, existItem)
            )
            // 新增字典项进历史表
            dictionaryItemHistoryService.saveDictionaryItemHistory(dictionaryItem, OperationType.INSERT)
        } catch (e: Exception) {
            log.error("保存字典项失败!", e)
            throw InternalException("保存字典项失败!")
        }
    }

    /**
     * 更新字典项
     * @param id 字典项id
     * @param existItem 存在的字典项
     * @param req 保存字典项请求
     * @return 更新后的字典项
     */
    private fun updateDictionaryItem(id: Long, existItem: DictionaryItem?, req: DictionaryItemSaveReq): DictionaryItem {
        // 检查id是否相同，不相同则表示该分类下存在同名的字典项
        if (id != existItem?.id) {
            throw DataDictionaryException(DataDictionaryResponseInformation.DICTIONARY_ENTRY_WITH_THE_SAME_CODE_EXISTS)
        }
        val dictionaryItem = dictionaryItemRepository.findById(id).orElseThrow {
            InternalException("字典项不存在!")
        }.apply {
            BeanCopyUtil.copyPropertiesWithValidation(req, this, Objects::nonNull)
            // 保存修改进历史表
            dictionaryItemHistoryService.saveDictionaryItemHistory(this, OperationType.UPDATE)
        }
        // 修改逻辑
        return dictionaryItem
    }

    /**
     * 新增字典项
     * @param req 保存字典项请求
     * @param existItem 存在的字典项
     * @return 新增的字典项
     */
    private fun addDictionaryItem(req: DictionaryItemSaveReq, existItem: DictionaryItem?): DictionaryItem {
        val dictionaryItem = DictionaryItem().also {
            // 检查是否存在同名的字典项
            if (Objects.nonNull(existItem)) {
                throw DataDictionaryException(DataDictionaryResponseInformation.DICTIONARY_ENTRY_WITH_THE_SAME_CODE_EXISTS)
            }
            BeanCopyUtil.copyPropertiesWithValidation(req, it, Objects::nonNull)
            it.createdAt = LocalDateTime.now()
            it.isDeleted = false
        }
        return dictionaryItem
    }

    /**
     * 物理删除字典项
     * @param req 删除请求
     */
    override fun physicalDeleteDictionaryItem(req: DeleteByIdsReq) {
        try {
            val dictionaryItems = dictionaryItemRepository.findAllById(req.ids)
            asyncService.batchRunAsync(
                mutableListOf(
                    Runnable {
                        // 保存上一次的字典项进入历史表
                        dictionaryItems.forEach {
                            // 保存物理删除进历史表
                            dictionaryItemHistoryService.saveDictionaryItemHistory(it, OperationType.PHYSICAL_DELETE)
                        }
                    },
                    Runnable {
                        // 物理删除字典项
                        dictionaryItemRepository.deleteAllById(req.ids)
                    }
                )
            )
        } catch (e: Exception) {
            log.error("物理删除字典项失败!", e)
            throw InternalException("物理删除字典项失败!")
        }
    }

    /**
     * 逻辑删除字典项
     * @param req 删除请求
     */
    override fun logicalDeleteDictionaryItem(req: DeleteByIdsReq) {
        try {
            asyncService.supplyAsync {
                dictionaryItemRepository.findAllById(req.ids).map {
                    it.apply {
                        isDeleted = true
                    }
                }.toList()
            }.thenCompose {
                asyncService.batchRunAsync(
                    mutableListOf(
                        Runnable {
                            dictionaryItemRepository.saveAll(it)
                        },
                        Runnable {
                            it.forEach {
                                dictionaryItemHistoryService.saveDictionaryItemHistory(it, OperationType.LOGICAL_DELETE)
                            }
                        }
                    )
                )
            }
        } catch (e: Exception) {
            log.error("逻辑删除字典项失败!", e)
            throw InternalException("逻辑删除字典项失败!")
        }
    }

    /**
     * 根据code和category获取字典项
     * @param code 字典项code
     * @param categoryName 字典项分类
     * @return 字典项
     */
    override fun getDictionaryItemByCodeAndCategoryName(code: String, categoryName: String): DictionaryItem? {
        try {
            return baseMapper.getDictionaryItemByCodeAndCategoryName(code, categoryName)
        } catch (e: Exception) {
            log.error("根据code和category获取字典项失败!", e)
            return null
        }
    }

    /**
     * 根据value和category获取字典项
     * @param value 字典项value
     * @param categoryName 字典项分类名称
     * @return 字典项
     */
    override fun getDictionaryItemByValueAndCategoryName(value: String, categoryName: String): DictionaryItem? {
        try {
            return baseMapper.getDictionaryItemByValueAndCategoryName(value, categoryName)
        } catch (e: Exception) {
            log.error("根据value和category获取字典项失败!", e)
            return null
        }
    }

    /**
     * 根据code和name获取字典项
     * @param code 字典项code
     * @param name 字典项名称
     * @return 字典项
     */
    override fun getDictionaryItemByCodeAndName(code: String, name: String): DictionaryItem? {
        return dictionaryItemRepository.findByCodeAndName(code, name)
    }
}
