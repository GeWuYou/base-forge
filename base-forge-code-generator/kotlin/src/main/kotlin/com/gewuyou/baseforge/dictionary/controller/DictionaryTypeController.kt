package com.gewuyou.baseforge.dictionary.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.context.MessageSource
import com.gewuyou.baseforge.entities.web.annotation.ApiVersion

/**
 *
 * 字典类别表 前端控制器
 *
 * @author gewuyou
 * @since 2025-03-02
 */
@RestController
@ApiVersion
@RequestMapping("/dictionary/dictionaryType")
class DictionaryTypeController(
   private val i18nMessageSource: MessageSource
){

}