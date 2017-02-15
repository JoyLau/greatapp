package cn.lfdevelopment.www.app.dev.blog.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "blog_column")
public class BlogColumn {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 栏目名
     */
    private String name;

    /**
     * 栏目图标
     */
    private String icon;

    /**
     * 栏目排序
     */
    private Integer sort;

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
     * 获取栏目名
     *
     * @return name - 栏目名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置栏目名
     *
     * @param name 栏目名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取栏目图标
     *
     * @return icon - 栏目图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置栏目图标
     *
     * @param icon 栏目图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取栏目排序
     *
     * @return sort - 栏目排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置栏目排序
     *
     * @param sort 栏目排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}