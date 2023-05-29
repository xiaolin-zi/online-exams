package com.lxg.exams.exportExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther xiaolin
 * @creatr 2023/5/14 15:22
 */
public class TestEasyExcel {

    public static void main(String[] args) {
        //实现excel写的操作
        //1.设置写入文件夹地址和excel文件名称
        String filename = "D:\\write.xlsx";

        //2.调用easyexcel里面的方法实现写操作
        //write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(filename, DemoData.class)
                .sheet("测试列表")
                .doWrite(getData());

    }

    //创建方法返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
}