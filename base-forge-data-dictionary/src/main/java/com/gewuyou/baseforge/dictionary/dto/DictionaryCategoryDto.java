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
    private Long id;
    private String name;
    private String description;
    private Boolean isEnabled;
    private LocalDateTime createdAt;
}