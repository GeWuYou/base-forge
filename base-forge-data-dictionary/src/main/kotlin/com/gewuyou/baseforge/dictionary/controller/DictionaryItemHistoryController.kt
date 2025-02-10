package com.gewuyou.baseforge.dictionary.controller

import com.gewuyou.baseforge.entities.web.annotation.ApiVersion
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * <p>
 * 字典项历史表 前端控制器
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@RestController
@ApiVersion
@RequestMapping("/dictionary/item/history")
class DictionaryItemHistoryController