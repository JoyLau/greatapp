/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.pcse.controller;

import cn.lfdevelopment.www.app.pcse.pojo.PcseSingleChoice;
import cn.lfdevelopment.www.app.pcse.service.PcseService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
