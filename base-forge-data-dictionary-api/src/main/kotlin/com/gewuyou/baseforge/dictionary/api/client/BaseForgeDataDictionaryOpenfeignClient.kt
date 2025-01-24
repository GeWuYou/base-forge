package com.gewuyou.baseforge.dictionary.api.client

import com.gewuyou.baseforge.dictionary.model.DictionaryItem
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * 字典服务 openfeign 客户端
 *
 * @author gewuyou
 * @since 2025-01-24 09:59:16
 */
@FeignClient(name = "base-forge-data-dictionary")
interface BaseForgeDataDictionaryOpenfeignClient {
    /**
     * 根据字典项代码与类别名获取字典项
     * @param code 字典代码
     * @param categoryName 字典项类别
     * @return 字典项
     */
    @GetMapping("/code")
    fun getDictionaryItemById(@RequestParam code: String, @RequestParam categoryName: String): DictionaryItem?

    /**
     * 根据字典项值与类别名获取字典项
     * @param value 字典项值
     * @param categoryName 字典项类别
     * @return 字典项
     */
    @GetMapping("/value")
    fun getDictionaryItemByCode(@RequestParam value: String, @RequestParam categoryName: String): DictionaryItem?

    /**
     * 根据字典项代码与名称获取字典项
     * @param code 字典项代码
     * @param name 字典项名称
     * @return 字典项
     */
    @GetMapping
    fun getDictionaryItemByCodeAndName(@RequestParam code: String, @RequestParam name: String): DictionaryItem?
}
