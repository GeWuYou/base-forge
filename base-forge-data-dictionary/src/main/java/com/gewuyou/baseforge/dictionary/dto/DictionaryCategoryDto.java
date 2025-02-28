package com.gewuyou.baseforge.dictionary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author gewuyou DTO for {@link com.gewuyou.baseforge.dictionary.model.DictionaryCategory}
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryCategoryDto implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否启用
     */
    private Boolean isEnabled;
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