package com.autotest.api.pratice;

import com.autotest.api.util.ExcelUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.UUID;

/**
 * @version V1.0
 * @Title: ClientSocket
 * @Package com.autotest.api.pratice
 * @Description:
 * @author: zhangshao
 * @date: 2019-08-23 17:28
 */
public class ClientSocket {


    public static void main(String[] args) {
//        File originalFile = new File("E:\\learnning\\53cdd1f7c1f21.jpg");//指定要读取的图片
//        try {
//
//            File result = new File("E:\\test\\53cdd1f7c1f21.jpg");//要写入的图片
//            if (result.exists()) {//校验该文件是否已存在
//                result.delete();//删除对应的文件，从磁盘中删除
//                result = new File("E:\\test\\53cdd1f7c1f21.jpg");//只是创建了一个File对象，并没有在磁盘下创建文件
//            }
//            if (!result.exists()) {//如果文件不存在
//                result.createNewFile();//会在磁盘下创建文件，但此时大小为0K
//            }
//            FileInputStream in = new FileInputStream(originalFile);
//            FileOutputStream out = new FileOutputStream(result);// 指定要写入的图片
//            int n = 0;// 每次读取的字节长度
//            byte[] bb = new byte[1024];// 存储每次读取的内容
//            while ((n = in.read(bb)) != -1) {
//                out.write(bb, 0, n);// 将读取的内容，写入到输出流当中
//            }
//            //执行完以上后，磁盘下的该文件才完整，大小是实际大小
//            out.close();
//            in.close();

            //客户端代码
            //Socket套接字连接服务器,指定ip和端口
//            String host ="";
//            int port = 8000;
//            String filePath ="";
//            Socket socket =new Socket(host,port);
//            //通过Socket获取字节输出流,向服务器写图片
//            OutputStream os = socket.getOutputStream();
//            //创建输入流对象,读取图片数据源
//            FileInputStream fis = new FileInputStream(filePath);
//            //读取图片,使用字节输出流,将图片写到服务器,采用字节数组进行缓冲
//            int len = 0;
//            byte[] bytes = new byte[1024];
//            while ((len = fis.read(bytes)) != -1){
//                os.write(bytes,0,len);
//            }
//            socket.shutdownOutput();//文件读完时给服务器写终止序列
//            //通过Socket套接字获取字节输入流,读取服务器发回来的上传成功
//            InputStream inputStream = socket.getInputStream();
//            len = inputStream.read(bytes);
//            System.out.println("服务器返回："+new String(bytes,0,len));
//            //关闭资源
//            socket.close();
//
//        }catch(Exception e){
//        e.printStackTrace();
//    }

    }
}
