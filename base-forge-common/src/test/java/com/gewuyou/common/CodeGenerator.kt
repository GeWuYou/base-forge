package com.gewuyou.common

import com.baomidou.mybatisplus.generator.FastAutoGenerator
import com.baomidou.mybatisplus.generator.config.*
import com.baomidou.mybatisplus.generator.config.po.TableField.MetaInfo
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import com.baomidou.mybatisplus.generator.function.ConverterFileName
import com.baomidou.mybatisplus.generator.type.TypeRegistry
import org.apache.ibatis.annotations.Mapper
import java.sql.Types

/**
 * 代码生成器
 *
 * @author gewuyou
 * @since 2024-04-13 下午7:48:59
 */
object CodeGenerator {
    private const val JDBC_URL =
        "jdbc:postgresql://192.168.200.131:5432/base_forge"
    private const val JDBC_USERNAME = "root"
    private const val JDBC_PASSWORD = "root"
    private const val AUTHOR = "gewuyou"
    private const val PARENT_PACKAGE = "com.gewuyou.baseforge"
    private const val ENTITY_OUTPUT_DIR = "base-forge-common\\src\\main\\java\\com\\gewuyou\\baseforge"
    private const val TABLE_PREFIX = "bf_"

    /**
     * 代码生成器
     *
     * @param outputDir    输出目录
     * @param xmlOutputDir xml输出目录
     * @param moduleName   模块名
     * @param tableNames   表名列表
     * @apiNote 查询数据库生成代码
     * @since 2024/4/13 下午9:37
     */
    private fun generation(
        outputDir: String,
        xmlOutputDir: String,
        moduleName: String,
        tableNames: List<String>,
        entityOutputDir: String = ENTITY_OUTPUT_DIR,
    ) {
        FastAutoGenerator.create(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD) // 全局配置
            .globalConfig { builder: GlobalConfig.Builder ->
                builder
                    // 设置作者
                    .author(AUTHOR)
                    // 开启 springdoc 模式
                    .enableSpringdoc()
                    // 开启 kotlin 模式
                    .enableKotlin()
                    // 指定输出目录
                    .outputDir(outputDir)
                    // 禁止打开输出目录
                    .disableOpenDir()
            } // 数据库配置
            .dataSourceConfig { builder: DataSourceConfig.Builder ->
                builder.typeConvertHandler { _: GlobalConfig, typeRegistry: TypeRegistry, metaInfo: MetaInfo ->
                    val typeCode = metaInfo.jdbcType.TYPE_CODE
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return@typeConvertHandler DbColumnType.INTEGER
                    }
                    typeRegistry.getColumnType(metaInfo)
                }
            } // 包配置
            .packageConfig { builder: PackageConfig.Builder ->
                builder // 设置父包名
                    .parent(PARENT_PACKAGE) // 设置父包模块名
                    .moduleName(moduleName)
                    .entity("model") // 设置各个模块代码生成路径
                    .pathInfo(
                        mapOf(
                            OutputFile.entity to entityOutputDir + "\\${moduleName}\\model",
                            OutputFile.xml to xmlOutputDir
                        )
                    )
            } // 策略配置
            .strategyConfig { builder: StrategyConfig.Builder ->
                // 设置需要生成的表名
                builder
                    .addInclude(tableNames) // 设置过滤表前缀
                    .addTablePrefix(TABLE_PREFIX) // 实体配置
                    .entityBuilder()
                    .enableFileOverride()
                    // 开启生成字段注解
                    .enableTableFieldAnnotation()
                    // 开启lombok插件
                    .enableLombok()
                    .disable()
                    // 服务配置
                    .serviceBuilder()
                    .convertServiceFileName((ConverterFileName { entityName: String -> entityName + ConstVal.SERVICE }))
//                    .disable()
                    .enableFileOverride()
                    // 控制器配置
                    .controllerBuilder()
//                    .disable()
                    // 设置rest风格
                    .enableRestStyle()
                    .enableFileOverride()
                    .mapperBuilder()
//                    .disable()
                    .enableFileOverride()
                    .mapperAnnotation(Mapper::class.java)
            } // 模板引擎配置
            // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .templateEngine(FreemarkerTemplateEngine())
            .execute()
    }

    /**
     * auth模块代码生成器
     */
    private fun authCodeGeneration() {
        val outputDir = "base-forge-auth\\src\\main\\kotlin"
        val xmlOutputDir = "base-forge-auth\\src\\main\\resources\\mapper"
        val moduleName = "auth"
        generation(
            outputDir, xmlOutputDir, moduleName, listOf(
                "bf_auth_permission",
                "bf_auth_role",
                "bf_auth_user",
                "bf_auth_resource",
                "bf_auth_user_role",
                "bf_auth_user_permission",
                "bf_auth_role_permission",
                "bf_auth_role_resource_permission"
            )
        )
    }
    /**
     * auth模块代码生成器
     */
    private fun dicCodeGeneration() {
        val outputDir = "base-forge-data-dictionary\\src\\main\\kotlin"
        val xmlOutputDir = "base-forge-data-dictionary\\src\\main\\resources\\mapper"
        val moduleName = "dictionary"
        generation(
            outputDir, xmlOutputDir, moduleName, listOf(
                "bf_dictionary_category",
                "bf_dictionary_item",
                "bf_dictionary_item_history",
                "bf_dictionary_item_translation"
            )
        )
    }

    @JvmStatic
    fun main(args: Array<String>) {
//        authCodeGeneration()
        dicCodeGeneration()
    }
}
