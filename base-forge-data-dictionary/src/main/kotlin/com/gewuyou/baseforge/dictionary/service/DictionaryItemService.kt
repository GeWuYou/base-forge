package com.gewuyou.baseforge.dictionary.service

import com.baomidou.mybatisplus.extension.service.IService
import com.gewuyou.baseforge.dictionary.dto.DictionaryItemDto
import com.gewuyou.baseforge.dictionary.model.DictionaryItem
import com.gewuyou.baseforge.dictionary.query.DictionaryItemQuery
import com.gewuyou.baseforge.dictionary.request.DictionaryItemSaveReq
import com.gewuyou.baseforge.entities.web.entity.DeleteByIdsReq
import com.gewuyou.baseforge.entities.web.entity.PageQuery
import com.gewuyou.baseforge.entities.web.entity.PageResult

/**
 * <p>
 * 字典项表 服务类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
interface DictionaryItemService : IService<DictionaryItem> {
    /**
     * 根据查询条件获取字典项列表
     * @param query 查询条件
     * @return 字典项列表
     */
    fun getDictionaryItemList(query: PageQuery<DictionaryItemQuery>): PageResult<DictionaryItemDto>

    /**
     * 保存字典项
     * @param req 保存字典项请求
     */
    fun saveDictionaryItem(req: DictionaryItemSaveReq)

    /**
     * 物理删除字典项
     * @param req 删除请求
     */
    fun physicalDeleteDictionaryItem(req: DeleteByIdsReq)

    /**
     * 逻辑删除字典项
     * @param req 删除请求
     */
    fun logicalDeleteDictionaryItem(req: DeleteByIdsReq)

    /**
     * 根据code和category获取字典项
     * @param code 字典项code
     * @param categoryName 字典项分类名称
     * @return 字典项
     */
    fun getDictionaryItemByCodeAndCategoryName(code: String, categoryName: String): DictionaryItem?

    /**
     * 根据value和category获取字典项
     * @param value 字典项value
     * @param categoryName 字典项分类名称
     * @return 字典项
     */
    fun getDictionaryItemByValueAndCategoryName(value: String, categoryName: String): DictionaryItem?

    /**
     * 根据code和name获取字典项
     * @param code 字典项code
     * @param name 字典项名称
     * @return 字典项
     */
    fun getDictionaryItemByCodeAndName(code: String, name: String): DictionaryItem?


}
