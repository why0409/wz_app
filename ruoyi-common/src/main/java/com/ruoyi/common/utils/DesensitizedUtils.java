package com.ruoyi.common.utils;

/**
 * @Author: LJW
 * @Date: 2024/6/20 0020 18:05
 */
public class DesensitizedUtils {

    public static String desensitizeName(String name) {
        int nameLength = name.length();

        if (nameLength == 2) {
            return name.charAt(0) + "*";
        } else if (nameLength > 2) {
            StringBuilder desensitizedName = new StringBuilder();
            desensitizedName.append(name.charAt(0));
            for (int i = 1; i < nameLength - 1; i++) {
                desensitizedName.append("*");
            }
            desensitizedName.append(name.charAt(nameLength - 1));
            return desensitizedName.toString();
        }

        // 其他情况（比如名字长度为1，或者空字符串等）保持原样
        return name;
    }
}
