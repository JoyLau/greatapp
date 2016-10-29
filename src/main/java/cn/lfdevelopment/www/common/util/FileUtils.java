/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.common.util;

import cn.lfdevelopment.www.GreatappApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by LiuFa on 2016/10/27.
 * cn.lfdevelopment.www.common.util
 * DevelopmentApp
 */
public class FileUtils {

    private static Logger _logger = LoggerFactory.getLogger(GreatappApplication.class);
    /**
     * 生成唯一的文件名，放在上传文件名相同发生覆盖情况
     *
     * @param realFileName
     *            当前文件名
     * @return 拼接UUID的唯一文件名
     */
    public String createUniqueFileName(String realFileName) {
        return UUID.randomUUID().toString() + realFileName;
    }

    /**
     * 获取文件大小，将B，转换为文字形式
     *
     * @param size
     * @return 返回文件大小的文字描述
     */
    public static String getFileSize(Long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        Double sizeD = Double.parseDouble(size.toString());
        if (size < 1024) {
            return size + "B";
        } else if (size < 1024 * 1024) {
            double d = sizeD / 1024;
            return df.format(d) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            double d = sizeD / (1024 * 1024);
            return df.format(d) + "M";
        } else {
            double d = sizeD / (1024 * 1024 * 1024);
            return df.format(d) + "G";
        }
    }

    /**
     * 通过文件路径获取文件名
     *
     * @param path
     *            文件路径
     * @return 返回文件名
     */
    public static String getFileName(String path) {
        if (path == null) {
            return "";
        }
        if (path.contains("/")) {
            if (path.contains(".")) {
                return path.substring(path.lastIndexOf("/") + 1,
                        path.lastIndexOf("."));
            } else {
                return path.substring(path.lastIndexOf("/") + 1);
            }
        } else if (path.contains("\\")) {
            if (path.contains(".")) {
                return path.substring(path.lastIndexOf("\\") + 1,
                        path.lastIndexOf("."));
            } else {
                return path.substring(path.lastIndexOf("\\") + 1);
            }
        } else {
            if (path.contains(".")) {
                return path.substring(0, path.lastIndexOf("."));
            } else {
                return path;
            }
        }
    }

    /**
     * 获取文件的扩展名
     *
     * @param filename
     *            文件名
     * @return 文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取项目部署的webRoot文件夹路径
     */
    public static String getClassPath() {
        // classes文件路径
        File classesDir = new File(FileUtils.class.getClassLoader()
                .getResource("").getPath());
        // 项目Classes目录
        return classesDir.getAbsolutePath().replace("%20", " ");
    }

    /**
     * 获取项目部署的webRoot文件夹路径
     */
    public static String getWebPath(HttpServletRequest request) {
        // 项目WebRoot目录
        String webRootPathString = request.getServletContext().getRealPath("/");
        return webRootPathString.replace("%20", " ");
    }


    /**
     * 输出图片
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @param filePath
     *            文件完整路径名
     * @throws Exception
     *             下载文件异常
     */
    public static void outImage(HttpServletRequest request,
                                     HttpServletResponse response, String filePath) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                _logger.error("文件" + filePath + "不存在");
                return;
            }

            long fileLength = file.length();
            String fileName = file.getName();
            String extName = FileUtils.getExtensionName(fileName).toLowerCase();
            if ("jpg".equals(extName)) {
                response.setContentType("image/jpeg");
            } else if ("png".equals(extName)) {
                response.setContentType("image/png");
            } else if ("jpeg".equals(extName)) {
                response.setContentType("image/jpeg");
            } else if ("bmp".equals(extName)) {
                response.setContentType("image/bmp");
            } else if ("gif".equals(extName)) {
                response.setContentType("image/gif");
            } else {
                _logger.error("文件类型不是图片");
            }
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(filePath));
            bos = new BufferedOutputStream(response.getOutputStream());
            //byte[] buff = new byte[FileSystemConfig.getDownloadFileBufferSize()];
            byte[] buff = new byte[1024];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (Exception e) {
                //
            }
        }
    }


    /**
     * spring文件下载
     * @param fileName
     * @param file
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> fileDownload(String fileName, File file) throws IOException {
        String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }


    /**
     * spring文件上传<多文件上传/>
     * @param request
     * @param uploadPath
     * @param fileName 文件名，不带后缀名
     * @return
     */
    public static boolean multipFileUpload(HttpServletRequest request, String uploadPath,String fileName){
        // 设置上下方文
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        try {
            // 检查form是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    // 由CommonsMultipartFile继承而来,拥有上面的方法.
                    MultipartFile file = multiRequest.getFile(iter.next());
                    if (file != null) {
                        File localFile = new File(uploadPath + fileName + "."
                                + FileUtils.getExtensionName
                                (file.getOriginalFilename()));
                        file.transferTo(localFile);
                    }

                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 单文件上传
     * @param file
     * @param uploadPath
     */
    public static void singleFileUpload(CommonsMultipartFile file, String uploadPath,String fileName) {
        // 文件类型
//        file.getContentType();
        // 文件大小
//        file.getSize();
        // 文件名称
//        file.getOriginalFilename();
        if (!file.isEmpty()) {
            String filename = fileName + "." + FileUtils.getExtensionName(file
                    .getOriginalFilename());
            File localFile = new File(uploadPath + filename);
            try {
                file.transferTo(localFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
