package cn.lfdevelopment.www.app.exampool.controller;

import cn.lfdevelopment.www.app.exampool.pojo.CivilServantChoice;
import cn.lfdevelopment.www.app.exampool.service.ExamPoolService;
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

/**
 * Created by LiuFa on 2016/10/8.
 * cn.lfdevelopment.www.app.exampool.controller
 * DevelopmentApp
 */
@Controller
public class ExamPoolController {
    private final Logger _logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExamPoolService examPoolService;


    @RequestMapping("/exampool/choice")
    public String choice(){
        return "desktop/exampool/choice";
    }


    @RequestMapping("/exampool/getdata")
    @ResponseBody
    public String getData(Model model,int page,int start,int limit){
        List<CivilServantChoice> list = examPoolService.getQueryData(page,limit);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("success",true);
        model.addAttribute("total",pageInfo.getTotal());
        model.addAttribute("choice",pageInfo.getList());
        return JSON.toJSONString(model);
    }
}
