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
import java.time.LocalDateTime;

/**
 * @author gewuyou DTO for {@link com.gewuyou.baseforge.dictionary.model.DictionaryItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryItemSaveReq implements Serializable {
    @Null(groups = AddValidationGroup.class, message = "data.dictionary.entity.id.must.null")
    @NotNull(groups = UpdateValidationGroup.class, message = "data.dictionary.entity.id.must.not.null")
    private Long id;
    @NotNull(message = "data.dictionaryItem.categoryId.must.not.null")
    private Long categoryId;
    @NotBlank(message = "data.dictionaryItem.code.must.not.blank")
    private String code;
    @NotBlank(message = "data.dictionaryItem.name.must.not.blank")
    private String name;
    @NotBlank(message = "data.dictionaryItem.value.must.not.blank")
    private String value;
    @NotBlank(message = "data.dictionaryItem.description.must.not.blank")
    private String description;
    @NotNull(message = "data.dictionaryItem.isEnabled.must.not.null")
    private Boolean isEnabled;
    private String environment;
    @NotBlank(message = "data.dictionaryItem.type.must.not.blank")
    private String type;
    private LocalDateTime effectiveDate;
    private LocalDateTime expirationDate;
}