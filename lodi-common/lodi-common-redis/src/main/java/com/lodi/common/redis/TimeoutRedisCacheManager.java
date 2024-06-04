package com.lodi.common.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.StringUtils;

import java.time.Duration;

import static com.lodi.common.core.constant.CommonConstant.SEPARATOR;
import static com.lodi.common.core.constant.CommonConstant.SPLIT;

/**
 * 支持自定义过期时间的 {@link RedisCacheManager} 实现类
 * <p>
 * 在 {@link Cacheable#cacheNames()} 格式为 "key#ttl" 时，# 后面的 ttl 为过期时间
 * 单位为最后一个字母（支持的单位有：d-天、h-小时、m-分钟、s-秒），默认单位为 s 秒
 *
 * @author MaybeBin
 * @createDate 2024-05-08
 */
public class TimeoutRedisCacheManager extends RedisCacheManager {

    public TimeoutRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        if (!StringUtils.hasText(name)) {
            return super.createRedisCache(name, cacheConfig);
        }
        // 如果使用 # 分隔，大小不为 2，则说明不使用自定义过期时间
        String[] names = StringUtils.split(name, SPLIT);
        if (names.length != 2) {
            return super.createRedisCache(name, cacheConfig);
        }

        // 核心：通过修改 cacheConfig 的过期时间，实现自定义过期时间
        if (cacheConfig != null) {
            // 移除 # 后面的 : 以及后面的内容，避免影响解析
            int pos = names[1].indexOf(SEPARATOR);
            if (-1 != pos) {
                names[1] = names[1].substring(0, pos);
            }
            // 解析时间
            Duration duration = parseDuration(names[1]);
            cacheConfig = cacheConfig.entryTtl(duration);
        }
        return super.createRedisCache(name, cacheConfig);
    }

    /**
     * 解析过期时间 Duration
     *
     * @param ttlStr 过期时间字符串
     * @return 过期时间 Duration
     */
    private Duration parseDuration(String ttlStr) {
        int index = ttlStr.length() - 1 > 0 ? ttlStr.length() - 1 : 0;
        String timeUnit = ttlStr.substring(index);
        String timeStr = ttlStr.substring(0, index);
        switch (timeUnit) {
            case "d":
                return Duration.ofDays(removeDurationSuffix(timeStr));
            case "h":
                return Duration.ofHours(removeDurationSuffix(timeStr));
            case "m":
                return Duration.ofMinutes(removeDurationSuffix(timeStr));
            case "s":
                return Duration.ofSeconds(removeDurationSuffix(timeStr));
            default:
                return Duration.ofSeconds(Long.parseLong(timeStr));
        }
    }

    /**
     * 移除多余的后缀，返回具体的时间
     *
     * @param ttlStr 过期时间字符串
     * @return 时间
     */
    private Long removeDurationSuffix(String ttlStr) {
        if (!StringUtils.hasText(ttlStr)) {
            return 0L;
        }
        return Long.parseLong(ttlStr);
    }

}
