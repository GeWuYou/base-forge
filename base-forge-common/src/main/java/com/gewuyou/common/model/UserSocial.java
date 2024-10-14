package com.gewuyou.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户社交账号绑定表
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-09
 */
@Schema(name = "UserSocial对象", description = "用户社交账号绑定表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user_social")
public class UserSocial extends BaseModel implements Serializable {

    @Schema(hidden = true)
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 社交账号绑定ID
     */
    @Schema(description = "社交账号绑定ID")
    @TableId("id")
    private String id;

    /**
     * 用户信息ID
     */
    @Schema(description = "用户信息ID")
    @TableField("user_info_id")
    private String userInfoId;

    /**
     * 社交平台名称 (如 Google, GitHub 等)
     */
    @Schema(description = "社交平台名称 (如 Google, GitHub 等)")
    @TableField("social_provider")
    private String socialProvider;

    /**
     * 社交平台上的用户 ID
     */
    @Schema(description = "社交平台上的用户 ID")
    @TableField("social_id")
    private String socialId;

    /**
     * 社交平台个人主页链接
     */
    @Schema(description = "社交平台个人主页链接")
    @TableField("social_link")
    private String socialLink;

    /**
     * 是否绑定社交账号 1 表示已绑定 0 表示已解绑
     */
    @Schema(description = "是否绑定社交账号 1 表示已绑定 0 表示已解绑")
    @TableField("is_social_bound")
    private Boolean isSocialBound;
}
