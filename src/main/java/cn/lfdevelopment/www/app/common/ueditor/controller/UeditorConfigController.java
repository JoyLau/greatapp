/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.ueditor.controller;

import cn.lfdevelopment.www.app.common.ueditor.service.UeditorConfigService;
import cn.lfdevelopment.www.common.util.FileUtils;
import cn.lfdevelopment.www.sys.redis.RedisUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiuFa on 2016/10/27.
 * cn.lfdevelopment.www.common.ueditor
 * DevelopmentApp
 */
@Controller
public class UeditorConfigController {

    @Autowired
    private UeditorConfigService ueditorConfigService;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${ueditor.fileupload.image-path}")
    private String ueditorImagePath;
    /**
     * @author LiuFa
     * @param [action, request, response, model]
     * @return java.lang.String
     * @description UEditor 请求统一处理
     */
    @RequestMapping("/common/ueditorConfig")
    @ResponseBody
    public String ueditorConfig(String action,HttpServletRequest request,HttpServletResponse response,Model model){
        if("config".equals(action)){
            return this.config();
        }else if("uploadimage".equals(action)){
            return this.uploadImage(request,response,model);
        }
        return null;
    }


    /**
     * 获取UEditor的全局配置
     * @return
     */
    private String config(){
        String config = "";
        try {
            config = (String) redisUtils.get("UEditorConfig");
        } catch (Exception e) {
            config = ueditorConfigService.getGloableConfig();
        }
        return config;
    }

    /**
     * @author LiuFa
     * @param
     * @return
     * @description  上传图片
     */
    private String uploadImage(HttpServletRequest request,HttpServletResponse response,Model model){
        InputStream stream = null;
        FileOutputStream os = null;
        String originalFileName;
        String savedFileName;
        try {

            File uploadedFile = null;
            // 获取文件流
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            MultipartFile multipartFile = null;
            for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
                multipartFile = set.getValue();// 文件名
            }
            originalFileName = multipartFile.getOriginalFilename();
            stream = multipartFile.getInputStream();
            String saveFolderPath = ueditorImagePath;
            // 创建文件夹
            File folder = new File(saveFolderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            savedFileName = System.currentTimeMillis() + "."
                    + FileUtils.getExtensionName(originalFileName);
            String filePath = saveFolderPath + savedFileName;
            uploadedFile = new File(filePath);
            os = new FileOutputStream(uploadedFile);
            byte buf[] = new byte[1024];
            int length = 0;
            while ((length = stream.read(buf)) > 0) {
                os.write(buf, 0, length);
            }
        } catch (Exception e) {
            model.addAttribute("state", "Failed");
            return JSON.toJSONString(model);
        } finally {
            // 资源关闭异常处理
            try {
                if (os != null) {
                    // 关闭流
                    os.flush();
                    os.close();
                }
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception e) {
                //
            }
        }
        model.addAttribute("state", "SUCCESS");
        model.addAttribute("url", request.getContextPath() + "/common/getUeditorImage?fileName="
                + savedFileName);
        model.addAttribute("title", originalFileName);
        model.addAttribute("original", originalFileName);
        return JSON.toJSONString(model);
    }




    @RequestMapping("/common/getUeditorImage")
    public void getUeditorImage(HttpServletRequest request,
                                HttpServletResponse response, String fileName) throws Exception {
        String imagePath = ueditorImagePath + fileName;
        FileUtils.outImage(request, response, imagePath);
    }



    /**
     * 去除字符串中的空格、回车、换行符、制表符
     * @param str
     * @return
     */
    private String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 去除注释《适用于/*和*斜杠的简单注释》
     * @param code
     * @return
     */
    private String replaceComment(String code){
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < code.length(); i++) {
            if (cnt == 0) {
                if (i + 1 < code.length() && code.charAt(i) == '/' && code.charAt(i + 1) == '*') {
                    cnt++;
                    i++;
                    continue;
                }
            } else {
                if (i + 1 < code.length() && code.charAt(i) == '*' && code.charAt(i + 1) == '/') {
                    cnt--;
                    i++;
                    continue;
                }
                if (i + 1 < code.length() && code.charAt(i) == '/' && code.charAt(i + 1) == '*') {
                    cnt++;
                    i++;
                    continue;
                }
            }
            if (cnt == 0) {
                sb.append(code.charAt(i));
            }
        }
//        System.out.println(replaceBlank(sb.toString().replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "")));
        return sb.toString();
    }
}
