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
 * 菜单表
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-09
 */
@Schema(name = "Menu对象", description = "菜单表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_menu")
public class Menu extends BaseModel implements Serializable {
    @Schema(hidden = true)
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @Schema(description = "菜单id")
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 菜单名
     */
    @Schema(description = "菜单名")
    @TableField("name")
    private String name;

    /**
     * 菜单路径
     */
    @Schema(description = "菜单路径")
    @TableField("path")
    private String path;

    /**
     * 菜单组件
     */
    @Schema(description = "菜单组件")
    @TableField("component")
    private String component;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    @TableField("icon")
    private String icon;

    /**
     * 排序号
     */
    @Schema(description = "排序号")
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 父菜单id(默认为null，表示顶级菜单)
     */
    @Schema(description = "父菜单id(默认为null，表示顶级菜单)")
    @TableField("parent_id")
    private String parentId;

    /**
     * 是否隐藏 0表示否。1表示是
     */
    @Schema(description = "是否隐藏 0表示否。1表示是")
    @TableField("is_hidden")
    private Boolean isHidden;
}
