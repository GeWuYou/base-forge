package com.gewuyou.baseforge.generator.model;

import com.gewuyou.baseforge.common.model.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;
import java.io.Serializable;

/**
 * 模块信息： 一个项目可能由多个模块组成，每个模块都有自己的模块信息
 *
 * @author gewuyou
 * @since 2025-02-28 16:40:08
 */
@Entity
@Table(name = "bf_module_info", schema = "generator")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleInfo extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bf_module_info_id_gen")
    @SequenceGenerator(name = "bf_module_info_id_gen", sequenceName = "bf_module_info_id_seq", allocationSize = 1)
    private Long id;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 模块名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 模块包名
     */
    @Column(name = "package_name")
    private String packageName;

    /**
     * 模板引擎
     */
    @Column(name = "template_engine")
    private String templateEngine;
    /**
     * 描述
     */
    @ColumnDefault(Strings.EMPTY)
    @Column(name = "description")
    private String description;
}
