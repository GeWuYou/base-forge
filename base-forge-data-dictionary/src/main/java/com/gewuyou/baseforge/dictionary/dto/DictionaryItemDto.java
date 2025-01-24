package com.gewuyou.baseforge.dictionary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author gewuyou DTO for {@link com.gewuyou.baseforge.dictionary.model.DictionaryItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryItemDto implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 类别id
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
     * 字典项值(可为配置、正则表达式，枚举值...)
     */
    private String value;
    /**
     * 字典类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否启用
     */
    private Boolean isEnabled;
    /**
     * 环境
     */
    private String environment;
    /**
     * 生效日期
     */
    private LocalDateTime effectiveDate;
    /**
     * 过期日期
     */
    private LocalDateTime expirationDate;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 修改人
     */
    private Long updatedBy;
}