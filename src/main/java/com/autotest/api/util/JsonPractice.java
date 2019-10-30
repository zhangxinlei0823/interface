package com.autotest.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @version V1.0
 * @Title: JsonPractice
 * @Package com.autotest.api.util
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-15 23:45
 */
public class JsonPractice {

    public static void main(String[] args){

        //字符创类型的json
        String str = "{'name':[{'store':80},{'class':2}]}";
        //将字符串类型的json转换为jsonObject对象
        JSONObject jsonObject = JSON.parseObject(str);
        //jsonObject自动上转到Object类型的对象，通过路径进行解析jsonObject,得到真正的JSON值
        System.out.println(JSONPath.eval(jsonObject,"$.name[0].store"));
    }
}
