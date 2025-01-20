package com.gewuyou.baseforge.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.gewuyou.baseforge.common.enums.AuthProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户认证信息表
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-05
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "<p> 用户认证信息表 </p>")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user_auth")
public class UserAuth extends BaseModel implements Serializable {

    @Schema(hidden = true)
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户认证id
     */
    @Schema(description = "用户认证id")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户信息id
     */
    @Schema(description = "用户信息id")
    @TableField("user_info_id")
    private String userInfoId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @TableField("phone")
    private String phone;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @TableField("password")
    private String password;

    /**
     * 登录类型  0表示本地登录 1表示谷歌登录 2表示github登录 3表示微软登录
     */
    @Schema(description = "登录类型  0表示本地登录 1表示谷歌登录 2表示github登录 3表示微软登录")
    @TableField("auth_provider")
    private AuthProvider authProvider;

    /**
     * 用户登录ip
     */
    @Schema(description = "用户登录ip")
    @TableField("ip_address")
    private String ipAddress;

    /**
     * 是否启用  0表示禁用 1表示启用
     */
    @Schema(description = "是否启用  0表示禁用 1表示启用")
    @TableField("is_enabled")
    private Byte isEnabled;
    /**
     * ip属地
     */
    @Schema(description = "ip属地")
    @TableField("ip_source")
    private String ipSource;

    /**
     * 上次登录时间
     */
    @Schema(description = "上次登录时间")
    @TableField("last_login_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastLoginTime;

    /**
     * 当前登录时间
     */
    @Schema(description = "当前登录时间")
    @TableField("current_login_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime currentLoginTime;
}
