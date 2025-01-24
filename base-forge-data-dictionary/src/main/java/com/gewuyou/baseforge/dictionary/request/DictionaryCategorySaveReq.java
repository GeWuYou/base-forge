package com.gewuyou.baseforge.dictionary.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gewuyou.baseforge.entities.web.validation.AddValidationGroup;
import com.gewuyou.baseforge.entities.web.validation.UpdateValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 类别保存请求
 *
 * @author gewuyou DTO for {@link com.gewuyou.baseforge.dictionary.model.DictionaryCategory}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryCategorySaveReq implements Serializable {
    /**
     * 主键
     */
    @Null(groups = AddValidationGroup.class, message = "data.dictionary.entity.id.must.null")
    @NotNull(groups = UpdateValidationGroup.class, message = "data.dictionary.entity.id.must.not.null")
    private Long id;
    /**
     * 类别名称
     */
    @NotBlank(message = "data.dictionaryCategory.name.notBlank")
    private String name;
    /**
     * 描述
     */
    @NotBlank(message = "data.dictionaryCategory.description.notBlank")
    private String description;
    /**
     * 是否启用
     */
    @NotNull(message = "data.dictionary.entity.isEnabled.notNull")
    private Boolean isEnabled;
}