package com.gewuyou.baseforge.dictionary.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.autoconfigure.util.BeanCopyUtil
import com.gewuyou.baseforge.core.exception.InternalException
import com.gewuyou.baseforge.dictionary.dto.DictionaryCategoryDto
import com.gewuyou.baseforge.dictionary.mapper.DictionaryCategoryMapper
import com.gewuyou.baseforge.dictionary.model.DictionaryCategory
import com.gewuyou.baseforge.dictionary.query.DictionaryCategoryQuery
import com.gewuyou.baseforge.dictionary.repository.DictionaryCategoryRepository
import com.gewuyou.baseforge.dictionary.request.DictionaryCategoryDeleteReq
import com.gewuyou.baseforge.dictionary.request.DictionaryCategorySaveReq
import com.gewuyou.baseforge.dictionary.service.DictionaryCategoryService
import com.gewuyou.baseforge.entities.web.entity.PageQuery
import com.gewuyou.baseforge.entities.web.entity.PageResult
import com.gewuyou.baseforge.entities.web.extension.getPredicates
import com.gewuyou.baseforge.entities.web.extension.toPageable
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import java.util.*

/**
 *
 * 字典类别 服务实现类
 *
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Service
class DictionaryCategoryServiceImpl(
    private val dictionaryCategoryRepository: DictionaryCategoryRepository
) : ServiceImpl<DictionaryCategoryMapper, DictionaryCategory>(),
    DictionaryCategoryService {
    /**
     * 获取字典类别列表
     * @param query 查询条件
     * @return 字典类别列表
     */
    override fun getCategoryList(query: PageQuery<DictionaryCategoryQuery>): PageResult<DictionaryCategoryDto> {
        try {
            log.debug("查询字典类别列表")
            val content = dictionaryCategoryRepository.findAll(
                { root, _, criteriaBuilder ->
                    log.debug("query: $query")
                    val predicates = query.getPredicates(criteriaBuilder, root)
                    if (StringUtils.isNotBlank(query.filter?.name)) {
                        predicates.add(
                            criteriaBuilder.like(
                                root[DictionaryCategory.FIELD_NAME],
                                "%${query.filter.name}%"
                            )
                        )
                    }
                    log.debug("predicates: $predicates")
                    criteriaBuilder.and(*predicates.toTypedArray())
                },
                query.toPageable()
            )
            return PageResult.of(
                query.page, query.pageSize, content.totalElements, content
                    .map { dictionaryCategory ->
                        BeanCopyUtil.shallowCopyObject(dictionaryCategory, DictionaryCategoryDto::class.java).get()
                    }.toList()
            )
        } catch (e: Exception) {
            log.error("类别列表查询失败!", e)
            throw InternalException("类别列表查询失败!")
        }
    }

    /**
     * 保存字典类别
     * @param req 保存请求
     */
    override fun saveDictionaryCategory(req: DictionaryCategorySaveReq) {
        // 检查是新增还是修改
        dictionaryCategoryRepository.save(
            req.id?.let {
                // 修改逻辑
                dictionaryCategoryRepository.findById(it).orElseThrow {
                    InternalException("字典类别不存在!")
                }.apply {
                    BeanCopyUtil.copyPropertiesWithValidation(req, this, Objects::nonNull)
                }
            } ?: DictionaryCategory().also {
                it.isDeleted = false
                // 新增逻辑
                BeanCopyUtil.copyPropertiesWithValidation(req, it, Objects::nonNull)
            }
        )
    }

    /**
     * 物理删除字典类别
     * @param req 删除请求
     */
    override fun physicalDeleteDictionaryCategory(req: DictionaryCategoryDeleteReq) {
        dictionaryCategoryRepository.deleteAllById(req.ids)
    }

    /**
     * 逻辑删除字典类别
     * @param req 删除请求
     */
    override fun logicalDeleteDictionaryCategory(req: DictionaryCategoryDeleteReq) {
        dictionaryCategoryRepository
            .saveAll(
                dictionaryCategoryRepository
                .findAllById(req.ids).map {
                    it.apply {
                        isDeleted = true
                    }
                }.toList()
            )
    }
}
