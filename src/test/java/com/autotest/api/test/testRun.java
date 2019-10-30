package com.autotest.api.test;

import com.autotest.api.util.ExcelUtil;
import com.autotest.api.util.HttpRequestUtil;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @version V1.0
 * @Title: testRun
 * @Package com.autotest.api.test
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-12 15:57
 */
public class testRun {
    private String filePath = null;

    @Parameters({"excelTestDataPathParam"})

    @BeforeTest
    public void getExcelParameters(String excelParameters){
        this.filePath = excelParameters;
    }

    @Test(dataProvider = "testcase")
    public void httpReq(String id,String is_exec,String req_type,String testcase,String req_host,String req_interface,String req_data,String req_expected,String is_dep,String dep_key){

        //初始化
        String actResult = "";
        String reqUrl = req_host + req_interface;

        //打印日志
        Reporter.log("请求接口:"+reqUrl);
        Reporter.log("请求参数:"+req_data);
        Reporter.log("接口预期值:"+req_expected);

        //向服务器发送请求并得到服务器返回值
        if("YES".equals(is_exec)) {
            if ("GET".equals(req_type)) {
                actResult = HttpRequestUtil.sendGet(reqUrl,req_data);
            } else {
                actResult = HttpRequestUtil.sendPost(reqUrl,req_data);
            }
        }else{
            System.out.println("此接口不执行");
        }
        //打印接口发送请求后服务器的返回值
        Reporter.log("服务器返回值:"+actResult);
    }

    @DataProvider(name = "testcase")
    public Object[][] testcase(){
        Object[][] caseData = null;
        try{
            ExcelUtil excelUtil = new ExcelUtil(filePath);
            caseData = excelUtil.ExcelDataToArray(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return caseData;
    }
}
