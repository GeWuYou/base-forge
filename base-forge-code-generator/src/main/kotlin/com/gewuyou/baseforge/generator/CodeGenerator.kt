package com.gewuyou.baseforge.generator


import com.baomidou.mybatisplus.core.toolkit.StringPool
import com.baomidou.mybatisplus.generator.FastAutoGenerator
import com.baomidou.mybatisplus.generator.config.*
import com.baomidou.mybatisplus.generator.config.builder.CustomFile
import com.baomidou.mybatisplus.generator.config.po.TableField.MetaInfo
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import com.baomidou.mybatisplus.generator.function.ConverterFileName
import com.baomidou.mybatisplus.generator.type.TypeRegistry
import com.gewuyou.baseforge.generator.decorator.TemplateEngineDecorator
import org.apache.ibatis.annotations.Mapper
import org.springframework.util.StringUtils
import java.sql.Types

/**
 * 代码生成器
 *
 * @author gewuyou
 * @since 2024-04-13 下午7:48:59
 */
object CodeGenerator {
    class CustomConfig(
        var moduleName: String,
        var xmlOutputDir: String,
        var mapperOutputDir: String,
        var repositoryOutputDir: String,
        var serviceOutputDir: String,
        var serviceImplOutputDir: String,
        var controllerOutputDir: String,
        var tableNames: List<String>,
        var entityOutputDir: String = "base-forge-common\\src\\main\\java\\com\\gewuyou\\baseforge",
        val schema: String = "public",
        var controllerEnableFileOverride: Boolean = false,
        var serviceEnableFileOverride: Boolean = false,
        var entityEnableFileOverride: Boolean = true,
        var mapperEnableFileOverride: Boolean = false,
        var repositoryDisabled: Boolean = false,
        var controllerDisabled: Boolean = false,
        var serviceDisabled: Boolean = false,
        var entityDisabled: Boolean = false,
        var mapperDisabled: Boolean = false,
        var jdbcUrl: String = "jdbc:postgresql://172.24.165.5:5432/base_forge",
        var jdbcUsername: String = "postgres",
        var jdbcPassword: String = "root",
        var author: String = "gewuyou",
        var parentPackage: String = "com.gewuyou.baseforge",
        var tablePrefix: String = "bf_",
    )

    /**
     * 代码生成器
     *@param customConfig 自定义配置
     * @apiNote 查询数据库生成代码
     * @since 2024/4/13 下午9:37
     */
    private fun generation(
        customConfig: CustomConfig
    ) {
        FastAutoGenerator.create(customConfig.jdbcUrl, customConfig.jdbcUsername, customConfig.jdbcPassword)
            // 全局配置
            .globalConfig { builder: GlobalConfig.Builder ->
                getGlobalConfig(builder, customConfig)
            } // 数据库配置
            .dataSourceConfig { builder: DataSourceConfig.Builder ->
                getDataSourceConfig(builder)
            } // 包配置
            .packageConfig { builder: PackageConfig.Builder ->
                builder // 设置父包名
                    .parent(customConfig.parentPackage) // 设置父包模块名
                    .moduleName(customConfig.moduleName)
                    .entity("model") // 设置各个模块代码生成路径
                    .pathInfo(
                        mapOf(
                            OutputFile.entity to customConfig.entityOutputDir + "\\${customConfig.moduleName}\\model",
                            OutputFile.mapper to customConfig.mapperOutputDir + "\\${customConfig.moduleName}\\mapper",
                            OutputFile.service to customConfig.serviceOutputDir + "\\${customConfig.moduleName}\\service",
                            OutputFile.serviceImpl to customConfig.serviceImplOutputDir + "\\${customConfig.moduleName}\\service\\impl",
                            OutputFile.controller to customConfig.controllerOutputDir + "\\${customConfig.moduleName}\\controller",
                            OutputFile.xml to customConfig.xmlOutputDir
                        )
                    )
            } // 策略配置
            .strategyConfig { builder: StrategyConfig.Builder ->
                // 设置需要生成的表名
                builder
                    .addInclude(customConfig.tableNames) // 设置过滤表前缀
                    .addTablePrefix(customConfig.tablePrefix) // 实体配置
                    .addExclude()
                    .addFieldPrefix()
                    .entityBuilder()
                    .javaTemplate("/templates/all-entity.java")
                    .also {
                        if(customConfig.entityEnableFileOverride){
                            it.enableFileOverride()
                        }
                    }
                    // 开启生成字段注解
                    .enableTableFieldAnnotation()
                    // 开启lombok插件
                    .enableLombok()
                    .also {
                        if (customConfig.entityDisabled) {
                            it.disable()
                        }
                    }
                    // 服务配置
                    .serviceBuilder()
                    .convertServiceFileName((ConverterFileName { entityName: String -> entityName + ConstVal.SERVICE }))
                    .also {
                        if (customConfig.serviceDisabled) {
                            it.disable()
                        }
                    }
                    .serviceTemplate("/templates/service.kt")
                    .serviceImplTemplate("/templates/serviceImpl.kt")
                    .also {
                        if (customConfig.serviceEnableFileOverride) {
                            it.enableFileOverride()
                        }
                    }
                    // 控制器配置
                    .controllerBuilder()
                    .template("/templates/controller.kt")
                    .also {
                        if (customConfig.controllerDisabled) {
                            it.disable()
                        }
                    }
                    // 设置rest风格
                    .enableRestStyle()
                    .also {
                        if (customConfig.controllerEnableFileOverride) {
                            it.enableFileOverride()
                        }
                    }
                    .mapperBuilder()
                    .mapperTemplate("/templates/mapper.java")
                    .mapperXmlTemplate("/templates/mapper.xml")
                    .also {
                        if (customConfig.mapperDisabled) {
                            it.disable()
                        }
                    }
                    .also {
                        if (customConfig.mapperEnableFileOverride) {
                            it.enableFileOverride()
                        }
                    }
                    .mapperAnnotation(Mapper::class.java)
            }.injectionConfig { builder: InjectionConfig.Builder ->
                getInjectionConfig(builder, customConfig)
            }
            // 模板引擎配置
            // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .templateEngine(TemplateEngineDecorator(FreemarkerTemplateEngine()))
            .execute()
        CustomFile
            .Builder()
            .build()
    }

