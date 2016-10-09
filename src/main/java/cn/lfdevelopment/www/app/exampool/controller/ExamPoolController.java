package cn.lfdevelopment.www.app.exampool.controller;

import cn.lfdevelopment.www.app.exampool.service.ExamPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
