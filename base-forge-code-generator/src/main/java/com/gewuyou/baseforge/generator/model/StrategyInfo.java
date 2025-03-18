package com.gewuyou.baseforge.generator.model;

import com.gewuyou.baseforge.common.model.BaseModel;
import com.gewuyou.baseforge.generator.converter.StringSetConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * 策略信息
 *
 * @author gewuyou
 * @since 2025-03-01 00:15:54
 */
@Entity
@Table(name = "bf_strategy_info", schema = "generator")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyInfo extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bf_strategy_info_id_gen")
    @SequenceGenerator(name = "bf_strategy_info_id_gen", sequenceName = "bf_strategy_info_id_seq", allocationSize = 1)
    private Long id;
    /**
     * 所属模块ID
     */
    @Column(name = "module_id")
    private Long moduleId;
    /**
     * 策略名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 表过滤前缀
     */
    @Column(name = "table_prefix")
    @Convert(converter = StringSetConverter.class)
    private Set<String> tablePrefix;
    /**
     * 表过滤后缀
     */
    @Column(name = "table_suffix")
    @Convert(converter = StringSetConverter.class)
    private Set<String> tableSuffix;
    /**
     * 字段过滤前缀
     */
    @Column(name = "field_prefix")
    @Convert(converter = StringSetConverter.class)
    private Set<String> fieldPrefix;
    /**
     * 字段过滤后缀
     */
    @Column(name = "field_suffix")
    @Convert(converter = StringSetConverter.class)
    private Set<String> fieldSuffix;
    /**
     * 需要包含的表名，允许正则表达式（与exclude二选一配置）<br/>
     * 当{@link #enableSqlFilter}为true时，正则表达式无效.
     */
    @Column(name = "table_include")
    @Convert(converter = StringSetConverter.class)
    private Set<String> tableInclude;
    /**
     * 需要排除的表名，允许正则表达式<br/>
     * 当{@link #enableSqlFilter}为true时，正则表达式无效.
     */
    @Column(name = "table_exclude")
    @Convert(converter = StringSetConverter.class)
    private Set<String> tableExclude;
    /**
     * 启用sql过滤，语法不能支持使用sql过滤表的话，可以考虑关闭此开关.
     *
     */
    @ColumnDefault("true")
    @Column(name = "enable_sql_filter")
    private Boolean enableSqlFilter;

    /**
     * 是否启用schema模式 默认false
     */
    @ColumnDefault("false")
    @Column(name = "enable_schema")
    private Boolean enableSchema;
    /**
     * 是否大写命名
     */
    @ColumnDefault("false")
    @Column(name = "is_capital_mode")
    private Boolean isCapitalMode;

    /**
     * 是否跳过视图
     */
    @ColumnDefault("false")
    @Column(name = "skip_view")
    private Boolean skipView;
}
