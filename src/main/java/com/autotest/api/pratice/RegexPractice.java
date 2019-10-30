package com.autotest.api.pratice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version V1.0
 * @Title: RegexPractice
 * @Package com.autotest.api.pratice
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-21 13:01
 */
public class RegexPractice {

    public void TestRegex(){
        //定义字符串
        String str1 = "17600905781@163.com";
        String str2 = "41121117@zxl张新磊";
        //建立字符串正则
        String regex1 = "[0-9]+@[0-9a-z]+\\.[a-z]*";
        String regex2 = "[0-9]+@[a-z]+[\\u4e00-\\u9fa5]+";
        //建立真正的字符串表达式
        if(regex1.endsWith("*")){
            Pattern pattern1 = Pattern.compile(regex1);
            Matcher matcher1 = pattern1.matcher(str1);
            //进行取值
            if(matcher1.find()){
                System.out.println(matcher1.group());
            }else {
                System.out.println("没找到");
            }
        }if (regex2.endsWith("+")){
            Pattern pattern = Pattern.compile(regex2);
            Matcher matcher = pattern.matcher(str2);
            //进行取值
            if(matcher.find()){
                System.out.println(matcher.group());
            }else {
                System.out.println("没找到");
            }
        }
        //使用正则表达式进行匹配
        //Matcher matcher = pattern.matcher(str2);
    }
    public static void main(String[]args){

        RegexPractice regexPractice = new RegexPractice();
        regexPractice.TestRegex();

}
}
