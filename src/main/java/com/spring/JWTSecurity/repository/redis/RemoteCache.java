package com.spring.JWTSecurity.repository.redis;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class RemoteCache {

    public final static int CACHE_DURATION_DEFAULT = 3600;

    private final RedisTemplate<String, String> redisTemplate;

    public void put(String key, String value, int expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    public void put(String key, Object object) {
        try {
            redisTemplate.opsForValue().set(key, new Gson().toJson(object));
        } catch (Exception e) {
            log.error("========json parser: ", e);
        }
    }

    public <T> T get(String key, Class<T> tClass) {
        try {
            String value = redisTemplate.opsForValue().get(key);
            return new Gson().fromJson(value, tClass);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
