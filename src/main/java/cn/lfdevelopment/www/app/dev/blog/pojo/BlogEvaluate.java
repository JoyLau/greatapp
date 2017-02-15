package cn.lfdevelopment.www.app.dev.blog.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "blog_evaluate")
public class BlogEvaluate {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 评价内容
     */
    private String text;

    /**
     * 评价时间
     */
    private Date time;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取昵称
     *
     * @return name - 昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置昵称
     *
     * @param name 昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取邮箱
     *
     * @return mail - 邮箱
     */
    public String getMail() {
        return mail;
    }

    /**
     * 设置邮箱
     *
     * @param mail 邮箱
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * 获取评价内容
     *
     * @return text - 评价内容
     */
    public String getText() {
        return text;
    }

    /**
     * 设置评价内容
     *
     * @param text 评价内容
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 获取评价时间
     *
     * @return time - 评价时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置评价时间
     *
     * @param time 评价时间
     */
    public void setTime(Date time) {
        this.time = time;
    }
}