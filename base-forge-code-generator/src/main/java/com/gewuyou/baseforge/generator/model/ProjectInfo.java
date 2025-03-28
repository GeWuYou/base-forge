package com.gewuyou.baseforge.generator.model;

import com.gewuyou.baseforge.common.model.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;
import java.io.Serializable;

/**
 * 项目信息
 *
 * @author gewuyou
 * @since 2025-02-28 11:49:32
 */
@Entity
@Table(name = "bf_project_info", schema = "generator")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfo extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bf_project_info_id_gen")
    @SequenceGenerator(name = "bf_project_info_id_gen", sequenceName = "bf_project_info_id_seq", allocationSize = 1)
    private Long id;

    /**
     * 项目名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 项目根路径
     */
    @Column(name = "root_path")
    private String rootPath;

    /**
     * 项目包名
     */
    @Column(name = "package_name")
    private String packageName;

    /**
     * 数据库信息ID
     */
    @Column(name = "database_info_id")
    private Long databaseInfoId;
    /**
     * 作者
     */
    @Column(name = "author")
    private String author;
    /**
     * 项目语言 全局使用项目语言，模板语言可单独配置语言
     */
    @Column(name = "language")
    private String language;
    /**
     * 描述
     */
    @ColumnDefault(Strings.EMPTY)
    @Column(name = "description")
    private String description;
    /**
     * 是否启用SpringDoc
     */
    @ColumnDefault("true")
    @Column(name = "is_spring_doc")
    private Boolean isSpringDoc;
    /**
     * 是否启用Swagger
     */
    @ColumnDefault("false")
    @Column(name = "is_enable_swagger")
    private Boolean isEnableSwagger;
}
