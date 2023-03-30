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
 * @creatr 2023/3/22 22:05
 */

@Controller
public class StudyMapController {

    String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/doc_analysis"; //教育试卷



    /**
     * 文档版面分析与识别
     */
        /**
         * 重要提示代码中所需工具类
         * FileUtil,Base64Util,HttpUtil,GsonUtils请从
         * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
         * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
         * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
         * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
         * 下载
         */
    @ResponseBody
    @PostMapping("/eduMap")
    public  String docAnalysis2(@RequestParam("file") MultipartFile file) throws IOException {


        //压缩图片到指定120K以内,不管你的图片有多少兆,都不会超过120kb,精度还算可以,不会模糊
        byte[] bytes = PicUtils.compressPicForScale(file.getBytes(), 120);


            //file转base64
//            String myUrl = Base64Util.encode(file.getBytes());
            String myUrl = Base64Util.encode(bytes);

        try {
            // 本地文件路径
//                String filePath = "[本地文件路径]";
//                byte[] imgData = FileUtil.readFileByBytes(filePath);

            String imgParam = URLEncoder.encode(myUrl, "UTF-8");

            String param = "language_type=" + "CHN_ENG" + "&result_type=" + "big" + "&image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = Token.getToken("lWwaEtuBTqFVt0P6KyFC9YCG","zxSxW17DDAK7l2u1PLGSiTgsMjyxa5Zb");
            String result = HttpUtil.post(url, accessToken, param);


            JSONObject json = new JSONObject(result);//将字符串重新转JSON
            JSONArray str = json.getJSONArray("results");//识别都内容都放在键为result的数组中

            String result0 = "";

            for(int i = 0;i<str.length();i++) {//遍历
                JSONObject words = (JSONObject) ((JSONObject) str.get(i)).get("words");
                result0+=words.get("word")+"\n";
            }
            return result0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
