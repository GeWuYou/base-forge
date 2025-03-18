package com.gewuyou.baseforge.generator.model;

import com.gewuyou.baseforge.common.model.BaseModel;
import com.gewuyou.baseforge.generator.enums.DatabaseType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库信息: 数据库类型、名称、地址、端口、用户名、密码、连接URL、驱动、状态
 *
 * @author gewuyou
 * @since 2025-02-28 10:58:10
 */
@Entity
@Table(name = "bf_database_info", schema = "generator")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseInfo extends BaseModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bf_database_info_id_gen")
    @SequenceGenerator(name = "bf_database_info_id_gen", sequenceName = "bf_database_info_id_seq", allocationSize = 1)
    private Long id;
    /**
     * 数据库类型
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DatabaseType type;
    /**
     * 数据库名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 数据库地址
     */
    @Column(name = "host")
    private String host;
    /**
     * 数据库端口
     */
    @Column(name = "port")
    private String port;
    /**
     * 数据库用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 数据库密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 数据库驱动
     */
    @Column(name = "driver")
    private String driver;
    /**
     * 数据库状态
     */
    @Column(name = "status")
    private Boolean status;
}
