/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.pcse.controller;

import cn.lfdevelopment.www.app.pcse.pojo.PcseSingleChoice;
import cn.lfdevelopment.www.app.pcse.service.PcseService;
import cn.lfdevelopment.www.common.util.ExcelUtils;
import cn.lfdevelopment.www.common.util.FileUtils;
import cn.lfdevelopment.www.common.util.WordUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuFa on 2016/10/20.
 * cn.lfdevelopment.www.app.pcse
 * DevelopmentApp
 */
@Controller
public class PcseController {
    @Value("${pcse.fileupload.template-path}")
    private String templateFilePath;
    @Value("${pcse.filedownload.path}")
    private String downLoadFilePath;
    private final Logger _logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PcseService pcseService;

    /**
     * 省级公务员考试单选题模块主页面
     * @return
     */
    @RequestMapping("/exampool/pcse/singleChoice/main")
    public String singleChoiceMain(Model model,String mainPanelId){
        model.addAttribute("mainPanelId",mainPanelId);
        return "desktop/exampool/pcse/singleChoice";
    }


    /**
     * 省级公务员考试多选题模块主页面
     * @param model
     * @param mainPanelId
     * @return
     */
    @RequestMapping("/exampool/pcse/multipleChoice/main")
    public String multipleChoiceMain(Model model,String mainPanelId){
        model.addAttribute("mainPanelId",mainPanelId);
        return "desktop/exampool/pcse/multipleChoice";
    }


    /**
     * store刷新查询
     * @param model
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/exampool/pcse/singleChoice/getStore")
    @ResponseBody
    public String getData(Model model,int page,int limit,String data){
        List<PcseSingleChoice> list = pcseService.getStoreData(page,limit,(Map) JSON.parse(data));
        PageInfo pageInfo = new PageInfo<>(list);
        model.addAttribute("success",true);
        model.addAttribute("total",pageInfo.getTotal());
        model.addAttribute("data",list);
        return JSON.toJSONString(model);
    }


    /**
     * 保存新增的选择题
     * @param model
     * @param pcseSingleChoice
     * @return
     */
    @RequestMapping("/exampool/pcse/saveAddSingleChoice")
    @ResponseBody
    public String saveAddSingleChoice(Model model,PcseSingleChoice pcseSingleChoice){
        //设置更新日期
        pcseSingleChoice.setUpdateTime(new Date());
        String title  = pcseSingleChoice.getTitle();
        //去除uwditor的p标签
        pcseSingleChoice.setTitle(title.substring(3,title.length()-4));
        int count= pcseService.saveAddSingleChoice(pcseSingleChoice);
        model.addAttribute("success",true);
        model.addAttribute("msg","成功保存" + count + "条数据");
        return JSON.toJSONString(model);
    }


    /**
     * 删除选择的单项选择题
     * @param model
     * @param ids
     * @return
     */
    @RequestMapping("/exampool/pcse/deleteSingleChoice")
    @ResponseBody
    public String deleteSingleChoice(Model model,String ids){
        pcseService.deleteSingleChoice(ids);
        model.addAttribute("success",true);
        model.addAttribute("msg","删除成功!");
        return JSON.toJSONString(model);
    }

    /**
     * 跳转到修改地、单项选择题的页面
     * @return
     */
    @RequestMapping("/exampool/pcse/toUpdateSingleChoice")
    public String toUpdateSingleChoice(int id, Model model){
        model.addAttribute("singleChoice",pcseService.getSingleChoiceById(id));
        return "desktop/exampool/pcse/singleUpdate";
    }


    /**
     * 保存修改的单项选择题
     * @param model
     * @param pcseSingleChoice
     * @return
     */
    @RequestMapping("/exampool/pcse/saveUpdateSingleChoice")
    @ResponseBody
    public String saveUpdateSingleChoice(Model model,PcseSingleChoice pcseSingleChoice){
        //设置更新日期
        pcseSingleChoice.setUpdateTime(new Date());
        String title  = pcseSingleChoice.getTitle();
        //去除uwditor的p标签
        pcseSingleChoice.setTitle(title.substring(3,title.length()-4));
        int count= pcseService.saveUpdateSingleChoice(pcseSingleChoice);
        model.addAttribute("success",true);
        model.addAttribute("msg","成功更新" + count + "条数据");
        return JSON.toJSONString(model);
    }


