package com.autotest.api.util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
/**
 * @version V1.0
 * @Title: ParseJsonToMapUtil
 * @Package com.autotest.api.util
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-13 15:05
 */

public class ParseJsonToMapUtil {

    //每个接口的返回值都会转为Map存储
    private  Map<String, String> iResponJsonToMap = new HashMap<String, String>();

    //存储接口名和接口Map结果
    private static Map<String,Map<String,String>> iNameAndIMap = new HashMap<String,Map<String,String>>();

    /**
     * @Title: getINameAndIMap
     * @Description: 得到iNameAndIMap,里面存储接口名和接口Map结果
     * @param: none
     * @return: Map<String,Map<String,String>>
     * @throws
     */
    public static Map<String,Map<String,String>> getINameAndIMap(){
        return iNameAndIMap;
    }

    /**
     * @Title: setINameAndIMap
     * @Description: 存储接口名和接口Map结果
     * @param: reqInterface
     * @param: responseJson
     * @return: void
     * @throws
     */
    public void setINameAndIMap(String reqInterface,String responseJson){
        iNameAndIMap.put(reqInterface, parseJsonToMap(responseJson));
    }



    /**
     * @Title: isJsonString
     * @Description: 判断入参是否为json格式的字符串类型
     * @param: @param jsonString
     * @param: @return
     * @return: boolean
     * @throws
     */
    public  boolean isJsonString(String jsonString) {
        boolean flag = true;
        try {
            JSON.parseObject(jsonString);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * @Title: isJsonArray
     * @Description: 判断入参是否为JSONArray类型
     * @param: String jsonArrayString
     * @return: boolean
     * @throws
     */
    public  boolean isJsonArray(String jsonArrayString) {
        boolean flag = true;
        try {
            JSONArray.parseArray(jsonArrayString);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * @Title: isString
     * @Description: 非JsonObject,非JsonArray类型
     * @param: String  str
     * @return: boolean
     * @throws
     */
    public  boolean isString(String str) {
        return !isJsonString(str) & !isJsonArray(str);
    }


    /**
     * @Title: testParseJsonArray
     * @Description: 解析JsonArray
     * @param: String sonArrayString
     * @return: void
     * @throws
     */
    public  void parseJsonArray(String jsonArrayString){
        try{
            JSONArray jsonArray = JSONArray.parseArray(jsonArrayString);
            for(Object json:jsonArray){
                String jsonItem = json.toString();
                if(isJsonString(jsonItem)){
                    parseJsonToMap(jsonItem);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Title: parseJsonToMap
     * @Description: 解析String类型的json串即每个接口的返回值都会转为Map
     * @param: String responseJson
     * @return: Map<String,String>
     * @throws
     */
    public Map<String, String> parseJsonToMap(String responseJson){

        try{
            JSONObject json = JSON.parseObject(responseJson);
            for(Entry<String, Object> entry:json.entrySet()){

                //注意-只能得到json第1层key,value(value有可能是:基本数据类型,类集,JSONObject,JSONArray)
                String key = entry.getKey();
                String value = String.valueOf(entry.getValue());

                //判断value值为""/null->若是则map把当前key的值改为空
                if(value.length()==0 || value.equals("null")){
                    iResponJsonToMap.put(key, "null");
                    continue;
                }

                //判断value值是否为json串
                if (isJsonString(value)){
                    iResponJsonToMap.put(key,value);
                    parseJsonToMap(value);
                }

                //判断value值是否为json数组
                if (isJsonArray(value)) {
                    iResponJsonToMap.put(key, value);
                    parseJsonArray(value);
                }

                //判断value值是否为字符串
                if (isString(value)){
                    if(iResponJsonToMap.containsKey(key)){
                        iResponJsonToMap.put(key,iResponJsonToMap.get(key) +"," + value);
                    }else{
                        iResponJsonToMap.put(key, value);}
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return iResponJsonToMap;
    }

    public static void main(String[] args) {

        //testcase1
//		ParseJsonToMapUtil parseJsonToMapUtil = new ParseJsonToMapUtil();
//		Map<String, String> tmp = parseJsonToMapUtil.parseJsonToMap("{\"status\":1,\"info\":\"\",\"data\":{\"id\":\"1048894\",\"lastlogin\":1527137945,\"logins\":[\"exp\",\"logins+1\"],\"lastip\":\"111.203.168.2\",\"checktype\":1}}");
//		System.out.println(" Map内当前： " + tmp);
//		System.out.println(" json parse value =  " + tmp.get("status"));

        //testcase2
//		ParseJsonToMapUtil parseJsonToMapUtil = new ParseJsonToMapUtil();
//		Map<String, String> tmp = parseJsonToMapUtil.parseJsonToMap("{\"a\":\"b\",\"c\":[{\"key1\":{\"key4\":{\"key5\":{\"key6\":[{\"key7\":[{\"key8\":0,\"key9\":[{\"name\":\"zhangsan\",\"score\":99,\"sex\":1},{\"name\":\"lisi\",\"score\":100},{\"name\":\"wangwu\",\"score\":30}]}]}]}}}},{\"key2\":\"value2\"},{\"key3\":\"value3\"}]}");
//		System.out.println(" Map内存储为： " + tmp);
//		System.out.println(" json parse value =  " + tmp.get("key8"));

    }

}