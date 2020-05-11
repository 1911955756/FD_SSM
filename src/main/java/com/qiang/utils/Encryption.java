package com.qiang.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

/**
 * @author Mr.锵
 * date 2020-04-27
 */
@Component
public class Encryption {
    // MD5加码。32位
//    public  String MD5(String inStr) {
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//            return "";
//        }
//        char[] charArray = inStr.toCharArray();
//        byte[] byteArray = new byte[charArray.length];
//
//        for (int i = 0; i < charArray.length; i++)
//            byteArray[i] = (byte) charArray[i];
//
//        byte[] md5Bytes = md5.digest(byteArray);
//
//        StringBuffer hexValue = new StringBuffer();
//
//        for (int i = 0; i < md5Bytes.length; i++) {
//            int val = ((int) md5Bytes[i]) & 0xff;
//            if (val < 16)
//                hexValue.append("0");
//            hexValue.append(Integer.toHexString(val));
//        }
//
//        return hexValue.toString();
//    }
    // 可逆的加密算法
//    加密算法
    public  String KL(String inStr) {
        int num=this.randomNumBoth(97,122);
        int b=(int)(Math.random()*10);//干扰
        int NUM=this.randomNumBoth(65,90);
        char c=(char)num;
        char C=(char)NUM;
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ c);
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ C);
        }
        String s = new String(a);
        String k=s+c+b+C;
        return k;
    }

    // 解密算法
    public  String JM(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length-3; i++) {
            a[i] = (char) (a[i] ^ a[a.length-1]);
        }
        for (int i = 0; i < a.length-3; i++) {
            a[i] = (char) (a[i] ^ a[a.length-3]);
        }
        String s = new String(a);
        String j=s.substring(0,s.length() -3);
        return j;
    }
    //生成随机数
    private Integer randomNumBoth(Integer Min,Integer Max){
        int num=Min+(int)(Math.random()*(Max+1-Min));
        return num;
    }
}
