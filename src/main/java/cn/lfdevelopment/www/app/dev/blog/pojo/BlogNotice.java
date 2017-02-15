package cn.lfdevelopment.www.app.dev.blog.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "blog_notice")
public class BlogNotice {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型，暂无
     */
    private Integer type;

    /**
     * 文本内容
     */
    private String text;

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
     * 获取类型，暂无
     *
     * @return type - 类型，暂无
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型，暂无
     *
     * @param type 类型，暂无
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取文本内容
     *
     * @return text - 文本内容
     */
    public String getText() {
        return text;
    }

    /**
     * 设置文本内容
     *
     * @param text 文本内容
     */
    public void setText(String text) {
        this.text = text;
    }
}