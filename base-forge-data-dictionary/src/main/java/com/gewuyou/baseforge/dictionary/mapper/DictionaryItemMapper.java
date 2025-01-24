package com.gewuyou.baseforge.dictionary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gewuyou.baseforge.dictionary.model.DictionaryItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 * 字典项表 Mapper 接口
 *
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Mapper
public interface DictionaryItemMapper extends BaseMapper<DictionaryItem> {

    /**
     * 根据code和categoryName获取字典项
     * @param code 字典项code
     * @param categoryName 字典项分类名称
     * @return 字典项
     */
    @Nullable DictionaryItem getDictionaryItemByCodeAndCategoryName(@NotNull @Param("code") String code, @NotNull @Param("categoryName") String categoryName);

    /**
     * 根据value和categoryName获取字典项
     * @param value 字典项值
     * @param categoryName 字典项分类名称
     * @return 字典项
     */
    @Nullable DictionaryItem getDictionaryItemByValueAndCategoryName(@NotNull @Param("value") String value, @NotNull @Param("categoryName") String categoryName);
}
