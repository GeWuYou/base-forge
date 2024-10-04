package com.gewuyou.cache.client;

import java.util.Map;
import java.util.Set;

/**
 * gRPC缓存客户端接口
 * 定义了与缓存系统交互的方法，包括设置、获取和删除缓存数据。
 *
 * @author gewuyou
 * @since 2024-10-03 22:49:15
 */
public interface ICacheClientGrpc {

    /**
     * 设置缓存值。
     *
     * @param key   缓存的键
     * @param value 要设置的值
     */
    void set(String key, String value);

    /**
     * 设置缓存值并指定过期时间（秒）。
     *
     * @param key        缓存的键
     * @param value      要设置的值
     * @param expireTime 过期时间（单位：秒）
     */
    void setWithExpireTimeBySec(String key, String value, long expireTime);

    /**
     * 设置缓存值并指定过期时间（毫秒）。
     *
     * @param key        缓存的键
     * @param value      要设置的值
     * @param expireTime 过期时间（单位：毫秒）
     */
    void setWithExpireTimeByMills(String key, String value, long expireTime);

    /**
     * 获取缓存值。
     *
     * @param key 缓存的键
     * @return 缓存的值，如果不存在，返回 null
     */
    String get(String key);

    /**
     * 删除指定的缓存项。
     *
     * @param key 缓存的键
     * @return 如果删除成功，返回 true；否则返回 false
     */
    boolean delete(String key);

    /**
     * 延迟删除指定的缓存项（按秒）。
     *
     * @param key        缓存的键
     * @param expireTime 过期时间（单位：秒）
     * @return 如果删除成功，返回 true；否则返回 false
     */
    boolean delayedDeleteBySec(String key, long expireTime);

    /**
     * 延迟删除指定的缓存项（按毫秒）。
     *
     * @param key        缓存的键
     * @param expireTime 过期时间（单位：毫秒）
     * @return 如果删除成功，返回 true；否则返回 false
     */
    boolean delayedDeleteByMills(String key,  long expireTime);

    /**
     * 设置缓存项的过期时间（秒）。
     *
     * @param key        缓存的键
     * @param expireTime 过期时间（单位：秒）
     * @return 如果设置成功，返回 true；否则返回 false
     */
    boolean setExpireTimeBySec(String key, long expireTime);

    /**
     * 设置缓存项的过期时间（毫秒）。
     *
     * @param key        缓存的键
     * @param expireTime 过期时间（单位：毫秒）
     * @return 如果设置成功，返回 true；否则返回 false
     */
    boolean setExpireTimeByMills(String key, long expireTime);

    /**
     * 获取缓存项的剩余过期时间（秒）。
     *
     * @param key 缓存的键
     * @return 剩余过期时间（单位：秒），如果不存在，返回 -1
     */
    long getExpireTimeBySec(String key);

    /**
     * 获取缓存项的剩余过期时间（毫秒）。
     *
     * @param key 缓存的键
     * @return 剩余过期时间（单位：毫秒），如果不存在，返回 -1
     */
    long getExpireTimeByMills(String key);

    /**
     * 检查指定的键是否存在。
     *
     * @param key 缓存的键
     * @return 如果存在，返回 true；否则返回 false
     */
    boolean hasKey(String key);

    /**
     * 对指定的键进行自增操作。
     *
     * @param key   缓存的键
     * @param delta 增量值
     * @return 增加后的值
     */
    long incr(String key, int delta);

    /**
     * 对指定的键进行自减操作。
     *
     * @param key   缓存的键
     * @param delta 减量值
     * @return 减少后的值
     */
    long decr(String key, int delta);

    /**
     * 设置哈希表中指定字段的值。
     *
     * @param key     哈希表的键
     * @param hashKey 哈希字段的键
     * @param value   要设置的值
     */
    void hSet(String key, String hashKey, String value);

    /**
     * 设置哈希表中指定字段的值，并指定过期时间（秒）。
     *
     * @param key        哈希表的键
     * @param hashKey    哈希字段的键
     * @param value      要设置的值
     * @param expireTime 过期时间（单位：秒）
     */
    void hSetWithExpireTimeBySec(String key, String hashKey, String value, long expireTime);

