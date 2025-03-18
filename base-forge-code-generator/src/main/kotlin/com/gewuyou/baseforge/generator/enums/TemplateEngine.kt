package com.gewuyou.baseforge.generator.enums

import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine

/**
 *模板引擎
 *
 * @since 2025-02-28 22:47:02
 * @author gewuyou
 */
enum class TemplateEngine(
    val value: String,
    val templateEngine: AbstractTemplateEngine
) {
    FREEMARKER("freemarker", FreemarkerTemplateEngine()),
}