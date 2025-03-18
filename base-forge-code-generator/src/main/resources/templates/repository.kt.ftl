package ${package.Repository}

import ${package.Entity}.${entity}
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
*
* ${table.comment!} Repository 接口
*
*
* @author ${author}
* @since ${date}
*/
interface ${repositoryName} : JpaRepository<${entity}, ${repositoryIdType}>, JpaSpecificationExecutor<${entity}>{

}