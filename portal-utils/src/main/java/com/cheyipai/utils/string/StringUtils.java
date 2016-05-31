package com.cheyipai.utils.string;


import org.apache.commons.lang.CharUtils;

public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 驼峰转数据库
     *
     * @author zhanghanlin
     * @param field
     * @return
     * @since JDK 1.7
     */
    public static String camelCase2DB(String field) {
        char[] chars = field.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            if (CharUtils.isAsciiAlphaUpper(c)) {
                sb.append("_" + StringUtils.lowerCase(CharUtils.toString(c)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}