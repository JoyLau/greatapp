package cn.lfdevelopment.www.app.dev.blog.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "blog_blog")
public class Blog {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 预览，导读
     */
    private String preview;

    /**
     * 总浏览量
     */
    private Integer pv;

    /**
     * 不明ip浏览量
     */
    @Column(name = "pv_null")
    private Integer pvNull;

    /**
     * 文章logo地址
     */
    private String logo;

    /**
     * 作者
     */
    private Integer author;

    /**
     * 所属栏目
     */
    private Integer column;

    /**
     * 文章来源
     */
    private String source;

    /**
     * 发布时间
     */
    private Date time;

    /**
     * 文章标签
     */
    private String tag;

    /**
     * 文章被赞数
     */
    private Integer praise;

    /**
     * 热门推荐 0 否 1 是
     */
    private Integer recommend;

    /**
     * 文章内容
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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取预览，导读
     *
     * @return preview - 预览，导读
     */
    public String getPreview() {
        return preview;
    }

    /**
     * 设置预览，导读
     *
     * @param preview 预览，导读
     */
    public void setPreview(String preview) {
        this.preview = preview;
    }

    /**
     * 获取总浏览量
     *
     * @return pv - 总浏览量
     */
    public Integer getPv() {
        return pv;
    }

    /**
     * 设置总浏览量
     *
     * @param pv 总浏览量
     */
    public void setPv(Integer pv) {
        this.pv = pv;
    }

    /**
     * 获取不明ip浏览量
     *
     * @return pv_null - 不明ip浏览量
     */
    public Integer getPvNull() {
        return pvNull;
    }

    /**
     * 设置不明ip浏览量
     *
     * @param pvNull 不明ip浏览量
     */
    public void setPvNull(Integer pvNull) {
        this.pvNull = pvNull;
    }

    /**
     * 获取文章logo地址
     *
     * @return logo - 文章logo地址
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置文章logo地址
     *
     * @param logo 文章logo地址
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public Integer getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(Integer author) {
        this.author = author;
    }

    /**
     * 获取所属栏目
     *
     * @return column - 所属栏目
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * 设置所属栏目
     *
     * @param column 所属栏目
     */
    public void setColumn(Integer column) {
        this.column = column;
    }

    /**
     * 获取文章来源
     *
     * @return source - 文章来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置文章来源
     *
     * @param source 文章来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取发布时间
     *
     * @return time - 发布时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置发布时间
     *
     * @param time 发布时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取文章标签
     *
     * @return tag - 文章标签
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置文章标签
     *
     * @param tag 文章标签
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取文章被赞数
     *
     * @return praise - 文章被赞数
     */
    public Integer getPraise() {
        return praise;
    }

    /**
     * 设置文章被赞数
     *
     * @param praise 文章被赞数
     */
    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    /**
     * 获取热门推荐 0 否 1 是
     *
     * @return recommend - 热门推荐 0 否 1 是
     */
    public Integer getRecommend() {
        return recommend;
    }

    /**
     * 设置热门推荐 0 否 1 是
     *
     * @param recommend 热门推荐 0 否 1 是
     */
    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    /**
     * 获取文章内容
     *
     * @return text - 文章内容
     */
    public String getText() {
        return text;
    }

    /**
     * 设置文章内容
     *
     * @param text 文章内容
     */
    public void setText(String text) {
        this.text = text;
    }
}