/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.controller;

import cn.lfdevelopment.www.app.common.login.service.LoginService;
import cn.lfdevelopment.www.common.util.vcode.Captcha;
import cn.lfdevelopment.www.common.util.vcode.GifCaptcha;
import cn.lfdevelopment.www.sys.shiro.CaptchaException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiuFa on 2016/8/5.
 * cn.lfdevelopment.www.app.common.login.controller
 * DevelopmentApp
 */
@Controller
public class LoginController {

    private final Logger _logger = LoggerFactory.getLogger(this.getClass());

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            //用户已经登录过了
            return "redirect:/main";
        }
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            //注意：此处虽然是未找到该账号，但是仍然要提示用户‘用户名或密码错误’，这是为了确保安全性，防止用户根据提示信息进行暴力登录-@liufa
            error = "用户名或密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            //同理
            error = "用户名或密码错误";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            error = "您的账号已被锁定，请联系管理员";
        } else if (CaptchaException.class.getName().equals(exceptionClassName)) {
            error = "验证码错误";
        } else if (exceptionClassName != null) {
            error = "您的账号存在异常,请联系管理员";
        }
        model.addAttribute("message", error);
        return "login";
    }

    @RequestMapping("/main")
    public String main(){
//        return "app/common/main/main";
        return "desktop/desktop";
    }
    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @RequestMapping(value="/getGifCode",method= RequestMethod.GET)
    public void getGifCode(HttpServletResponse response){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            Captcha captcha = new GifCaptcha(150,45,6);//宽，高，位数
            //输出
            ServletOutputStream out = response.getOutputStream();
            captcha.out(out);
            out.flush();
            //保存到session
            SecurityUtils.getSubject().getSession().setAttribute("captcha",captcha.text().toLowerCase());
            _logger.info(captcha.text().toLowerCase());
        } catch (Exception e) {
            _logger.error("获取验证码异常：%s",e.getMessage());
        }
    }

    @RequestMapping("/zhihu")
    String zhihu (){
        return "zhihu";
    }

    @RequestMapping("/desktop")
    public String desktop(){
        return "desktop/desktop";
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    String getMenu (){
        return "{\n" +
                "  \"menu\": [\n" +
                "    {\n" +
                "      \"righturl\": null,\n" +
                "      \"id\": \"51\",\n" +
                "      \"text\": \"客户管理\",\n" +
                "      \"leaf\": false,\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"righturl\": \"pages/customer/customer.jsp\",\n" +
                "          \"id\": \"52\",\n" +
                "          \"text\": \"客户信息\",\n" +
                "          \"leaf\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"righturl\": \"pages/customer/company.jsp\",\n" +
                "          \"id\": \"56\",\n" +
                "          \"text\": \"公司管理\",\n" +
                "          \"leaf\": true\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"id\": \"3\",\n" +
                "      \"text\": \"日常管理\",\n" +
                "      \"leaf\": false,\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"righturl\": \"pages/manage/zc_cggl.jsp\",\n" +
                "          \"id\": \"599\",\n" +
                "          \"text\": \"资产管理\",\n" +
                "          \"leaf\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"righturl\": \"pages/manage/wage_home_manage.jsp\",\n" +
                "          \"id\": \"10107\",\n" +
                "          \"text\": \"家装组工资方案\",\n" +
                "          \"leaf\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"righturl\": \"pages/sys/qybps.jsp\",\n" +
                "          \"id\": \"546\",\n" +
                "          \"text\": \"企业白皮书\",\n" +
                "          \"leaf\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"righturl\": \"pages/manage/wage_2013_manage.jsp\",\n" +
                "          \"id\": \"470\",\n" +
                "          \"text\": \"2013工资方案\",\n" +
                "          \"leaf\": true\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"id\": \"2\",\n" +
                "      \"text\": \"系统管理\",\n" +
                "      \"leaf\": false,\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"righturl\": \"pages/sys/user/drawuser.jsp\",\n" +
                "          \"id\": \"90\",\n" +
                "          \"text\": \"用户管理\",\n" +
                "          \"leaf\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"righturl\": \"pages/sys/right.jsp\",\n" +
                "          \"id\": \"12\",\n" +
                "          \"text\": \"权限管理\",\n" +
                "          \"leaf\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"righturl\": \"pages/sys/role.jsp\",\n" +
                "          \"id\": \"11\",\n" +
                "          \"text\": \"角色管理\",\n" +
                "          \"leaf\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"righturl\": \"pages/sys/password.jsp\",\n" +
                "          \"id\": \"13\",\n" +
                "          \"text\": \"密码修改\",\n" +
                "          \"leaf\": true\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"id\": \"462\",\n" +
                "      \"text\": \"考勤管理\",\n" +
                "      \"leaf\": false,\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"righturl\": \"pages/manage/work_time_manage.jsp\",\n" +
                "          \"id\": \"465\",\n" +
                "          \"text\": \"考勤报表\",\n" +
                "          \"leaf\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"righturl\": \"pages/manage/dept_manage.jsp\",\n" +
                "          \"id\": \"536\",\n" +
                "          \"text\": \"考勤汇总\",\n" +
                "          \"leaf\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"righturl\": \"pages/manage/attendance_manage.jsp\",\n" +
                "          \"id\": \"461\",\n" +
                "          \"text\": \"考勤查询\",\n" +
                "          \"leaf\": true\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"id\": \"9999\",\n" +
                "      \"text\": \"<font color = \\\"red\\\">数据源管理<\\/font>\",\n" +
                "      \"leaf\": false,\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"righturl\": \"pages/database/datasync.jsp\",\n" +
                "          \"id\": \"10155\",\n" +
                "          \"text\": \"同步管理\",\n" +
                "          \"leaf\": true\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"user\": {\n" +
                "    \"allownum\": \"\",\n" +
                "    \"bgs000\": \"\",\n" +
                "    \"brand\": \"\",\n" +
                "    \"cardno\": \"\",\n" +
                "    \"ccsj00\": \"\",\n" +
                "    \"createtime\": \"\",\n" +
                "    \"curusername\": \"\",\n" +
                "    \"deptid\": \"\",\n" +
                "    \"deptname\": \"\",\n" +
                "    \"djid00\": \"\",\n" +
                "    \"drawuserid\": \"\",\n" +
                "    \"drawusername\": \"\",\n" +
                "    \"email\": \"\",\n" +
                "    \"fangan\": \"\",\n" +
                "    \"fuid\": \"\",\n" +
                "    \"gender\": \"\",\n" +
                "    \"gzdjdx\": \"\",\n" +
                "    \"gzsjdj\": \"\",\n" +
                "    \"headmax\": \"\",\n" +
                "    \"headportrait\": \"\",\n" +
                "    \"htdjdx\": \"\",\n" +
                "    \"htsjdj\": \"\",\n" +
                "    \"htynum\": \"\",\n" +
                "    \"htyrank\": \"\",\n" +
                "    \"hukou0\": \"\",\n" +
                "    \"idcard\": \"\",\n" +
                "    \"im_uid\": \"\",\n" +
                "    \"infolx\": \"\",\n" +
                "    \"isbigaudit\": \"\",\n" +
                "    \"isdefault\": \"\",\n" +
                "    \"isorder\": \"\",\n" +
                "    \"ispresmallaudit\": \"\",\n" +
                "    \"issmallaudit\": \"\",\n" +
                "    \"jzdjdx\": \"\",\n" +
                "    \"jzsjdj\": \"\",\n" +
                "    \"kflx00\": \"\",\n" +
                "    \"kfsylj\": \"\",\n" +
                "    \"loginHistoryId\": \"\",\n" +
                "    \"lrr000\": \"\",\n" +
                "    \"lrsj00\": \"\",\n" +
                "    \"openId\": \"\",\n" +
                "    \"orderdate\": \"\",\n" +
                "    \"phone\": \"18655173391\",\n" +
                "    \"pwd\": \"202cb962ac59075b964b07152d234b70\",\n" +
                "    \"pwdupdate\": \"2022-12-23\",\n" +
                "    \"qq\": \"295869323\",\n" +
                "    \"realname\": \"管理员\",\n" +
                "    \"remark\": \"\",\n" +
                "    \"rylx00\": \"2\",\n" +
                "    \"rzdjb0\": \"\",\n" +
                "    \"rzly00\": \"\",\n" +
                "    \"rzlyzl\": \"\",\n" +
                "    \"rzrq00\": \"\",\n" +
                "    \"salary\": \"0\",\n" +
                "    \"sfszz0\": \"1\",\n" +
                "    \"sftzsh\": \"\",\n" +
                "    \"sfycc0\": \"\",\n" +
                "    \"shr000\": \"\",\n" +
                "    \"shsj00\": \"\",\n" +
                "    \"state\": \"1\",\n" +
                "    \"theme\": \"ext-all-xtheme-red03\",\n" +
                "    \"type\": \"1\",\n" +
                "    \"userid\": \"1\",\n" +
                "    \"userids\": 0,\n" +
                "    \"username\": \"admin\",\n" +
                "    \"validoffice\": \"\",\n" +
                "    \"viewnum\": \"\",\n" +
                "    \"workno\": \"管理员\",\n" +
                "    \"xglx00\": \"\",\n" +
                "    \"xgzt00\": \"\",\n" +
                "    \"xl0000\": \"\",\n" +
                "    \"yhdj00\": \"\",\n" +
                "    \"zhiwu0\": \"\",\n" +
                "    \"zhiwu1\": \"\"\n" +
                "  },\n" +
                "  \"userrole\": [\n" +
                "    {\n" +
                "      \"creator\": \"管理员\",\n" +
                "      \"creatorId\": \"1\",\n" +
                "      \"creattime\": \"2016-04-26 15:12:13\",\n" +
                "      \"rightid\": [],\n" +
                "      \"roleid\": \"167\",\n" +
                "      \"rolename\": \"考勤管理\",\n" +
                "      \"userid\": [],\n" +
                "      \"workno\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"creator\": \"管理员\",\n" +
                "      \"creatorId\": \"1\",\n" +
                "      \"creattime\": \"2015-01-23 20:53:50\",\n" +
                "      \"rightid\": [],\n" +
                "      \"roleid\": \"1\",\n" +
                "      \"rolename\": \"系统管理员\",\n" +
                "      \"userid\": [],\n" +
                "      \"workno\": \"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"userright\": [\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"0\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"51\",\n" +
                "      \"rightname\": \"客户管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"0\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"462\",\n" +
                "      \"rightname\": \"考勤管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"8\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"0\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"2\",\n" +
                "      \"rightname\": \"系统管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"6\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"0\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"3\",\n" +
                "      \"rightname\": \"日常管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"0\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"9999\",\n" +
                "      \"rightname\": \"<font color = \\\"red\\\">数据源管理<\\/font>\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"9999\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"203\",\n" +
                "      \"rightname\": \"查询所有客户\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"9\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10147\",\n" +
                "      \"rightname\": \"添加项目信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"7\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10148\",\n" +
                "      \"rightname\": \"保存项目信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"9\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10149\",\n" +
                "      \"rightname\": \"停用项目信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"10\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10150\",\n" +
                "      \"rightname\": \"启用项目信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"11\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"56\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10145\",\n" +
                "      \"rightname\": \"启用公司信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"7\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"56\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10146\",\n" +
                "      \"rightname\": \"保存公司信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"8\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1481\",\n" +
                "      \"rightname\": \"历史能力配置\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"1112\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"470\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10015\",\n" +
                "      \"rightname\": \"查看工资历史计算方式\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"30\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"470\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10019\",\n" +
                "      \"rightname\": \"查看工资品豆计算方式\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"/\",\n" +
                "      \"sort\": \"31\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"9998\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10109\",\n" +
                "      \"rightname\": \"部署流程查询\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"act/workFlow_list.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10119\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10120\",\n" +
                "      \"rightname\": \"读取登录人申请的流程实例2\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"act/workFlow_myApply.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"9998\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10110\",\n" +
                "      \"rightname\": \"读取流程启动表单\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"act/workFlow_readStartForm.action\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10113\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10116\",\n" +
                "      \"rightname\": \"读取待办任务表单\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"act/workFlow_readTaskForm.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"9998\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10111\",\n" +
                "      \"rightname\": \"读取流程资源\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"act/workFlow_resource.action\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10113\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10118\",\n" +
                "      \"rightname\": \"完成待办任务\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"act/workFlow_taskComplete.action\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10113\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10115\",\n" +
                "      \"rightname\": \"查看待办任务\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"act/workFlow_upComingTask.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10134\",\n" +
                "      \"rightname\": \"家装组评分更正申请审核\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"auditHomeApplyKhpf\",\n" +
                "      \"sort\": \"13\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"56\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"120\",\n" +
                "      \"rightname\": \"停用公司信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"company/company_invalid.action\",\n" +
                "      \"sort\": \"6\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"56\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"58\",\n" +
                "      \"rightname\": \"查询公司信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"company/company_query.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"56\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"64\",\n" +
                "      \"rightname\": \"添加公司信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"company/company_save.action,sys/user_query.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"56\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"66\",\n" +
                "      \"rightname\": \"修改公司信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"company/company_update.action,company/company_queryById.action,sys/user_query.action\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"62\",\n" +
                "      \"rightname\": \"停用客户信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"customer/customer_invalid.action\",\n" +
                "      \"sort\": \"5\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"200\",\n" +
                "      \"rightname\": \"启用客户信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"customer/customer_invalid.action\",\n" +
                "      \"sort\": \"7\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"57\",\n" +
                "      \"rightname\": \"查询客户信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"customer/customer_query.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"59\",\n" +
                "      \"rightname\": \"添加客户信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"customer/customer_save.action,company/company_query.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"0\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"175\",\n" +
                "      \"rightname\": \"快捷菜单\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"customer/customer_save.action,company/company_query.action,yw/yw_ddgl_save.action,hkjl/hkjl_saveByOrder.action,customer/customer_query.action,yhq/yhq_query.action,yhq/yhq_save.action,customer/customer_query.action\",\n" +
                "      \"sort\": \"8\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"52\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"61\",\n" +
                "      \"rightname\": \"修改客户信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"customer/customer_update.action,customer/customer_queryById.action,company/company_query.action\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"721\",\n" +
                "      \"rightname\": \"表现师有问必答分配\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"customer/ywbd_querySigType.action,customer/ywbd_saveTypeUser.action\",\n" +
                "      \"sort\": \"13\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"646\",\n" +
                "      \"rightname\": \"资产管理查看资产领用情况\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/assets_queryToAssets.action,manage/assets_queryNotAssets.action\",\n" +
                "      \"sort\": \"16\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"618\",\n" +
                "      \"rightname\": \"采购管理资产类型配置\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/assets_queryType.action,manage/assets_deletetype.action,manage/assets_savetype.action\",\n" +
                "      \"sort\": \"5\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"461\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"706\",\n" +
                "      \"rightname\": \"考勤配置\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/attendance_config.action\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"461\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"891\",\n" +
                "      \"rightname\": \"考勤导出Excel\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/attendance_exportToExcel.action\",\n" +
                "      \"sort\": \"5\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"465\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10151\",\n" +
                "      \"rightname\": \"工作时间导出Excel\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/attendance_exportWorkToExcel.action\",\n" +
                "      \"sort\": \"5\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"461\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10143\",\n" +
                "      \"rightname\": \"查看部门考勤\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/attendance_query.action\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"461\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"464\",\n" +
                "      \"rightname\": \"查看所有考勤\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/attendance_query.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"536\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"548\",\n" +
                "      \"rightname\": \"考勤汇总查询全部\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/attendance_queryDept.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"536\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"553\",\n" +
                "      \"rightname\": \"考勤汇总部门查询\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/attendance_queryDept.action\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"465\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10153\",\n" +
                "      \"rightname\": \"查看部门的工作时间\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/attendance_queryWorkTime.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"465\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10154\",\n" +
                "      \"rightname\": \"查看所有的工作时间\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/attendance_queryWorkTime.action\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10133\",\n" +
                "      \"rightname\": \"家装组月度评分审核\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_auditHomeWageApply.action\",\n" +
                "      \"sort\": \"12\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10125\",\n" +
                "      \"rightname\": \"家装组确认\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_confirmHome.action\",\n" +
                "      \"sort\": \"5\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10126\",\n" +
                "      \"rightname\": \"家装组取消确认\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_confirmHome.action\",\n" +
                "      \"sort\": \"6\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10127\",\n" +
                "      \"rightname\": \"家装组终审\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_endAuditHome.action\",\n" +
                "      \"sort\": \"7\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10123\",\n" +
                "      \"rightname\": \"家装组保存\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_lockOrUnlockHome.action\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10124\",\n" +
                "      \"rightname\": \"家装组解锁\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_lockOrUnlockHome.action\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10128\",\n" +
                "      \"rightname\": \"家装组发工资\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_payHome.action\",\n" +
                "      \"sort\": \"8\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10129\",\n" +
                "      \"rightname\": \"家装组取消发工资\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_payHome.action\",\n" +
                "      \"sort\": \"9\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10122\",\n" +
                "      \"rightname\": \"家装组查看工资单\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_queryHomeWageInfo.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10130\",\n" +
                "      \"rightname\": \"查询家装组工资\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_queryWageHome.action\",\n" +
                "      \"sort\": \"10\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10132\",\n" +
                "      \"rightname\": \"家装组添加其它项\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_queryWageOnther.action,manage/wage_saveWageOnther.action\",\n" +
                "      \"sort\": \"11\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"10107\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10121\",\n" +
                "      \"rightname\": \"家装组重新计算\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/wage_resetWageHome.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"621\",\n" +
                "      \"rightname\": \"资产明细资产报废申请\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_addsqfq.action\",\n" +
                "      \"sort\": \"8\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"692\",\n" +
                "      \"rightname\": \"撤销分配的资产\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_cxzcfp.action\",\n" +
                "      \"sort\": \"23\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"616\",\n" +
                "      \"rightname\": \"采购管理删除资产\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_deleteZc.action\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1395\",\n" +
                "      \"rightname\": \"资产删除申请\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_delZcmx.action\",\n" +
                "      \"sort\": \"47\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"691\",\n" +
                "      \"rightname\": \"批量归还人员资产\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_lzzcgh.action\",\n" +
                "      \"sort\": \"22\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"679\",\n" +
                "      \"rightname\": \"资产明细资产统计\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryAssetsStatistic.action\",\n" +
                "      \"sort\": \"20\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1394\",\n" +
                "      \"rightname\": \"查看资产删除报废数据\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryBfSc.action\",\n" +
                "      \"sort\": \"46\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1399\",\n" +
                "      \"rightname\": \"报废删除资产统计\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryBfScTongji.action\",\n" +
                "      \"sort\": \"51\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"654\",\n" +
                "      \"rightname\": \"采购管理复制编号\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryByCgbhAndZcbh.action\",\n" +
                "      \"sort\": \"18\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"617\",\n" +
                "      \"rightname\": \"采购管理查看明细\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryCg.action\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"642\",\n" +
                "      \"rightname\": \"查看采购管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryCg.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"614\",\n" +
                "      \"rightname\": \"查看全部公司资产\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryCg.action,manage/zichan_queryZc.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"647\",\n" +
                "      \"rightname\": \"是否全员可查看\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryCg.action,manage/zichan_queryZc.action\",\n" +
                "      \"sort\": \"17\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1428\",\n" +
                "      \"rightname\": \"易耗品出库/入库审核\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryCkRkQq.action,manage/zichan_updateZcCrsh.action\",\n" +
                "      \"sort\": \"54\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"625\",\n" +
                "      \"rightname\": \"资产明细操作记录\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryCz.action\",\n" +
                "      \"sort\": \"12\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1429\",\n" +
                "      \"rightname\": \"查看易耗品操作记录\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryCz.action\",\n" +
                "      \"sort\": \"55\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1396\",\n" +
                "      \"rightname\": \"资产删除申请审核\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryDelSq.action,manage/zichan_delsh.action\",\n" +
                "      \"sort\": \"48\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"992\",\n" +
                "      \"rightname\": \"资产明细资产报废审核查看\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryfqsq.action\",\n" +
                "      \"sort\": \"26\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"623\",\n" +
                "      \"rightname\": \"资产明细认领归还审核\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryRlsh.action,manage/zichan_rlghshtg.action,manage/zichan_rlghshbtg.action\",\n" +
                "      \"sort\": \"10\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"626\",\n" +
                "      \"rightname\": \"资产明细我的资产\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryWdzc.action,manage/zichan_jsfpzc.action,manage/zichan_bjsfpzc.action\",\n" +
                "      \"sort\": \"13\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"997\",\n" +
                "      \"rightname\": \"采购管理修改审核\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryXG.action,manage/zichan_shjl.action,manage/zichan_btgjl.action,manage/zichan_zs.action\",\n" +
                "      \"sort\": \"45\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1397\",\n" +
                "      \"rightname\": \"查看低值易耗品数据\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryYhp.action\",\n" +
                "      \"sort\": \"49\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1398\",\n" +
                "      \"rightname\": \"低值易耗品资产统计\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryYhpTongji.action\",\n" +
                "      \"sort\": \"50\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1456\",\n" +
                "      \"rightname\": \"资产修改申请审核\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryZcxgsq.action,manage/zichan_updateShxgsq.action\",\n" +
                "      \"sort\": \"57\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"624\",\n" +
                "      \"rightname\": \"资产明细资产转移审核\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryZczysh.action,manage/zichan_zyshtg.action,manage/zichan_zyshbtg.action\",\n" +
                "      \"sort\": \"11\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"686\",\n" +
                "      \"rightname\": \"采购管理资产统计\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_queryZiChanCgTongji.action\",\n" +
                "      \"sort\": \"21\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"615\",\n" +
                "      \"rightname\": \"采购管理新增资产\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_save.action,sys/user_getAllUser.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1426\",\n" +
                "      \"rightname\": \"易耗品出库申请\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_saveCkRksq.action\",\n" +
                "      \"sort\": \"52\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1427\",\n" +
                "      \"rightname\": \"易耗品入库申请\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_saveCkRksq.action\",\n" +
                "      \"sort\": \"53\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"995\",\n" +
                "      \"rightname\": \"采购管理修改资产\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_saveXG.action,manage/assets_common_queryType.action,sys/user_getAllUser.action\",\n" +
                "      \"sort\": \"44\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1430\",\n" +
                "      \"rightname\": \"资产明细修改申请\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_saveZcmxXgSq.action\",\n" +
                "      \"sort\": \"56\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1058\",\n" +
                "      \"rightname\": \"资产拆分\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_splitZc.action\",\n" +
                "      \"sort\": \"32\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"635\",\n" +
                "      \"rightname\": \"资产明细资产分配\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_zcfp.action\",\n" +
                "      \"sort\": \"14\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1014\",\n" +
                "      \"rightname\": \"资产明细报废审核初审\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_zcfq.action,manage/zichan_csbtg.action\",\n" +
                "      \"sort\": \"24\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"620\",\n" +
                "      \"rightname\": \"资产明细资产归还\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_zcgh.action\",\n" +
                "      \"sort\": \"7\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"619\",\n" +
                "      \"rightname\": \"资产明细资产认领\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_zcrl.action\",\n" +
                "      \"sort\": \"6\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"622\",\n" +
                "      \"rightname\": \"资产明细资产转移\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_zczy.action\",\n" +
                "      \"sort\": \"9\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"636\",\n" +
                "      \"rightname\": \"资产明细调岗转移\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_zczy.action,manage/zichan_queryWdzc.action,manage/zichan_tgzy.action\",\n" +
                "      \"sort\": \"15\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"991\",\n" +
                "      \"rightname\": \"资产明细报废审核终审\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"manage/zichan_zstg.action,manage/zichan_zsbtg.action\",\n" +
                "      \"sort\": \"25\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"51\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"56\",\n" +
                "      \"rightname\": \"公司管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/customer/company.jsp\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"51\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"52\",\n" +
                "      \"rightname\": \"客户信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/customer/customer.jsp\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"9999\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10155\",\n" +
                "      \"rightname\": \"同步管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/database/datasync.jsp\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"462\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"461\",\n" +
                "      \"rightname\": \"考勤查询\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/manage/attendance_manage.jsp\",\n" +
                "      \"sort\": \"16\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"462\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"536\",\n" +
                "      \"rightname\": \"考勤汇总\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/manage/dept_manage.jsp\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"3\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"470\",\n" +
                "      \"rightname\": \"2013工资方案\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/manage/wage_2013_manage.jsp\",\n" +
                "      \"sort\": \"16\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"3\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10107\",\n" +
                "      \"rightname\": \"家装组工资方案\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/manage/wage_home_manage.jsp\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"462\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"465\",\n" +
                "      \"rightname\": \"考勤报表\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/manage/work_time_manage.jsp\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"3\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"599\",\n" +
                "      \"rightname\": \"资产管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/manage/zc_cggl.jsp\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"599\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1230\",\n" +
                "      \"rightname\": \"资产导出Excel\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/statistic/exportUrl.jsp,manage/zichan_exportMx.action,manage/zichan_downloadExcel.action,manage/zichan_exportCg.action\",\n" +
                "      \"sort\": \"30\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"2\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"13\",\n" +
                "      \"rightname\": \"密码修改\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/sys/password.jsp\",\n" +
                "      \"sort\": \"6\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"3\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"546\",\n" +
                "      \"rightname\": \"企业白皮书\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/sys/qybps.jsp\",\n" +
                "      \"sort\": \"12\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"2\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"12\",\n" +
                "      \"rightname\": \"权限管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/sys/right.jsp\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"2\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"11\",\n" +
                "      \"rightname\": \"角色管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/sys/role.jsp\",\n" +
                "      \"sort\": \"5\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"2\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"90\",\n" +
                "      \"rightname\": \"用户管理\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"pages/sys/user/drawuser.jsp\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"750\",\n" +
                "      \"rightname\": \"表现师白皮书等级分配不受限制\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/gzzd_common_AllDjpz.action\",\n" +
                "      \"sort\": \"16\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"701\",\n" +
                "      \"rightname\": \"表现师白皮书级别配置\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/gzzd_common_AllDjpz.action,sys/user_updateYhdj.action\",\n" +
                "      \"sort\": \"8\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"546\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"581\",\n" +
                "      \"rightname\": \"查询白皮书\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/gzzd_getJsonTree.action,sys/gzzd_show.action,sys/gzzd_getGzzdList.action,sys/gzzd_addGzzdYd.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1105\",\n" +
                "      \"rightname\": \"增加权限\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_addRight.action,sys/role_AddQuerySigRight.action,sys/role_AddQuerySigRight1.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1097\",\n" +
                "      \"rightname\": \"删除角色\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_deleteRole.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1096\",\n" +
                "      \"rightname\": \"查询角色\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_query.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1101\",\n" +
                "      \"rightname\": \"查看全部角色\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_query.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1104\",\n" +
                "      \"rightname\": \"角色管理查看用户\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_queryRole.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1099\",\n" +
                "      \"rightname\": \"分配权限\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_querySigRight.action,sys/role_querySigRight_delegated.action,sys/role_saveRight.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1102\",\n" +
                "      \"rightname\": \"分配下放权限\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_querySigRight.action,sys/role_querySigRight_delegated1.action,sys/role_saveRight_delegated.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1103\",\n" +
                "      \"rightname\": \"添加角色\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_save.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1100\",\n" +
                "      \"rightname\": \"白皮书等级配置\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_updateRoleYhdj.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1098\",\n" +
                "      \"rightname\": \"修改角色\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/role_updateRoleYhdj.action,sys/role_save.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1286\",\n" +
                "      \"rightname\": \"修改表现师人员的附加基本信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_addFjjbxx.action,randomaudit/randomaudit_queryLevel.action,sys/user_updateUserFjjbxx.action,sys/user_customerqueryUserByUserid.action\",\n" +
                "      \"sort\": \"24\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"112\",\n" +
                "      \"rightname\": \"添加\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_adduser.action,randomaudit/randomaudit_queryLevel.action\",\n" +
                "      \"sort\": \"2\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"13\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"177\",\n" +
                "      \"rightname\": \"修改密码\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_changepwd.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"197\",\n" +
                "      \"rightname\": \"修改表现师密码\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_changePwdFromAdmin.action\",\n" +
                "      \"sort\": \"6\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"111\",\n" +
                "      \"rightname\": \"表现师详细\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_customerqueryUserByUserid.action,sys/user_queryDymicCol.action,randomaudit/randomaudit_queryLevel.action,,sys/user_queryQQById.action,sys/user_queryDymicColByUseridUpdate.action\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"109\",\n" +
                "      \"rightname\": \"停用\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_invalid.action\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"719\",\n" +
                "      \"rightname\": \"表现师启用\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_invalid.action\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"756\",\n" +
                "      \"rightname\": \"查看表现师部门人员信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_query.action\",\n" +
                "      \"sort\": \"17\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"113\",\n" +
                "      \"rightname\": \"查看全部表现师人员信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_query.action,sys/user_queryDymicCol.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1288\",\n" +
                "      \"rightname\": \"修改表现师人员的补充信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_queryQQById.action,sys/user_queryDymicColByUserid.action,sys/user_saveAddition.action\",\n" +
                "      \"sort\": \"26\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"711\",\n" +
                "      \"rightname\": \"查看修改后的表现师附加/补充信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_querySysUserInfoSh.action,sys/user_queryDymicColByUseridUpdate.action\",\n" +
                "      \"sort\": \"10\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"720\",\n" +
                "      \"rightname\": \"表现师分配角色\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_queryUserRole.action,sys/user_queryUserRole.action,sys/user_saveRole.action\",\n" +
                "      \"sort\": \"12\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"779\",\n" +
                "      \"rightname\": \"查看修改后的表现师附加基本信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_queryYhsh.action,sys/user_customerqueryUserYhshByUserid.action,randomaudit/randomaudit_queryLevel.action\",\n" +
                "      \"sort\": \"19\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"693\",\n" +
                "      \"rightname\": \"查看新增表现师\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_queryYhsh.action,sys/user_customerqueryUserYhshByUserid.action,sys/user_queryQQById.action\",\n" +
                "      \"sort\": \"8\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1480\",\n" +
                "      \"rightname\": \"能力配置审核\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_queryYhsh.action,yw/standardization_queryBxsAbilitySH.action\",\n" +
                "      \"sort\": \"1111\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1287\",\n" +
                "      \"rightname\": \"修改表现师人员的附加信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_saveAddition.action,sys/user_queryDymicColByUserid.action,sys/user_queryDymicColByUserid.action\",\n" +
                "      \"sort\": \"25\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"937\",\n" +
                "      \"rightname\": \"表现师离职信息录入\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_saveLzrydj.action\",\n" +
                "      \"sort\": \"22\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"11\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1111\",\n" +
                "      \"rightname\": \"角色共享\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_saveRole_shared.action,sys/zwj_getSharedUser.action\",\n" +
                "      \"sort\": \"1\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"870\",\n" +
                "      \"rightname\": \"图纸审核指定表现师\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_saveTzZdhty.action,sys/user_queryZdhty.action,sys/user_queryZdhty.action\",\n" +
                "      \"sort\": \"21\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"10131\",\n" +
                "      \"rightname\": \"家装组工资参数配置\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_saveWageInfo.action\",\n" +
                "      \"sort\": \"30\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1285\",\n" +
                "      \"rightname\": \"修改表现师人员的基本信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_update.action,sys/user_customerqueryUserByUserid.action,sys/user_adduser.action\",\n" +
                "      \"sort\": \"23\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"110\",\n" +
                "      \"rightname\": \"表现师修改\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_update.action,sys/user_queryQQById.action,sys/user_saveAddition.action,sys/user_queryDymicColByUserid.action,sys/user_querySysUserInfoSh.action,sys/user_UpdateAddition.action,sys/user_updateShBtg.action,sys/user_queryDymicColByUseridUpdate,sys/user_customerqueryUserByUserid.action,sys/user_queryDymicCol.action,randomaudit/randomaudit_queryLevel.action,sys/user_addFjjbxx.action\",\n" +
                "      \"sort\": \"4\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"743\",\n" +
                "      \"rightname\": \"审核表现师附加/补充信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_UpdateAddition.action,sys/user_updateShBtg.action\",\n" +
                "      \"sort\": \"15\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"780\",\n" +
                "      \"rightname\": \"审核修改后的表现师附加基本信息\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_updateUserFjjbxx.action,sys/user_updateUserShTg.action,sys/user_updateUserShbtg.action\",\n" +
                "      \"sort\": \"20\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"742\",\n" +
                "      \"rightname\": \"审核新增表现师\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/user_updateYhsh.action,sys/user_updateUserShbtg.action\",\n" +
                "      \"sort\": \"14\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"536\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"549\",\n" +
                "      \"rightname\": \"考勤汇总保存\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"sys/zwj_kqhzUpdate.action,sys/zwj_validateUserHaveSalary.action,sys/zwj_resetMoney.action\",\n" +
                "      \"sort\": \"3\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"1449\",\n" +
                "      \"rightname\": \"表现师能力配置\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"yw/standardization_saveBxsAbility.action,yw/standardization_queryBxsAbility.action\",\n" +
                "      \"sort\": \"30\",\n" +
                "      \"text\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"children\": [],\n" +
                "      \"id\": \"\",\n" +
                "      \"ispublic\": \"\",\n" +
                "      \"leaf\": false,\n" +
                "      \"parentid\": \"90\",\n" +
                "      \"parentname\": \"\",\n" +
                "      \"remark\": \"\",\n" +
                "      \"rightid\": \"282\",\n" +
                "      \"rightname\": \"读取自己工作量\",\n" +
                "      \"rightstate\": \"\",\n" +
                "      \"righttype\": \"\",\n" +
                "      \"righturl\": \"yw/yw_wddd_getWorkLog.action\",\n" +
                "      \"sort\": \"8\",\n" +
                "      \"text\": \"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"success\": true\n" +
                "}";
    }


}
