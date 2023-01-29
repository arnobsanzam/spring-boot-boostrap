package com.example.springbootboostrap.route;

public class AppRoute {
    public static final  String API = "/api";
    public static class Auth {
        public static final String LOGIN = "/auth/login";
        public static final String LOGOUT = "/auth/logout";
        public static final String GENERATE_TOKEN = "/auth/generate-token";
    }

    public static class User {
        public static final String CREATE_USER = "/users";
        public static final String GET_USER_BY_ID = "/users/{id}";
        public static final String GET_ALL_USER = "/users";
    }
}
