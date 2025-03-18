package ${package.Controller}

import org.springframework.web.bind.annotation.RequestMapping
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController
<#else>
import org.springframework.stereotype.Controller
</#if>
<#if useI18nMessageSource>
import org.springframework.context.MessageSource
</#if>
<#if useApiVersion>
import com.gewuyou.baseforge.entities.web.annotation.ApiVersion
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage}
</#if>

/**
 *
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if useApiVersion>
@ApiVersion
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
class ${table.controllerName}(
  <#if useI18nMessageSource>
   private val i18nMessageSource: MessageSource
  </#if>
)<#if superControllerClass??> : ${superControllerClass}()</#if>{

}