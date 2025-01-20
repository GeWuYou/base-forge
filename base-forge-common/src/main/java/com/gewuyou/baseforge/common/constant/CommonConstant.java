package com.gewuyou.baseforge.common.constant;

/**
 * 公共常量
 *
 * @author gewuyou
 * @since 2024-10-04 22:34:36
 */
public class CommonConstant {
    //region 分页相关常量
    public static final String CREATED_AT = "created_at";
    //endregion
    //region Byte相关常量
    public static final Byte ONE = 1;
    /**
     * 当前页码
     */
    public static final String CURRENT = "current";

    /**
     * 分页尺寸
     */
    public static final String SIZE = "size";

    /**
     * 默认每页大小
     */
    public static final String DEFAULT_SIZE = "10";
    //endregion
    //region 响应状态码相关常量
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    //endregion
    //region OAuth2提供商相关常量
    public static final String GOOGLE = "Google";
    public static final String GITHUB = "GitHub";
    public static final String MICROSOFT = "Microsoft";
    public static final Byte ZERO = 0;
    public static final Byte TRUE = 1;
    public static final Byte FALSE = 0;

    private CommonConstant() {
    }
    //endregion
}
