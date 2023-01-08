package com.dvaren.utils;

/**
 * @description:
 * @author: iiu
 * @date: 2021/6/7 23:33
 */
public class TextUtil {
    public static boolean isEmpty(String s){
        return s == null || s.isEmpty();
    }

    public static String generateNickName() {
        return "user_" + (int) (Math.random()*10000000);
    }

    public static String generateAvatar() {
        return "https://iiu.oss-cn-chengdu.aliyuncs.com/blog-v2/default-avatar/" + (int)(Math.random()*5+1) + ".png";
    }
    /**
     * 用户名验证
     *  @param name
     *  @return
     */
    public static boolean checkName(String name) {
        String regExp = "^[a-zA-Z0-9_-]{4,16}$";
        return name.matches(regExp);
    }
    /**
     * 密码验证
     *  @param pwd
     *  @return
     */
    public static boolean checkPwd(String pwd) {
        String regExp = "^[\\w_]{6,20}$";
        return pwd.matches(regExp);
    }
}
