package com.gewuyou.baseforge.dictionary.service

import com.baomidou.mybatisplus.extension.service.IService
import com.gewuyou.baseforge.dictionary.dto.DictionaryCategoryDto
import com.gewuyou.baseforge.dictionary.model.DictionaryCategory
import com.gewuyou.baseforge.dictionary.query.DictionaryCategoryQuery
import com.gewuyou.baseforge.dictionary.request.DictionaryCategoryDeleteReq
import com.gewuyou.baseforge.dictionary.request.DictionaryCategorySaveReq
import com.gewuyou.baseforge.entities.web.entity.PageQuery
import com.gewuyou.baseforge.entities.web.entity.PageResult

/**
 * <p>
 * 字典类别 服务类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
interface DictionaryCategoryService : IService<DictionaryCategory> {
    /**
     * 获取字典类别列表
     * @param query 查询条件
     * @return 字典类别列表
     */
    fun getCategoryList(query: PageQuery<DictionaryCategoryQuery>): PageResult<DictionaryCategoryDto>


    /**
     * 保存字典类别
     * @param req 保存请求
     */
    fun saveDictionaryCategory(req: DictionaryCategorySaveReq)

    /**
     * 物理删除字典类别
     * @param req 删除请求
     */
    fun physicalDeleteDictionaryCategory(req: DictionaryCategoryDeleteReq)

    /**
     * 逻辑删除字典类别
     * @param req 删除请求
     */
    fun logicalDeleteDictionaryCategory(req: DictionaryCategoryDeleteReq)

}
