package com.lxg.exams.controller.identificationcontroller;


import com.lxg.exams.utils.Base64Util;
import com.lxg.exams.utils.WebIATWSUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @auther xiaolin
 * @creatr 2023/3/22 17:30
 */

@Controller
public class SpeechController {


    @GetMapping("/tospeech")
    public String tospeech() {
        return "thespeech";
    }


    @ResponseBody
    @PostMapping("/speechRecognition")
    public String speechToText(@RequestParam("file") MultipartFile file) throws Exception {

        System.out.println(file);

        //创建一个目录对象
        String path = System.getProperty("java.io.tmpdir");
        File dir = new File(path);
        //目录不存在，需要创建
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 创建目录名+文件名的文件对象，表示保存的图片文件
        File dest = new File(dir, "yuyin.mp3");
        // 执行保存文件，把参数file的数据写入到这个空文件中
        file.transferTo(dest);


//        WebIATWSUtils.setFile("src/main/resources/static/yuyin.mp3");
        WebIATWSUtils.setFile(path + "\\yuyin.mp3");

        WebIATWSUtils.start();
        Thread.sleep(1000);
        System.out.println("");
        return WebIATWSUtils.getResult();
    }


}
