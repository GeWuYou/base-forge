package com.gewuyou.baseforge.dictionary.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gewuyou DTO for {@link com.gewuyou.baseforge.dictionary.model.DictionaryItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryItemQuery implements Serializable {
    /**
     * 类别主键
     */
    private Long categoryId;
    /**
     * 字典代码
     */
    private String code;
    /**
     * 字典项名
     */
    private String name;
    /**
     * 字典项值(可为配置、正则表达式、枚举值...)
     */
    private String value;
    /**
     * 环境(dev/test/prod...)
     */
    private String environment;

    /**
     * 字典项类型(常量/枚举/正则/配置...)
     */
    private String type;
}