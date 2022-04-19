package com.CS5500.springbootinfrastructure.helper;

public class CheckNull {
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }
}
