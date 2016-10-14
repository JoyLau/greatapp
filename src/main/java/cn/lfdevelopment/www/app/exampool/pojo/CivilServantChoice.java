/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.exampool.pojo;

import cn.lfdevelopment.www.sys.base.BaseEntity;

/**
 * Created by LiuFa on 2016/10/8.
 * cn.lfdevelopment.www.app.exampool.pojo
 * DevelopmentApp
 */
public class CivilServantChoice extends BaseEntity{
    private String title;
    private Integer type;
    private Integer is_image;
    private String image_url;
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private String answer_e;
    private String answer_f;
    private String answer_g;
    private String answer_h;
    private Integer answer_right;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIs_image() {
        return is_image;
    }

    public void setIs_image(Integer is_image) {
        this.is_image = is_image;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer_a() {
        return answer_a;
    }

    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    public String getAnswer_b() {
        return answer_b;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    public String getAnswer_c() {
        return answer_c;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    public String getAnswer_d() {
        return answer_d;
    }

    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }

    public String getAnswer_e() {
        return answer_e;
    }

    public void setAnswer_e(String answer_e) {
        this.answer_e = answer_e;
    }

    public String getAnswer_f() {
        return answer_f;
    }

    public void setAnswer_f(String answer_f) {
        this.answer_f = answer_f;
    }

    public String getAnswer_g() {
        return answer_g;
    }

    public void setAnswer_g(String answer_g) {
        this.answer_g = answer_g;
    }

    public String getAnswer_h() {
        return answer_h;
    }

    public void setAnswer_h(String answer_h) {
        this.answer_h = answer_h;
    }

    public Integer getAnswer_right() {
        return answer_right;
    }

    public void setAnswer_right(Integer answer_right) {
        this.answer_right = answer_right;
    }
}
