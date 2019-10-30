package com.autotest.api.pratice;

import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * @version V1.0
 * @Title: ServerSocket
 * @Package com.autotest.api.pratice
 * @Description:
 * @author: zhangshao
 * @date: 2019-09-03 20:03
 */
public class ServerSocket {

    public static void main(){
//        //服务端代码
//        //ServerSocket套接字对象,监听8000
//        ServerSocket serverSocket = new ServerSocket(8000);
//        //获取客户端连接对象
//        Socket socket = serverSocket.accept();
//        //客户端连接对象获取字节输入流,读取客户端发送图片
//        InputStream in = socket.getInputStream();
//        //创建File对象,绑定上传文件夹。判断文件夹存在,不存在则创建文件夹
//        File upload = new File("E:\\upload");
//        if (!upload.exists()){
//            upload.mkdirs();
//        }
//        //创建字节输出流,数据目的File对象所在文件夹
//        String fileName = "pic"+System.currentTimeMillis()+ new Random().nextInt(9999)+".jpg";
//        FileOutputStream fos = new FileOutputStream(upload+File.separator+fileName);
//        //字节流读取图片,字节流将图片写入到目的文件中
//        byte[] bytes = new byte[1024];
//        int len = 0;
//        while((len = in.read(bytes))!=-1){     //读的是客户端发过来图片的字节数组,只有读文件能读到-1,所以这里永远读不到-1，read()会一直阻塞。（需要客户端发送结束标志）
//            fos.write(bytes,0,len);
//        }
//        //将上传成功回写给客户端
//        socket.getOutputStream().write(("上传到服务器"+socket.getLocalAddress().toString()+"成功").getBytes());
//        //关闭资源
//        fos.close();
//        socket.close();
//

    }


}
