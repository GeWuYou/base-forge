package com.gewuyou.baseforge.dictionary.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 字典类别删除请求
 *
 * @author gewuyou
 * @since 2025-01-18 17:39:18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DictionaryCategoryDeleteReq(@NotNull(message = "data.dictionaryCategory.ids.notNull") List<Long> ids) {
}
