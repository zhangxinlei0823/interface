package com.autotest.api.pratice;

import org.apache.commons.codec.binary.Base64;

/**
 * @version V1.0
 * @Title: Base64
 * @Package com.autotest.api.pratice
 * @Description:
 * @author: zhangshao
 * @date: 2019-09-27 18:48
 */

public class BasePractice {

public static void main(String[] args){
    String stringBase = "17600905781";

    //base64位加密
    byte[] byteArr = Base64.encodeBase64(stringBase.getBytes());

    String string1 =new String(byteArr);

    System.out.println(string1);

    //解密64位字符串
    byte[] byteArr1 = Base64.decodeBase64(byteArr);

    String string2 = new String(byteArr1);

    System.out.println(string2);

}
}
