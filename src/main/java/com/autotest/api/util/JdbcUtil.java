package com.autotest.api.util;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @version V1.0
 * @Title: JdbcUtil
 * @Package com.autotest.api.util
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-14 20:55
 */
public class JdbcUtil {
    private  static String url = "";
    private static String username = "root";
    private static String passwd = "root";
    private  static String sql = "";

    public static void handler(){
        try {
            //得到驱动
            Class.forName("com.mysql.jdbc.Driver");
            //自动调用驱动进行连接
            Connection connection  = DriverManager.getConnection(url,username,passwd);
            //调用方法预编译存储sql
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //执行sql查询
            ResultSet resultSet = preparedStatement.executeQuery();
            //对结果集数据做处理，得到需要能够操作元数据结果集
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //得到总列数
            int columnTotalCount = resultSetMetaData.getColumnCount();
            while(resultSet.next()){
                for(int i=1;i<=columnTotalCount;i++){
                    String columnName = resultSetMetaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(columnName);
                    //将数据库中的数据给实体类做映射
                   Field field =  Autolog.class.getDeclaredField(columnName);
                   field.setAccessible(true);
                   //将实体类中的字段设置为具体的值
                   field.set(Autolog.class,columnValue);
                }
            }
            //执行sql增删改
            int number = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }
}
