/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.pojo;

import cn.lfdevelopment.www.sys.base.BaseEntity;

import java.util.Date;

/**
 * Created by LiuFa on 2016/9/12.
 * cn.lfdevelopment.www.app.common.login.pojo
 * DevelopmentApp
 */
public class SysUser extends BaseEntity{
    //主键
    private Integer id;

    //用户登录名
    private String loginName;

    //用户登录密码
    private String password;

    //用户名
    private String realName;

    //用户类型
    private int type;

    //用户性别
    private String sex;

    //创建时间
    private Date createTime;

    //是否有效
    private Integer inUse;

    //昵称
    private String nickName;


    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter method for property loginName.
     *
     * @return property value of loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Setter method for property <tt>loginName</tt>.
     *
     * @param loginName value to be assigned to property loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * Getter method for property password.
     *
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     *
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property realName.
     *
     * @return property value of realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * Setter method for property <tt>realName</tt>.
     *
     * @param realName value to be assigned to property realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Getter method for property type.
     *
     * @return property value of type
     */
    public int getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Getter method for property sex.
     *
     * @return property value of sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Setter method for property <tt>sex</tt>.
     *
     * @param sex value to be assigned to property sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Getter method for property createTime.
     *
     * @return property value of createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Setter method for property <tt>createTime</tt>.
     *
     * @param createTime value to be assigned to property createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Getter method for property inUse.
     *
     * @return property value of inUse
     */
    public Integer getInUse() {
        return inUse;
    }

    /**
     * Setter method for property <tt>inUse</tt>.
     *
     * @param inUse value to be assigned to property inUse
     */
    public void setInUse(Integer inUse) {
        this.inUse = inUse;
    }

    /**
     * Getter method for property nickName.
     *
     * @return property value of nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Setter method for property <tt>nickName</tt>.
     *
     * @param nickName value to be assigned to property nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
