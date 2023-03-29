package com.lxg.exams.controller.identificationcontroller;


import com.lxg.exams.utils.Base64Util;
import com.lxg.exams.utils.WebIATWSUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("/speechRecognition")
    public String speechToText(String myData) throws Exception {

        System.out.println(myData);

//        String base64 = myData.substring(23, myData.length());

//        Base64Util.base64ToFile(base64,"yuyin.mp3");
        Base64Util.base64ToFile(myData, "yuyin.mp3");

        String path = System.getProperty("java.io.tmpdir");

//        WebIATWSUtils.setFile("src/main/resources/static/yuyin.mp3");
        WebIATWSUtils.setFile(path + "\\yuyin.mp3");

        WebIATWSUtils.start();
        Thread.sleep(1000);
        System.out.println("");
        return WebIATWSUtils.getResult();
    }


}
