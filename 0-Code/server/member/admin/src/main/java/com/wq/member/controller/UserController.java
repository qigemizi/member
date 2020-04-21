package com.wq.member.controller;


import com.wq.member.common.api.CommonResult;
import com.wq.member.dto.UserParam;
import com.wq.member.model.User;
import com.wq.member.service.UserService;
import com.wq.member.util.FileNameUtil;
import com.wq.member.util.MinioUtil;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import io.swagger.annotations.Api;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Api(value = "user")
@Controller
@RequestMapping(value = "/user")
public class UserController {

   @Autowired
   private UserService userService;


    @ResponseBody
    @PostMapping("/doLogin")
    public String login(@RequestBody String s){
        System.out.println("前端页面的点击事件传过来了！");
        System.out.println("s="+s);
        return "200";
    }

    /**
     * 注册管理员
     * @param userParam
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody UserParam userParam){

        System.out.println("前端页面的点击事件传过来了！");
        System.out.println("userParam="+userParam.toString());
        //
        User user = new User();
        user.setUsername(userParam.getUsername());
        user.setPassword(userParam.getPassword());
        userService.register(user);
        return CommonResult.success("200");
    }

    /**
     * 下载管理员Excel
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/userExcel")
    public CommonResult<?> userExcel(){

        userService.userExcel();
        return CommonResult.success("200");
    }


//    @ResponseBody
//    @PostMapping("/signOut")
//    public MyResult signOutController(@RequestBody User user){
//        System.out.println("signOut前端页面的点击事件传过来了！");
//        System.out.println("signOut_user="+user.toString());
//        return userService.signOut(user);
//    }

    @ResponseBody
    @PostMapping("/listObjects")
    public CommonResult<?> listObjects(){
        String bucketName = "download";
        // 存放文件名称
        Map<Integer,String> map = new HashMap<>();
        int i=0;
        try {
            MinioClient minioClient =MinioUtil.getMinioClient();
            // 检查 bucket 是否存在。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                // 列出对象
                Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName);
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName()); //Mon Mar 16 09:56:02 CST 2020, 6, 12345.xlsx
                    String preFileName = FileNameUtil.preFileName(item.objectName());
                    map.put(i++,preFileName);
                }
            } else {
                System.out.println("mybucket does not exist");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return CommonResult.success(map);
    }



    @ResponseBody
    // @PostMapping("/upload")
    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    public String uploadController(
            int categoryId,
            String tag,
            String description,
            String prefix,
            @RequestParam("file") MultipartFile multipartFile
    ) {
        System.out.println("前端页面的点击事件传过来了！");
        System.out.println("categoryId="+categoryId + tag + description + prefix + multipartFile);
        try {
            System.out.println("multipartFile.getSize()="+multipartFile.getSize());
            InputStream is = multipartFile.getInputStream();
            String s=multipartFile.getContentType();
            multipartFile.getName();
            System.out.println("s=" + s + " ,multipartFile.getSize()="+multipartFile.getSize());
            MinioClient minioClient = new MinioClient("http://192.168.10.77:9000", "minioadmin", "minioadmin");
            minioClient.listBuckets();
            minioClient.putObject("upload", "123.xlsx",is,"application/json;charset=UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "200";
    }


    /**
     * http://localhost:8080/file/download?fileName=新建文本文档.txt
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/download" ,method = RequestMethod.POST)
    public Object downloadFile(HttpServletResponse response, HttpServletRequest request){
        System.out.println("进入downloadFile方法");
        OutputStream os = null;
        InputStream is= null;
        String fileName="你好吗.txt";
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            //读取文件
            String path="/Users/wq/WQ/你好吗.txt";
            File f = new File(path);
            is = new FileInputStream(f);
            if (is == null) {
                // logger.error("下载附件失败，请检查文件“" + fileName + "”是否存在");
                return "下载附件失败，请检查文件“" + fileName + "”是否存在";
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return "下载附件失败,error:"+e.getMessage();
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                // logger.error(ExceptionUtils.getFullStackTrace(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                // logger.error(ExceptionUtils.getFullStackTrace(e));
            }
        }
        return null;
    }

}
