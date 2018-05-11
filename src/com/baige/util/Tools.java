package com.baige.util;

public class Tools {
    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }
    public static boolean isEquals(Object a, Object b){
        if(a == null || b == null){
            return false; //注意 都为null时还是不相等
        }
        return a.equals(b);
    }

    public static String ramdom(){
        int number = (int) (Math.random() * 900 + 100);
        return System.currentTimeMillis() + "_"+number;
    }

    public static String randomVerification(){
        int number = (int) (Math.random() * 9000 + 1000);
        return System.currentTimeMillis() + "_"+number;
    }
}
