package com.gewuyou.baseforge.dictionary.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gewuyou DTO for {@link com.gewuyou.baseforge.dictionary.model.DictionaryCategory}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryCategoryQuery implements Serializable {
    /**
     * 类别名称
     */
    private String name;
}