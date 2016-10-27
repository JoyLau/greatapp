package cn.lfdevelopment.www.app.common.ueditor.pojo;

import javax.persistence.*;

@Table(name = "ueditor_config")
public class UeditorConfig {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ueditor的全局配置
     */
    @Column(name = "global_config")
    private String globalConfig;

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
     * 获取ueditor的全局配置
     *
     * @return global_config - ueditor的全局配置
     */
    public String getGlobalConfig() {
        return globalConfig;
    }

    /**
     * 设置ueditor的全局配置
     *
     * @param globalConfig ueditor的全局配置
     */
    public void setGlobalConfig(String globalConfig) {
        this.globalConfig = globalConfig;
    }
}