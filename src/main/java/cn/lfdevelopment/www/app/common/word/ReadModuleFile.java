package cn.lfdevelopment.www.app.common.word;

import com.alibaba.fastjson.JSON;
import org.aspectj.util.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

/**
 * Created by LiuFa on 2016/10/23.
 */
@Controller
public class ReadModuleFile {
    public static void readTxtFile(String filePath) {
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }

    /**
     * 通过 FileSystemResource 以文件系统绝对路径的方式进行访问；
     * 通过 ClassPathResource 以类路径的方式进行访问；
     * 通过 ServletContextResource 以相对于 Web 应用根目录的方式进行访问。
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//        Resource res = new ClassPathResource("choice.ftl");
//        Resource res = new FileSystemResource("H:\\var\\choice.ftl");
//        // ② 将文件内容拷贝到一个 String 中
//        String fileStr = FileCopyUtils.copyToString(new FileReader(res.getFile()));
//        System.out.println(fileStr);

        String filePath = "H:\\var\\choice2.ftl";
        File file = new File(filePath);


        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            System.out.println(lineTxt);
        }
        read.close();

        String fileTxt = FileUtil.readAsString(file);

        FileUtil.writeAsString(file,String.valueOf(System.currentTimeMillis()));
    }

    @ResponseBody
    @RequestMapping("/test/readProperty")
    public String readProperty(Model model) throws IOException {
        Resource res = new ClassPathResource("templates/test.ftl");
        File file = res.getFile();
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        String properties = "";
        while ((lineTxt = bufferedReader.readLine()) != null) {
            if(lineTxt.contains("<label class=\"control-label leipiplugins-orgname\">")){
                properties = properties + lineTxt.replace("<label class=\"control-label leipiplugins-orgname\">","").replace("</label>","")+ ",";
            }
        }
        read.close();
        model.addAttribute("success",true);
        model.addAttribute("properties",properties.substring(0, properties.length() - 1));
        return JSON.toJSONString(model);
    }

    @ResponseBody
    @RequestMapping("/test/save")
    public String save(Model model,String field1_1,String field1_2,String field2_1,String field2_2,String field3_1,String field3_2,String field4_1,String field4_2) throws IOException {
        Resource res = new ClassPathResource("templates/test.ftl");
        File file = res.getFile();
        String fileTxt = FileUtil.readAsString(file);
        fileTxt = new String(fileTxt.getBytes("gbk"),"utf-8");
        fileTxt = fileTxt.replace(field1_1.trim(),field1_2).replace(field2_1.trim(),field2_2).replace(field3_1.trim(),field3_2).replace(field4_1.trim(),field4_2);
        FileUtil.writeAsString(file,fileTxt);
        model.addAttribute("success",true);
        return JSON.toJSONString(model);
    }

    @RequestMapping("/test/list")
    public String list(){
        return "/readModuleFile";
    }

    @RequestMapping("/test/view")
    public String view(){
        return "";
    }
}
