package cn.lfdevelopment.www.app.common.word;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Random;

/**
 * Created by LiuFa on 2016/9/13.
 */
@Controller
public class ReadOrWriteWord {

    public static String testReadByExtractor(String filePath) throws Exception {
        InputStream is = new FileInputStream(filePath);
        XWPFDocument doc = new XWPFDocument(is);
        XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
        String text = extractor.getText();
        String str = "";
        String[] content = text.split("//");
        for (int i = 0; i < 8; i++) {
            int number = new Random().nextInt(content.length) + 1;
             str = str + content[number];
        }
        close(is);
        return str;
    }




    private static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/readWordText",produces = {"application/json;charset=UTF-8"})
    public String readWordText(@RequestParam("filePath") String filePath,Model model) throws Exception {
        model.addAttribute("message","已为您自动抽取其中8道题目");
        return testReadByExtractor(filePath);
    }


    @RequestMapping("/file")
    public String file(){
        return "/uplaod";
    }

    /**
     * 文件上传具体实现方法;
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload",produces = {"application/json;charset=UTF-8"})
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model){
        if(!file.isEmpty()){
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("filePath",file.getOriginalFilename());
            return "redirect:/readWordText";
        }else{
            return "redirect:/file";
        }
    }
}
