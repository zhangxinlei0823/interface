package com.autotest.api.pratice;

/**
 * @version V1.0
 * @Title: RandomInt
 * @Package com.autotest.api.pratice
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-24 10:50
 */
public class RandomInt {
    public static void main(String[] args){
        //int max=100,min=1;
        double ran = Math.random();
        System.out.println(ran);

        int max=100,min=1;
        int ran2 = (int) (Math.random()*(max-min)+min);
        System.out.println(ran2);

        int i1 = (int)(Math.random() * 10) + 10;
        System.out.println(i1);
        int i2 = (int)(Math.random() * 51);
        System.out.println(i2);
        int i3= (int)(Math.random() * 2);
        System.out.println(i3);

        //使用valueOf方法将整形数据转换为字符串类型且输出字符串
        String a = String.valueOf((int)((Math.random()*9+1)*100000000));
        System.out.println(a);
    }
}
