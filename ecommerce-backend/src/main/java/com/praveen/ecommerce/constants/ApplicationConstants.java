package com.praveen.ecommerce.constants;

public class ApplicationConstants {
    private ApplicationConstants() {
        throw new AssertionError("Utility class cannot be instantiated.");
    }
    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE = "d7f9c4a2f0e84b3e9c1a2f6d7a4e5b8c";
    public static final String JWT_HEADER = "Authorization";
    public static final String ORDER_STATUS_CREATED = "CREATED";
    public static final String ORDER_STATUS_COMPLETED = "COMPLETED";
    public static final String ORDER_STATUS_CANCELLED = "CANCELLED";
}
