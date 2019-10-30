package com.autotest.api.pratice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * @version V1.0
 * @Title: StringSort
 * @Package com.autotest.api.pratice
 * @Description:
 * @author: zhangshao
 * @date: 2019-10-22 18:20
 */
public class StringSort {
    //创建Log对象，将创建的StringSort.class类添加到日志中后续可以打印类中的日志
    private static final Log _log= LogFactory.getLog(StringSort.class);
    public static String[] getUrlParam(String[] keys){
           for(int i=0;i<keys.length-1;i++){
               for(int j=0;j<keys.length-i-1;j++){
                   String pre = keys[j];
                   String next = keys[j+1];
                   if(isMoreThan(pre,next)){
                       String temp = pre;
                       keys[j]=next;
                       keys[j+1]=temp;
                   }
               }
           }
        return keys;
    }
    /**
     * 比较两个字符串的大小，按字母的ASCII码比较
     * @param pre
     * @param next
     * @return
     */
    private static boolean isMoreThan(String pre, String next){
        if (null == pre|| null == next || "".equals(pre) || "".equals(next)){
            _log.error("字符串比较数据不能为空");
        }
        //将字符串转化为字符串数组使用toCharArray()方法
        char[] c_pre=pre.toCharArray();
        char[] c_next=next.toCharArray();

        //比较两个数组长度的大小，得到数组的最小长度
        int minSize = Math.min(c_pre.length,c_next.length);

        //对两个字符串进行循环比较
        for(int i=0;i<minSize;i++){
            if ((int)c_pre[i]>(int)c_next[i]) {
                return  true;
            }else if((int)c_pre[i]<(int)c_next[i]){
                return false;
            }
            /*
            if(c_pre.length>c_next.length){
                return true;
            }
            */
        }

        return false;
    }
public static void main(String[] args){
    String[] keys = getUrlParam(new String[]{"fin","abc","shidema","shide","bushi"});

    for (String key : keys) {
        System.out.println(key);
    }
}

}
