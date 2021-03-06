package cn.lfdevelopment.www.app.exampool.controller;

import cn.lfdevelopment.www.app.exampool.pojo.CivilServantChoice;
import cn.lfdevelopment.www.app.exampool.service.ExamPoolService;
import cn.lfdevelopment.www.app.sys.pojo.SysRight;
import cn.lfdevelopment.www.app.sys.service.SysRightService;
import cn.lfdevelopment.www.sys.redis.RedisUtils;
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

    @Autowired
    private SysRightService sysRightService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/exampool/choice")
    public String choice(){
        return "desktop/exampool/choice";
    }

    @RequestMapping("/exampool/exampool")
    public String exampool(){
        return "desktop/exampool/exampool";
    }

    @RequestMapping("/exampool/pcse/multipleChoice")
    public String multipleChoice(){
        return "desktop/exampool/pcse/multipleChoice";
    }

    @RequestMapping("/exampool/pcse/toAddSingleChoice")
    public String toAddSingleChoice(){
        return "desktop/exampool/pcse/singleAdd";
    }

    @RequestMapping("/exampool/fileuUpload")
    public String fileUpload(){
        return "desktop/common/fileupload";
    }


    @RequestMapping("/exampool/getdata")
    @ResponseBody
    public String getData(Model model,int page,int start,int limit){
        List<CivilServantChoice> list = examPoolService.getQueryData(page,limit);
        PageInfo pageInfo = new PageInfo<>(list);
        model.addAttribute("success",true);
        model.addAttribute("total",pageInfo.getTotal());
        model.addAttribute("choice",pageInfo.getList());
        return JSON.toJSONString(model);
    }


    @RequestMapping("/exampool/saveChoice")
    @ResponseBody
    public String saveChoice(Model model,CivilServantChoice civilServantChoice){
        int count= examPoolService.save(civilServantChoice);
        model.addAttribute("success",true);
        model.addAttribute("msg","成功保存" + count + "条数据");
        return JSON.toJSONString(model);
    }


    @RequestMapping("/exampool/deleteChoice")
    @ResponseBody
    public String deleteChoice(Model model,String ids){
        examPoolService.deleteChoice(ids);
        model.addAttribute("success",true);
        model.addAttribute("msg","删除成功!");
        return JSON.toJSONString(model);
    }

    @RequestMapping("/exampool/updateChoice")
    @ResponseBody
    public String updateChoice(Model model,CivilServantChoice civilServantChoice){
        examPoolService.updateChoice(civilServantChoice);
        model.addAttribute("success",true);
        model.addAttribute("msg","更新成功!");
        return JSON.toJSONString(model);
    }

    @RequestMapping("/exampool/getMenu")
    @ResponseBody
    public String getMenu(Model model){
        //优先从缓存中获取数据
        List<SysRight> rootList;
        try {
            rootList = (List<SysRight>) redisUtils.get("menuList");
        } catch (Exception e) {
            _logger.error("redis读取菜单失败,正在从数据库读取....");
            rootList = sysRightService.getSysRightRoot();
            for (SysRight sysRight : rootList) {
                int rootRightId = sysRight.getId();
                List<SysRight> childrenList = sysRightService.getSysRightChildren(rootRightId);
                sysRight.setChildren(childrenList);
            }
        }
        model.addAttribute("menu",rootList);
        model.addAttribute("success",true);
        return JSON.toJSONString(model);
    }

}
