package com.imooc.reader.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
    public static String md5Digest(String code, Integer salt) {
        char[] charArray = code.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (charArray[i] + salt);
        }
        String str = new String(charArray);
        String md5Hex = DigestUtils.md5Hex(str);
        return md5Hex;
    }
}
