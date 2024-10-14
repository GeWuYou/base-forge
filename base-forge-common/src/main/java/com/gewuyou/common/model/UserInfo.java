package com.gewuyou.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-09
 */
@Schema(name = "UserInfo对象", description = "用户信息表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user_info")
public class UserInfo extends BaseModel implements Serializable {

    @Schema(hidden = true)
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    @Schema(description = "用户Id")
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

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
     * 昵称
     */
    @Schema(description = "昵称")
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    @TableField("avatar")
    private String avatar;

    /**
     * 用户简介
     */
    @Schema(description = "用户简介")
    @TableField("intro")
    private String intro;

    /**
     * 是否删除 0 表示未删除 1 表示已删除
     */
    @Schema(description = "是否删除 0 表示未删除 1 表示已删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    /**
     * 性别 0 表示未知 1 表示男 2 表示女
     */
    @Schema(description = "性别 0 表示未知 1 表示男 2 表示女")
    @TableField("gender")
    private Byte gender;

}
