package com.autotest.api.pratice;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @version V1.0
 * @Title: JSONPathTest
 * @Package com.autotest.api.pratice
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-27 11:00
 */
public class JSONPathTest {
    public static void main(String[]args){

        //定义字符串json
        String str = "{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings\",\"price\":9.66},{\"category\":\"fiction\",\"author\":\"Evelyn\",\"title\":\"Sword\",\"price\":19.88}],\"bicycle\":[{\"price\":20.66,\"color\":\"red\"}]}}";

        //step1 将字符串json转换为Json
        JSONObject json = JSON.parseObject(str);

        //step2 JSONPath.eval(),获取Json数据

        System.out.println("book数目为:" + JSONPath.eval(json, "$.store.book.size()"));
        System.out.println("第一本书title为:" + JSONPath.eval(json, "$.store.book[0].title"));
        System.out.println("第二本书title为:" + JSONPath.eval(json, "$.store.book[1].title"));

        System.out.println("price大于10元book为:" + JSONPath.eval(json, "$.store.book[price>10]"));
        System.out.println("price大于10元book的title为:" + JSONPath.eval(json, "$.store.book[price>10].title"));
        System.out.println("price在8到12之间book为:" + JSONPath.eval(json, "$.store.book[price between 8 and 12]"));

        System.out.println("title以S开头的book为:" + JSONPath.eval(json, "$.store.book[title like 'S%']"));
        System.out.println("title在Sword和Sayings范围:" + JSONPath.eval(json, "$.store.book[title in ('Sword','Sayings')]"));


        System.out.println("bicycle所有属性值:" + JSONPath.eval(json, "$.store.bicycle[0].*"));
        System.out.println("bicycle的price和color属性值:" + JSONPath.eval(json, "$.store.bicycle[0]['price','color']"));


        //step2 JSONPath.set(),修改Json属性数据
        JSONPath.set(json,"$.store.book[0].title","new book");
        System.out.println("第一本书title为:" + JSONPath.eval(json, "$.store.book[0].title"));


        //step2 JSONPath.arrayAdd(),添加新数据
        JSONPath.arrayAdd(json, "$.store.bicycle", "{'value':13}","{'test':13}");
        System.out.println("bicycle所有属性值:" + JSONPath.eval(json, "$.store.bicycle"));


        //step2 JSONPath.containsValue(),判断是否包含指定值
        System.out.println("判断是否含{'value':13}:" + JSONPath.containsValue(json, "$.store.bicycle", "{'value':13}"));


        //step2 JSONPath.remove(),判断是否包含指定值
        System.out.println("删除{'value':13}:" + JSONPath.remove(json, "$.store.bicycle[1]"));
        System.out.println("bicycle所有属性值:" + JSONPath.eval(json, "$.store.bicycle"));


        //测试
        String str2 = "{\"status\":1,\"info\":\"\",\"data\":{\"id\":\"1050187\",\"lastlogin\":1540882402,\"logins\":[\"exp\",\"logins+1\"],\"lastip\":\"111.203.168.2\",\"checktype\":1}}";

        JSONObject json2 = JSON.parseObject(str2);
        System.out.println("str2长度为:" + JSONPath.eval(json2, "$.size()"));
        System.out.println("status为:" + JSONPath.eval(json2, "$.status"));
        System.out.println("info为:" + JSONPath.eval(json2, "$.info"));

        System.out.println("info为:" + JSONPath.eval(json2, "$.data.id"));
        System.out.println("info为:" + JSONPath.eval(json2, "$.data.logins[0]"));
        System.out.println("str2长度为:" + JSONPath.read(str2, "$.size()"));


    }
}
