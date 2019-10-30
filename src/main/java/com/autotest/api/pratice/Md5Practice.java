package com.autotest.api.pratice;

import java.security.MessageDigest;

/**
 * @version V1.0
 * @Title: Md5Practice
 * @Package com.autotest.api.pratice
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-21 13:02
 */
public class Md5Practice {
    public static void main(String[] args) throws  Exception{
        //得到md5加密字符创对象
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //定义字符创
        String pwd ="123456";
        //得到字节数组
        byte[] b1 = pwd.getBytes();
        //对字节数组进行加密
        byte[] b2 = md5.digest(b1);
        //输出加密内容
//        for(byte b:b2){
//            System.out.print((char)b);
//        }
        System.out.println(b1.toString());
        System.out.println(b2.toString());
    }
}
