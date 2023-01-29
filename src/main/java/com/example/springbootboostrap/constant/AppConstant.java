package com.example.springbootboostrap.constant;

public class AppConstant {

    public class ExceptionMessage {
        public static String USERNAME_ALREADY_EXISTS = "Username already exists.";
        public static String USER_IS_TEMPORARILY_DISABLED = "User is temporarily disabled.";
        public static String INVALID_CREDENTIALS = "Invalid credentials";
        public static String USER_IS_BLOCKED_DUE_TO_TOO_MANY_FAILED_ATTEMPTS = "User is blocked due to too many failed attempts.";
        public static String USER_DOES_NOT_EXIST_BY_USERNAME = "User does not exist by username %s";
        public static String BEARER_TOKEN_NOT_FOUND = "Bearer token not found";
    }

    public class Security {
        public static String[] OPEN_URLS = {"/api/auth/**"};
    }
}
