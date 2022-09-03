package com.imooc.reader.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtil {
    private String code;
    private String message;
    private Map data = new LinkedHashMap<>();

    public ResponseUtil() {
        this.code = "0";
        this.message = "success";
    }

    public ResponseUtil(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseUtil put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
