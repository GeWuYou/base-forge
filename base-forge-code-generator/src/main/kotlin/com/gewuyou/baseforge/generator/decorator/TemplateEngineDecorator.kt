package com.gewuyou.baseforge.generator.decorator

import com.baomidou.mybatisplus.core.toolkit.StringPool
import com.baomidou.mybatisplus.generator.config.ConstVal
import com.baomidou.mybatisplus.generator.config.OutputFile
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder
import com.baomidou.mybatisplus.generator.config.po.TableInfo
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine
import java.io.File

/**
 *模板引擎装饰器
 *
 * @since 2025-03-02 11:18:31
 * @author gewuyou
 */
class TemplateEngineDecorator(
    private val templateEngine: AbstractTemplateEngine
): AbstractTemplateEngine() {
    /**
     * 模板引擎初始化
     */
    override fun init(configBuilder: ConfigBuilder): AbstractTemplateEngine {
        templateEngine.init(configBuilder)
        return this
    }

    /**
     * 将模板转化成为文件
     *
     * @param objectMap    渲染对象 MAP 信息
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     * @throws Exception 异常
     * @since 3.5.0
     */
    override fun writer(objectMap: MutableMap<String, Any>, templatePath: String, outputFile: File) {
        templateEngine.writer(objectMap, templatePath, outputFile)
    }

    /**
     * 模板真实文件路径
     *
     * @param filePath 文件路径
     * @return ignore
     */
    override fun templateFilePath(filePath: String): String {
        return templateEngine.templateFilePath(filePath)
    }

    /**
     * 输出Mapper文件(含xml)
     *
     * @param tableInfo 表信息
     * @param objectMap 渲染数据
     * @since 3.5.0
     */
    override fun outputMapper(tableInfo: TableInfo, objectMap: MutableMap<String, Any>) {
        // MpMapper
        val entityName = tableInfo.entityName
        val mapperPath = getPathInfo(OutputFile.mapper)
        val mapper = configBuilder.strategyConfig.mapper()
        if (mapper.isGenerateMapper) {
            val mapperFile =
                String.format(
                    (mapperPath + File.separator + tableInfo.mapperName +
                            StringPool.DOT + extractExtensionFromTemplatePath(mapper.mapperTemplatePath)
                            ), entityName
                )
            outputFile(
                getOutputFile(mapperFile, OutputFile.mapper),
                objectMap,
                templateFilePath(mapper.mapperTemplatePath),
                configBuilder.strategyConfig.mapper().isFileOverride
            )
        }

        // MpMapper.xml
        val xmlPath = getPathInfo(OutputFile.xml)
        if (mapper.isGenerateMapperXml) {
            val xmlFile =
                String.format((xmlPath + File.separator + tableInfo.xmlName + ConstVal.XML_SUFFIX), entityName)
            outputFile(
                getOutputFile(xmlFile, OutputFile.xml),
                objectMap,
                templateFilePath(mapper.mapperXmlTemplatePath),
                configBuilder.strategyConfig.mapper().isFileOverride
            )
        }
    }

    //region Description
    /**
     * 输出service文件
     *
     * @param tableInfo 表信息
     * @param objectMap 渲染数据
     * @since 3.5.0
     */
    override fun outputService(tableInfo: TableInfo, objectMap: MutableMap<String, Any>) {
        // IMpService
        val entityName = tableInfo.entityName
        // 判断是否要生成service接口
        val service = configBuilder.strategyConfig.service()
        if (service.isGenerateService) {
            val servicePath = getPathInfo(OutputFile.service)
            val serviceFile =
                String.format(
                    (servicePath + File.separator + tableInfo.serviceName +
                            StringPool.DOT +
                            extractExtensionFromTemplatePath(service.serviceTemplate)
                            ), entityName
                )
            outputFile(
                getOutputFile(serviceFile, OutputFile.service),
                objectMap,
                templateFilePath(service.serviceTemplate),
                configBuilder.strategyConfig.service().isFileOverride
            )
        }

        // MpServiceImpl.java
        val serviceImplPath = getPathInfo(OutputFile.serviceImpl)
        if (service.isGenerateServiceImpl) {
            val implFile = String.format(
                (serviceImplPath + File.separator +
                        tableInfo.serviceImplName +
                        StringPool.DOT + extractExtensionFromTemplatePath(service.serviceImplTemplate)),
                entityName
            )
            outputFile(
                getOutputFile(implFile, OutputFile.serviceImpl),
                objectMap,
                templateFilePath(service.serviceImplTemplate),
                configBuilder.strategyConfig.service().isFileOverride
            )
        }
    }
    //endregion

    /**
     * 输出controller文件
     *
     * @param tableInfo 表信息
     * @param objectMap 渲染数据
     * @since 3.5.0
     */
    override fun outputController(tableInfo: TableInfo, objectMap: MutableMap<String, Any>) {
        // MpController.java
        val controller = configBuilder.strategyConfig.controller()
        val controllerPath = getPathInfo(OutputFile.controller)
        if (controller.isGenerate) {
            val entityName = tableInfo.entityName
            val controllerFile = String.format(
                (controllerPath + File.separator +
                        tableInfo.controllerName +
                        StringPool.DOT +
                        extractExtensionFromTemplatePath(controller.templatePath)),
                entityName
            )
            outputFile(
                getOutputFile(controllerFile, OutputFile.controller),
                objectMap,
                templateFilePath(controller.templatePath),
                configBuilder.strategyConfig.controller().isFileOverride
            )
        }
    }

    /**
     * 从模板路径中提取文件扩展名
     */
    private fun extractExtensionFromTemplatePath(path: String): String {
        return path.substringAfterLast('.', "")
    }
}