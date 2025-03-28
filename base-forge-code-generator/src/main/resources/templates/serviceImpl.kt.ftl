package ${package.ServiceImpl}

import ${package.Entity}.${entity}
import ${package.Mapper}.${table.mapperName}
import ${package.Service}.${table.serviceName}
import ${superServiceImplClassPackage}
import org.springframework.stereotype.Service

/**
 *
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
