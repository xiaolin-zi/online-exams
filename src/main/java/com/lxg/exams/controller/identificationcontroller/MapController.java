package com.lxg.exams.controller.identificationcontroller;


import com.lxg.exams.utils.Base64Util;
import com.lxg.exams.utils.HttpUtil;
import com.lxg.exams.utils.PicUtils;
import com.lxg.exams.utils.Token;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * @auther xiaolin
 * @creatr 2023/3/22 20:56
 */

@Controller
public class MapController {




    //获取用户上传的图进行识别
    @ResponseBody
    @PostMapping("/highPrecision")
    public String highPrecision(@RequestParam("file") MultipartFile file) throws IOException {
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";//高精度

        //压缩图片到指定120K以内,不管你的图片有多少兆,都不会超过120kb,精度还算可以,不会模糊
        byte[] bytes = PicUtils.compressPicForScale(file.getBytes(), 240);


        //file转base64
//            String myUrl = Base64Util.encode(file.getBytes());
        String myUrl = Base64Util.encode(bytes);

        try {

//            System.out.println(myUrl);


            //处理头信息
//            String imgStr = myUrl.substring(23, myUrl.length());


//            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String imgParam = URLEncoder.encode(myUrl, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = Token.getToken("lWwaEtuBTqFVt0P6KyFC9YCG","zxSxW17DDAK7l2u1PLGSiTgsMjyxa5Zb");

            String result = HttpUtil.post(url, accessToken, param);


            JSONObject json = new JSONObject(result);//将字符串重新转JSON
            JSONArray str = json.getJSONArray("words_result");//识别都内容都放在键为words_result的数组中

            String result0 = "";

            for(int i = 0;i<str.length();i++) {//遍历
                result0+=((JSONObject)str.get(i)).get("words");//必须进行强转，因为str1.get(i)返回的是Object对象，是最顶层的父类。get("words")只返回value值
            }
            return result0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @PostMapping("/basic")
    public String basic(@RequestParam("file") MultipartFile file) throws IOException {

            String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";//基础图识别文字

        //压缩图片到指定120K以内,不管你的图片有多少兆,都不会超过120kb,精度还算可以,不会模糊
        byte[] bytes = PicUtils.compressPicForScale(file.getBytes(), 120);


        //file转base64
//            String myUrl = Base64Util.encode(file.getBytes());
        String myUrl = Base64Util.encode(bytes);

        try {

//            System.out.println(myUrl);


            //处理头信息
//            String imgStr = myUrl.substring(23, myUrl.length());


//            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String imgParam = URLEncoder.encode(myUrl, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = Token.getToken("lWwaEtuBTqFVt0P6KyFC9YCG","zxSxW17DDAK7l2u1PLGSiTgsMjyxa5Zb");

            String result = HttpUtil.post(url, accessToken, param);


            JSONObject json = new JSONObject(result);//将字符串重新转JSON
            JSONArray str = json.getJSONArray("words_result");//识别都内容都放在键为words_result的数组中

            String result0 = "";

            for(int i = 0;i<str.length();i++) {//遍历
                result0+=((JSONObject)str.get(i)).get("words")+"\n";//必须进行强转，因为str1.get(i)返回的是Object对象，是最顶层的父类。get("words")只返回value值
            }
            return result0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
