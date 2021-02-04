package com.jwt_spring.helpers;

public class SecurityConstantes {
    public static final String SECRET = "spring_jwt_angular.net";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
