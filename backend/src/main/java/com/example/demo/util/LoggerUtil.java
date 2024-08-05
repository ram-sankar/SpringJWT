package com.example.demo.util;

public class LoggerUtil {
    public static void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }

    public static void info(String message) {
        System.out.println(message);
    }

    public static void warn(String message) {
        System.out.println("[WARN] " + message);
    }

    public static void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