    /**
     * 设置哈希表中指定字段的值，并指定过期时间（毫秒）。
     *
     * @param key        哈希表的键
     * @param hashKey    哈希字段的键
     * @param value      要设置的值
     * @param expireTime 过期时间（单位：毫秒）
     */
    void hSetWithExpireTimeByMills(String key, String hashKey, String value, long expireTime);

    /**
     * 获取哈希表中指定字段的值。
     *
     * @param key     哈希表的键
     * @param hashKey 哈希字段的键
     * @return 哈希字段的值，如果不存在，返回 null
     */
    String hGet(String key, String hashKey);

    /**
     * 获取哈希表中所有字段的值。
     *
     * @param key 哈希表的键
     * @return 包含所有字段和值的映射
     */
    Map<String, Object> hGetAll(String key);

    /**
     * 删除哈希表中一个或多个字段。
     *
     * @param key     哈希表的键
     * @param hashKey 一个或多个哈希字段的键
     * @return 被删除字段的数量
     */
    long hDel(String key, String... hashKey);

    /**
     * 对哈希表中指定字段进行自增操作。
     *
     * @param key     哈希表的键
     * @param hashKey 哈希字段的键
     * @param delta   增量值
     * @return 增加后的值
     */
    long hIncr(String key, String hashKey, int delta);

    /**
     * 对哈希表中指定字段进行自减操作。
     *
     * @param key     哈希表的键
     * @param hashKey 哈希字段的键
     * @param delta   减量值
     * @return 减少后的值
     */
    long hDecr(String key, String hashKey, int delta);

    /**
     * 对有序集合中的指定元素进行分数自增操作。
     *
     * @param key     有序集合的键
     * @param value   要增加分数的元素
     * @param score   增幅
     * @return 增加后的分数
     */
    double zIncr(String key, String value, double score);

    /**
     * 对有序集合中的指定元素进行分数自减操作。
     *
     * @param key     有序集合的键
     * @param value  要减少分数的元素
     * @param score   减幅
     * @return 减少后的分数
     */
    double zDecr(String key, String value, double score);

    /**
     * 以分数的降序获取有序集合中指定范围的元素及其分数。
     *
     * @param key   有序集合的键
     * @param start 起始位置（从 0 开始）
     * @param end   结束位置（从 0 开始）
     * @return 包含元素及其分数的映射
     */
    Map<Object, Double> zReversedRangWithScores(String key, long start, long end);

    /**
     * 获取有序集合中指定元素的分数。
     *
     * @param key   有序集合的键
     * @param value 要查询分数的元素
     * @return 元素的分数，如果不存在，返回 -1
     */
    double zScore(String key, String value);

    /**
     * 获取有序集合中所有元素及其分数。
     *
     * @param key 有序集合的键
     * @return 包含所有元素及其分数的映射
     */
    Map<Object, Double> zAllScore(String key);

    /**
     * 向集合中添加一个或多个元素。
     *
     * @param key    集合的键
     * @param values 要添加的元素
     * @return 被添加元素的数量，不包括已经存在的元素
     */
    long sAdd(String key, Object... values);

    /**
     * 检查指定的元素是否为集合的成员。
     *
     * @param key   集合的键
     * @param value 要检查的元素
     * @return 如果元素是集合的成员，返回 true；否则返回 false
     */
    boolean sisMember(String key, String value);

    /**
     * 获取集合中元素的数量。
     *
     * @param key 集合的键
     * @return 集合中元素的数量
     */
    long sSize(String key);

    /**
     * 获取多个集合的差集。
     *
     * @param keys 要计算差集的多个集合的键
     * @return 包含差集元素的集合
     */
    Set<Object> sDiff(String... keys);

    /**
     * 如果指定的键不存在，则添加元素并设置过期时间（秒）。
     *
     * @param key        缓存的键
     * @param value      要添加的元素
     * @param expireTime 过期时间（单位：秒）
     * @return 如果添加成功，返回 true；否则返回 false
     */
    boolean sIfAbsentBySec(String key, String value, long expireTime);

    /**
     * 如果指定的键不存在，则添加元素并设置过期时间（毫秒）。
     *
     * @param key        缓存的键
     * @param value      要添加的元素
     * @param expireTime 过期时间（单位：毫秒）
     * @return 如果添加成功，返回 true；否则返回 false
     */
    boolean sIfAbsentByMills(String key, String value, long expireTime);
}
