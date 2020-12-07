package com.isharefox.share.common.util;

/**
 * item id 转换
 */
public class ItemIdUtil {

    public static String enCode(Integer itemId) {
        return Integer.toHexString(itemId);
    }

    public static Integer deCode(String hexString) {
        return Integer.parseInt(hexString, 16);
    }
}
