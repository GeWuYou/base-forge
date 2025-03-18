package com.gewuyou.baseforge.generator.model;

import com.gewuyou.baseforge.common.model.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;
import java.io.Serializable;

/**
 * 模板信息
 *
 * @author gewuyou
 * @since 2025-02-28 16:36:51
 */
@Entity
@Table(name = "bf_template_info", schema = "generator")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateInfo extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bf_template_info_id_gen")
    @SequenceGenerator(name = "bf_template_info_id_gen", sequenceName = "bf_template_info_id_seq", allocationSize = 1)
    private Long id;

    /**
     * 模板类型: entity, service, serviceImpl, controller, repository, mapper, mapperXml, other
     */
    @Column(name = "type")
    private String type;

    /**
     * 描述
     */
    @ColumnDefault(Strings.EMPTY)
    @Column(name = "description")
    private String description;
    /**
     * 语言类型
     */
    @Column(name = "language")
    private String language;
    /**
     * 最终生成的文件名为：filePrefix+fileName+fileSuffix+由模板类型决定的文件扩展名
     * 文件名称(不带扩展名,扩展名由模板类型决定)
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 生成文件的前缀
     */
    @Column(name = "file_prefix")
    private String filePrefix;

    /**
     * 生成文件的后缀
     */
    @Column(name = "file_suffix")
    private String fileSuffix;

    /**
     * 模板路径: 本地使用，模板文件相对于模板目录的路径也可以是绝对路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 模板内容：模板文件内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 自定义文件包名
     */
    @Column(name = "package_name")
    private String packageName;

    /**
     * 文件输出路径：生成文件的输出路径,默认覆盖项目信息中定义的输出路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 是否覆盖已有文件（默认 false）
     */
    @ColumnDefault("false")
    @Column(name = "file_override")
    private boolean fileOverride;

    /**
     * 是否禁用输出（默认 false）
     */
    @ColumnDefault("false")
    @Column(name = "is_disable")
    private Boolean isDisable;
}