    private fun getInjectionConfig(builder: InjectionConfig.Builder, customConfig: CustomConfig) {
        builder
            .beforeOutputFile { t, u ->
                run {
                    // 获取配置信息
                    val packageInfo = (u["package"] as Map<*, *>).toMutableMap()
                    // 从包信息中获取父包名
                    val parentPackage = packageInfo["Parent"] as String
                    // 设置repository包名
                    if (StringUtils.hasText(parentPackage)) {
                        packageInfo["Repository"] = parentPackage + StringPool.DOT + "repository"
                    }
                    u["package"] = packageInfo
                    u["repositoryIdType"] = t.fields.find { it.isKeyFlag }?.columnType?.type
                    u["repositoryName"] = t.entityName.plus("Repository")
                    u["isBuilderModel"] = true
                    u["useApiVersion"] = true
                    u["useI18nMessageSource"] = true
                    if ("public" != customConfig.schema && customConfig.schema.isNotBlank()) {
                        u["schema"] = customConfig.schema
                    }
                    // 处理字段属性
                    t.fields.forEach {
                        val customMap = mapOf("constantName" to "FIELD_" + it.name.uppercase())
                        it.customMap = customMap
                    }
                }
            }
            .customFile(
                CustomFile
                    .Builder()
                    .packageName("repository")
                    .fileName("Repository.kt")
                    .templatePath("/templates/repository.kt.ftl")
                    .filePath(customConfig.repositoryOutputDir + "\\${customConfig.moduleName}")
                    .build()
            )
            .build()
    }

    private fun getGlobalConfig(
        builder: GlobalConfig.Builder,
        customConfig: CustomConfig
    ) {
        builder
            // 设置作者
            .author(customConfig.author)
            // 开启 springdoc 模式
            .enableSpringdoc()
            // 指定输出目录
            // 禁止打开输出目录
            .disableOpenDir()
    }

    private fun getDataSourceConfig(builder: DataSourceConfig.Builder) {
        builder.typeConvertHandler { _: GlobalConfig, typeRegistry: TypeRegistry, metaInfo: MetaInfo ->
            val typeCode = metaInfo.jdbcType.TYPE_CODE
            if (typeCode == Types.SMALLINT) {
                // 自定义类型转换
                return@typeConvertHandler DbColumnType.INTEGER
            }
            typeRegistry.getColumnType(metaInfo)
        }
    }

    /**
     * 字典模块代码生成器
     */
    private fun dictCodeGeneration() {
        val xmlOutputDir = "base-forge-data-dictionary\\src\\main\\resources\\mapper"
        val javaOutputDir = "base-forge-data-dictionary\\src\\main\\java\\com\\gewuyou\\baseforge"
        val kotlinOutputDir = "base-forge-data-dictionary\\src\\main\\kotlin\\com\\gewuyou\\baseforge"
        val moduleName = "dictionary"
        generation(
            customConfig = CustomConfig(
                moduleName, xmlOutputDir,javaOutputDir, kotlinOutputDir, kotlinOutputDir,kotlinOutputDir,kotlinOutputDir, listOf(
                    "bf_dictionary_type",
                    "bf_dictionary_type_history",
                    "bf_dynamic_config",
                    "bf_dynamic_config_history",
                    "bf_enum_data",
                    "bf_enum_data_history",
                    "bf_i18n_message_item"
                )
            )
        )
    }

    @JvmStatic
    fun main(args: Array<String>) {
        dictCodeGeneration()
    }
}
