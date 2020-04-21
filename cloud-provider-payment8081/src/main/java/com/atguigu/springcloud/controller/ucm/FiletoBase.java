package com.atguigu.springcloud.controller.ucm;

import cn.hutool.core.io.resource.ResourceUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

import java.io.*;

/**
 * Created by Administrator on 2020/3/17 0017.
 */
public class FiletoBase {
    /**
     * base64字符串转换成图片
     * @param is      base64字符串
     * @param imgFilePath  图片存放路径
     * @return
     *
     * @author sunhailong
     * @dateTime 2018-11-19 14:42:17
     */
    public static void base64ToImage(InputStream is, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte b = (byte)is.read();
            while(b != ',') {
                b = (byte)is.read();
            }
            // Base64解码
            byte[] bs = decoder.decodeBuffer(is);
            for (int i = 0; i < bs.length; ++i) {
                if (bs[i] < 0) {// 调整异常数据
                    bs[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bs);
            out.flush();
            out.close();

        } catch (Exception e) {
           // log.error("fail to base64ToImage:" + e);
        }

    }


    //将base64图片转为图片file格式并存储
    private static String base64ToImage() throws Exception {
        FileInputStream InputStream  = new FileInputStream("C:/Users/Administrator/Desktop/222.png");
//2. 返回这个输入流中可以被读的剩下的bytes字节的估计值；
        int size =  InputStream.available() ;
        //3. 根据输入流中的字节数创建byte数组；
        byte[] array = new byte[size];
        //4.把数据读取到数组中；
        InputStream.read( array ) ;
        //5.根据获取到的Byte数组新建一个字符串，然后输出；
        // System.out.println(new String(array));

        FileOutputStream outputStream  = new FileOutputStream("C:/Users/Administrator/Desktop/222221.png");
        outputStream.write(array);
         //将读取的输入流 写入到指定的路径下

        //字符流
         FileWriter writer = new FileWriter("C:/Users/Administrator/Desktop/222.png");
        BufferedWriter bufferedWriter=new BufferedWriter(writer);
        BufferedReader bufferedReader=new BufferedReader(new FileReader("FileReader"));
        String len;
        while ((len=bufferedReader.readLine())!=null) {
            bufferedWriter.write(len+"\n");
        }
        return null;
    }

    public static void main(String[] args)throws Exception {
        base64ToImage();
    }
}
