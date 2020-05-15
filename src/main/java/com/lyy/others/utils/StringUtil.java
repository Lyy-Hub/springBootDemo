/*
 * Decompiled with CFR 0_122.
 */
package com.lyy.others.utils;

import java.util.UUID;

public class StringUtil {
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = String.valueOf(uuid);
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }

    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '\u0000' && c <= '\u00ff') {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    b = new byte[]{};
                }
                int j = 0;
                while (j < b.length) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                    ++j;
                }
            }
            ++i;
        }
        return sb.toString();
    }
}

