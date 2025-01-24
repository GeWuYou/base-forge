package com.gewuyou.baseforge.dictionary.enums

/**
 *操作类型
 *
 * @since 2025-01-22 17:20:37
 * @author gewuyou
 */
enum class OperationType(
    private val value: Int
) {

    INSERT(1),
    /**
     * 修改
     */
    UPDATE(2),

    /**
     * 逻辑删除
     */
    LOGICAL_DELETE(3),

    /**
     * 物理删除
     */
    PHYSICAL_DELETE(4)
    ;

    fun getValue(): Int {
        return value
    }
}