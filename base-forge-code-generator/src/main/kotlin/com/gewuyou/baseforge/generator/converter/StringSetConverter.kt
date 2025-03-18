package com.gewuyou.baseforge.generator.converter

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.springframework.util.StringUtils


/**
 *字符串组转换器
 *
 * @since 2025-03-01 11:51:13
 * @author gewuyou
 */
@Converter
class StringSetConverter(
    private val objectMapper: ObjectMapper = ObjectMapper()
) :AttributeConverter<Set<String>, String> {
    override fun convertToDatabaseColumn(set: Set<String>?): String {
        set?.let { return objectMapper.writeValueAsString(it) }?: return ""
    }

    override fun convertToEntityAttribute(setStr: String?): Set<String> {
        return if(StringUtils.hasText(setStr)){
            objectMapper.readValue(setStr, object : TypeReference<Set<String>>() {})
        }else{
            emptySet()
        }
    }
}