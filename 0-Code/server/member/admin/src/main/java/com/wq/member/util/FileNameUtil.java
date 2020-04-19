package com.wq.member.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileNameUtil {
    public static String newFileName (String fileName){
        //两位随机数加时间戳
        String random = (int)((Math.random()*9+1)*10)+""+new Date().getTime();
        String pre = fileName.substring(0, fileName.lastIndexOf("."));
        String suf = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        return pre+"_"+random+suf;
    }

    /**
     * 对上传到minio里面的文件增加时间戳，防止文件覆盖
     * @param fileName
     * @return
     */
    public static String newMinioFileName (String fileName){
        // 时间戳
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");//设置日期格式
        String date = df.format(new Date());
        String pre = fileName.substring(0, fileName.lastIndexOf("."));
        String suf = fileName.substring(fileName.lastIndexOf("."));
        return pre + "_" + date + suf;
    }

    /**
     * 文件名只要.前面的内容
     * @param fileName
     * @return
     */
    public static String preFileName(String fileName){
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

}
