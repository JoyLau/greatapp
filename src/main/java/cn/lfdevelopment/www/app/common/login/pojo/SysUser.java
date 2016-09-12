/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.pojo;

import java.util.Date;

/**
 * Created by LiuFa on 2016/9/12.
 * cn.lfdevelopment.www.app.common.login.pojo
 * DevelopmentApp
 */
public class SysUser {
    //主键
    private Integer id;

    //用户登录名
    private String userLoginName;

    //用户登录密码
    private String userPassword;

    //用户名
    private String userName;

    //用户类型
    private int userType;

    //用户性别
    private String sex;

    //创建时间
    private Date createTime;

    //是否有效
    private Integer inUse;

    //昵称
    private String nickName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getInUse() {
        return inUse;
    }

    public void setInUse(Integer inUse) {
        this.inUse = inUse;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Getter method for property userType.
     *
     * @return property value of userType
     */
    public int getUserType() {
        return userType;
    }

    /**
     * Setter method for property <tt>userType</tt>.
     *
     * @param userType value to be assigned to property userType
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }
}
