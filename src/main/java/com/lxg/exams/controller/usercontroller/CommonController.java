package com.lxg.exams.controller.usercontroller;

import com.lxg.exams.utils.FileUtil;
import com.lxg.exams.utils.PicUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;


/**
 * Author: xuesinuan
 */

@RestController
@RequestMapping("/common")
public class CommonController {
    //固定基础路径
    @Value("${image.path}")
    private String basePath;


    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {

        //压缩图片到指定120K以内,不管你的图片有多少兆,都不会超过120kb,精度还算可以,不会模糊
        byte[] bytes = PicUtils.compressPicForScale(file.getBytes(), 120);


        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取原始文件名的后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        //为了防止重名发生覆盖，采取随机生成文件名的方式UUID
        //创建一个目录对象
//        File dir = new File(basePath);
        //目录不存在，需要创建
//        if (!dir.exists()){
//            dir.mkdirs();
//        }
        String fileName = UUID.randomUUID().toString() + substring;
        // 创建目录名+文件名的文件对象，表示保存的图片文件
//        File dest = new File(dir, fileName);
        // 执行保存文件，把参数file的数据写入到这个空文件中
//        file.transferTo(dest);

        FileUtil.byteArrayToFile(bytes, basePath + fileName);//直接把压缩后的字节数组转成文件存起来了


        return fileName;
    }


    /**
     * 文件下载
     * @param name
     * @param response
     */
    //输出流需要通过response来获得
   /* @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {
        //输入流，通过输入流读取文件内容
        FileInputStream fileInputStream = new FileInputStream(basePath+name);

        //response响应对象获取输出流，通过输出流将文件写回浏览器，在浏览器展示图片
        ServletOutputStream outputStream = response.getOutputStream();

        //设置响应的文件类型(图片文件)
        response.setContentType("image/jpeg");
        int len=0;
        byte[] bytes = new byte[1024];
        while((len=fileInputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
            outputStream.flush();//刷新
        }
        //关闭资源
        outputStream.close();
        fileInputStream.close();
    }*/
}
