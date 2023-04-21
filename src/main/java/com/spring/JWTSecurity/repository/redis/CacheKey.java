package com.spring.JWTSecurity.repository.redis;

public class CacheKey {
    public static String genUserKey(Long userId){
        return "user:" + userId;
    }
}
