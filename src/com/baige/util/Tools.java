package com.baige.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

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

    public static String formatTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(time));
    }

    /* Public Methods */
    public static byte[] stringToData(String string, String charsetName) {
        if (string != null) {
            try {
                return string.getBytes(charsetName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String dataToString(byte[] data, String charsetName) {
        if (data != null) {
            try {
                return new String(data, charsetName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public final static String DEFAULT_ENCODE = "UTF-8";

    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    public static byte[] toByte(long data) {
        byte[] buf = new byte[Long.BYTES];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((data >> (i * 8)) & 0xff);
        }
        return buf;
    }

    public static byte[] toByte(int data) {
        byte[] buf = new byte[Integer.BYTES];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((data >> (i * 8)) & 0xff);
        }
        return buf;
    }

    public static long toLong(byte buf[]) {
        long data = 0x00;
        for (int i = buf.length - 1; i >= 0; i--) {
            data <<= 8;
            data |= (buf[i] & 0xff);
        }
        return data;
    }

    public static String getServerDeviceId() {
        byte[] buf = toByte(System.currentTimeMillis());
        String timeString = Base64.getEncoder().encodeToString(buf);
        return "0" + timeString.substring(0, timeString.length() - 1)
                + String.format("%03d", Integer.valueOf((int) (Math.random() * 1000)));
    }

    public static String getMobileDeviceId() {
        byte[] buf = toByte(System.currentTimeMillis());
        String timeString = Base64.getEncoder().encodeToString(buf);
        return "1" + timeString.substring(0, timeString.length() - 1)
                + String.format("%03d", Integer.valueOf((int) (Math.random() * 1000)));
    }
}
