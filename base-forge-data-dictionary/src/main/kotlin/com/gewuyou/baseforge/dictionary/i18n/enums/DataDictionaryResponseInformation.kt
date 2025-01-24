package com.gewuyou.baseforge.dictionary.i18n.enums

import com.gewuyou.baseforge.autoconfigure.i18n.entity.ResponseInformation
import org.springframework.http.HttpStatus


/**
 * 数据字典 响应信息
 *
 * @author gewuyou
 * @since 2024-11-26 17:24:47
 */
enum class DataDictionaryResponseInformation(private val code: Int, private val i18nMessageCode: String) :
    ResponseInformation {
    /**
     * 数据字典项已存在
     */
    DICTIONARY_ENTRY_WITH_THE_SAME_CODE_EXISTS(HttpStatus.BAD_REQUEST.value(), "data.dictionaryItem.code.exist"),
    ;

    override fun getResponseCode(): Int {
        return this.code
    }

    override fun getResponseI8nMessageCode(): String {
        return this.i18nMessageCode
    }
}
