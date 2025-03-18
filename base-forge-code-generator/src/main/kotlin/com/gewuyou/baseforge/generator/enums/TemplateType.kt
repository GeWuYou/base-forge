package com.gewuyou.baseforge.generator.enums

/**
 *模板类型
 *
 * @since 2025-02-26 22:41:13
 * @author gewuyou
 */
enum class TemplateType(val desc: String) {
    ENTITY("实体类模板"),
    SERVICE("服务类模板"),
    SERVICE_IMPL("服务实现类模板"),
    CONTROLLER("控制器类模板"),
    REPOSITORY("数据仓库模板"),
    MAPPER("Mybatis Mapper模板"),
    MAPPER_XML("Mybatis Mapper XML模板"),
    OTHER("其他模板")
}