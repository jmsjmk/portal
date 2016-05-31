package com.cheyipai.utils.encrypt;

/**
 * 加密工具类枚举
 * Created by andy on 16/4/26.
 */
public enum Method {
    MD5("MD5"),
    SHA("SHA");

    private String name;

    Method(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
