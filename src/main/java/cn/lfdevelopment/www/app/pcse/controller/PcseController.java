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
     *
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
}
