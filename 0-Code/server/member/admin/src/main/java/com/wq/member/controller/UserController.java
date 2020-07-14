package com.wq.member.controller;


import com.wq.member.common.api.CommonResult;
import com.wq.member.dto.UserParam;
import com.wq.member.model.User;
import com.wq.member.service.UserService;
import com.wq.member.util.*;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import io.swagger.annotations.Api;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Api(value = "user")
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;


    // 其实第一次登录是返回token，后面对接口的访问是验证token
    @ResponseBody
    @PostMapping("/doLogin")
    public CommonResult login(@RequestBody UserParam userParam, HttpServletRequest request){
        // 登录测试账号 sss1 123
        System.out.println("前端页面的点击事件传过来了！user/doLogin.userParam="+userParam.toString());
        String username = userParam.getUsername();
        String password = userParam.getPassword();
        String verifyCode =  userParam.getVerifyCode();
        // TODO 验证码是否正确

        // TODO loadUserByUsername就是看账号密码是否正确，以及取出他的权限
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 根据用户详情（包含用户的账号，密码，权限)生成token
        // TODO 下面是为账号生成token
        String token = null;
        // 认证
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication); // 国际惯例  // 认证成功，存储认证信息到上下文
        token = JwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", "Bearer");
        return CommonResult.success(tokenMap);


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

    @RequestMapping(value = "/verifyCode" ,method = RequestMethod.GET)
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCodeUtil code = new VerifyCodeUtil();
        BufferedImage image = code.getImage();
        String text = code.getText();
        System.out.println("text="+text);
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerifyCodeUtil.output(image, response.getOutputStream());
    }

    @ResponseBody
    @PostMapping("/logout")
    public CommonResult<?> logout(){
        System.out.println("要退出，OK");
        return CommonResult.success("OK");
    }

}