    /**
     * 罗列出系统中题目重复的数据，可以选择性的进行删除
     * @param model
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/exampool/pcse/singleChoice/removeRepeatChoice")
    @ResponseBody
    public String removeRepeatChoice(Model model,int page,int limit){
        List<PcseSingleChoice> list = pcseService.removeRepeatChoice(page,limit);
        PageInfo pageInfo = new PageInfo<>(list);
        model.addAttribute("success",true);
        model.addAttribute("total",pageInfo.getTotal());
        model.addAttribute("data",list);
        return JSON.toJSONString(model);
    }


    /**
     * 批量上传文件模板下载
     * @return
     * @throws IOException
     */
    @RequestMapping("/exampool/pcse/singleChoice/templateDownload")
    public ResponseEntity<byte[]> templateDownload() throws IOException {
        Resource res = new ClassPathResource("files/PCSESingleChoiceTemplateV1.0.xlsx");
        return FileUtils.fileDownload("省级公务员试题单选题批量上传模板v1.0.xlsx",res.getFile());
    }


    /**
     * 模板文件上传
     * @param request
     * @param model
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("/exampool/pcse/singleChoice/uploadTemplateFile")
    @ResponseBody
    public String uploadTemplateFile(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file)throws IllegalStateException, IOException {
        String fileName = String.valueOf(System.currentTimeMillis());
        //扩展名
        String extensionName = FileUtils.getExtensionName(file.getOriginalFilename());
        try {
            File folder = new File(templateFilePath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            FileUtils.multipFileUpload(request,templateFilePath,fileName);
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("success", false);
            e.printStackTrace();
        }
        model.addAttribute("attachmentId", fileName);
        model.addAttribute("attachmentName", fileName + "." + extensionName);
        return JSON.toJSONString(model);
    }


    /**
     *  解析上传的模板文件
     * @param fileName
     * @param model
     * @return is too complex to analyze by data flow algorithm
     */
    @RequestMapping("/exampool/pcse/singleChoice/parseTemplateFile")
    @ResponseBody
    public String parseTemplateFile(String fileName, Model model){
        try {
            Workbook wb = ExcelUtils.getWorkbook(templateFilePath, fileName);

            //得到第3个shell
            Sheet sheet=wb.getSheetAt(2);

            //得到Excel的行数
            int totalRows=sheet.getPhysicalNumberOfRows();

            //得到Excel的列数(前提是有行数)
            int totalCells = 0;
            if(totalRows>0 && sheet.getRow(0) != null){
                totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            }

            //无数据
            if(totalRows <= 1 || totalCells <= 0){
                model.addAttribute("success",false);
                model.addAttribute("message","没有读取到任何数据,请检查文档");
                return JSON.toJSONString(model);
            }
            List<PcseSingleChoice> list=new ArrayList<>();
            //循环读取
            for (int r=1;r<totalRows;r++) {
                PcseSingleChoice pcseSingleChoice = new PcseSingleChoice();
                pcseSingleChoice.setIsImage(0);
                pcseSingleChoice.setUpdateTime(new Date());

                Row row = sheet.getRow(r);
                if (row == null) continue;
                //循环Excel的列
                for(int c = 0; c <totalCells; c++){
                    Cell cell = row.getCell(c);
                    if (null != cell){
                        if(c==0){
                            pcseSingleChoice.setTitle(cell.getStringCellValue());
                        }else if(c==1){
                            pcseSingleChoice.setAnswerA(cell.getStringCellValue());
                        }else if(c==2){
                            pcseSingleChoice.setAnswerB(cell.getStringCellValue());
                        }else if(c==3){
                            pcseSingleChoice.setAnswerC(cell.getStringCellValue());
                        }else if(c==4){
                            pcseSingleChoice.setAnswerD(cell.getStringCellValue());
                        }else if(c==5){
                            pcseSingleChoice.setType(cell.getStringCellValue().contains("行测")? 0 : 1);
                        }else if(c==6){
                            String value = cell.getStringCellValue();
                            pcseSingleChoice.setAnswerRight(value.contains("A")?0 :value.contains("B")?1:value.contains("C")?2:3);
                        }else if(c==7){
                            pcseSingleChoice.setMeno(cell.getStringCellValue());
                        }
                    }
                }
                list.add(pcseSingleChoice);
            }
            int count = pcseService.saveByBatch(list);
            model.addAttribute("message","解析成功,成功插入"+count+"条数据!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("success",false);
            model.addAttribute("message","解析出错,请检查文档");
            return JSON.toJSONString(model);
        }
        model.addAttribute("success",true);
        return JSON.toJSONString(model);
    }



    @RequestMapping("/exampool/pcse/singleChoice/htmlToWordDownload")
    public ResponseEntity<byte[]> htmlToWordDownload(String ids) throws IOException {
        String html = "<html><body><p style=\"text-align:center\">\n" +
                "    <strong><span style=\"font-family: 宋体;font-size: 14px;position: relative;top: -4px\"></span></strong>\n" +
                "</p>\n" +
                "<p style=\"text-align:center\">\n" +
                "    <span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><strong><span style=\"font-size: 19px; position: relative; top: -4px;\">一战成公</span></strong><strong><span style=\"font-size: 19px; position: relative; top: -4px;\"></span></strong></span>\n" +
                "</p>\n" +
                "<p style=\"text-align:center\">\n" +
                "    <span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><strong><span style=\"font-size: 19px; position: relative; top: -4px;\">省级公务员试题单选题v1.0</span></strong><strong><span style=\"font-size: 19px; position: relative; top: -4px;\"></span></strong><strong><span style=\"font-size: 19px; position: relative; top: -4px;\"></span></strong></span>\n" +
                "</p>\n" +
                "<p>\n" +
                "    <span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><strong><span style=\"font-family: 宋体; font-size: 19px; position: relative; top: -4px;\"><br/></span></strong><strong><span style=\"font-family: 宋体; font-size: 19px; position: relative; top: -4px;\"></span></strong></span>\n" +
                "</p>\n" +
                "<p style=\"text-align:center;line-height:24px\">\n" +
                "    <span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><strong><span style=\"font-size: 14px; font-family: 宋体;\">by "+SecurityUtils.getSubject().getPrincipal()+" on "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"</span></strong></span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 24px;\">\n" +
                "    <span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><strong><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></strong><strong><span style=\"font-size: 14px; font-family: 宋体;\"></span></strong></span>\n" +
                "</p>";
        List<PcseSingleChoice> list  = pcseService.getSingleChoiceByIds(ids);
        for (int i = 0; i < list.size(); i++) {
            //题目类型
            String type  = list.get(i).getType() == 0 ? "行测" : "申论";
            int answer = list.get(i).getAnswerRight();
            String answerRight = answer == 0 ? "A" : answer == 1 ? "B" : answer == 2 ? "C" : "D";
            //标题
            html = html + "<p style=\"margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px\">\n" +
                    "    <span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;" +
                    "\">"+(i+1)+"."+list.get(i).getTitle()+"</span>\n" +
                    "</p>";
            //A选项
            html = html + "<p style=\"margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px\">\n" +
                    "    <span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;" +
                    "\">A．"+list.get(i).getAnswerA()+"</span>\n" +
                    "</p>";
            //B选项
            html = html + "<p style=\"margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px\">\n" +
                    "    <span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;" +
                    "\">A．"+list.get(i).getAnswerB()+"</span>\n" +
                    "</p>";
            //C选项
            html = html + "<p style=\"margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px\">\n" +
                    "    <span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;" +
                    "\">A．"+list.get(i).getAnswerC()+"</span>\n" +
                    "</p>";
            //D选项
            html = html + "<p style=\"margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px\">\n" +
                    "    <span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;" +
                    "\">A．"+list.get(i).getAnswerD()+"</span>\n" +
                    "</p>";
            //题目类型
            html = html + "<p style=\"margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px\">\n" +
                    "    <span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;" +
                    "\"><strong>【题目类型】</strong>"+type+"</span>\n" +
                    "</p>";
            //正确答案
            html = html + "<p style=\"margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px\">\n" +
                    "    <span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;" +
                    "\"><strong>【正确答案】</strong>"+answerRight+"</span>\n" +
                    "</p>";
            //答案解析
            html = html + "<p style=\"margin-top:0;margin-bottom:0;text-indent:28px;line-height:24px\">\n" +
                    "    <span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;" +
                    "\"><strong>【解析】</strong>"+list.get(i).getMeno()+"</span>\n" +
                    "</p><br/><br/>";
        }

        String fileName = System.currentTimeMillis()+".docx";
        WordUtils.htmlToWord(html,downLoadFilePath,fileName);
        Resource res = new FileSystemResource(downLoadFilePath+fileName);
        return FileUtils.fileDownload(fileName,res.getFile());
    }


    /**
     * 在线预览选中的题目
     * @param ids
     * @param model
     * @return
     */
    @RequestMapping("/exampool/pcse/singleChoice/htmlToWordPreview")
    public String htmlToWordPreview(String ids,Model model){
        if(StringUtil.isNotEmpty(ids)){
            List<PcseSingleChoice> list  = pcseService.getSingleChoiceByIds(ids);
            model.addAttribute("list",list);
        }
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "desktop/exampool/pcse/htmlPreview";

    }
}
