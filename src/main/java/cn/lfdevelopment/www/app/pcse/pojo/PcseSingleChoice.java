package cn.lfdevelopment.www.app.pcse.pojo;

import cn.lfdevelopment.www.sys.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "pcse_single_choice")
public class PcseSingleChoice extends BaseEntity{
    /**
     * 题目类型0:申论，1：行测
     */
    private Integer type;

    /**
     * 是否有图片
     */
    @Column(name = "is_image")
    private Integer isImage;

    /**
     * 图片地址<多个图片地址以分号隔开>
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 选项A
     */
    @Column(name = "answer_A")
    private String answerA;

    /**
     * 选项B
     */
    @Column(name = "answer_B")
    private String answerB;

    /**
     * 选项C
     */
    @Column(name = "answer_C")
    private String answerC;

    /**
     * 选项D
     */
    @Column(name = "answer_D")
    private String answerD;

    /**
     * 正确答案选项
     */
    @Column(name = "answer_right")
    private Integer answerRight;

    /**
     * 题目解析
     */
    private String meno;

    /**
     * 更新时间<在创建新记录和修改现有记录的时候都对这个数据列刷新>
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 题目
     */
    private String title;

    /**
     * 获取题目类型0:申论，1：行测
     *
     * @return type - 题目类型0:申论，1：行测
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置题目类型0:申论，1：行测
     *
     * @param type 题目类型0:申论，1：行测
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取是否有图片
     *
     * @return is_image - 是否有图片
     */
    public Integer getIsImage() {
        return isImage;
    }

    /**
     * 设置是否有图片
     *
     * @param isImage 是否有图片
     */
    public void setIsImage(Integer isImage) {
        this.isImage = isImage;
    }

    /**
     * 获取图片地址<多个图片地址以分号隔开>
     *
     * @return image_url - 图片地址<多个图片地址以分号隔开>
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片地址<多个图片地址以分号隔开>
     *
     * @param imageUrl 图片地址<多个图片地址以分号隔开>
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取选项A
     *
     * @return answer_A - 选项A
     */
    public String getAnswerA() {
        return answerA;
    }

    /**
     * 设置选项A
     *
     * @param answerA 选项A
     */
    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    /**
     * 获取选项B
     *
     * @return answer_B - 选项B
     */
    public String getAnswerB() {
        return answerB;
    }

    /**
     * 设置选项B
     *
     * @param answerB 选项B
     */
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    /**
     * 获取选项C
     *
     * @return answer_C - 选项C
     */
    public String getAnswerC() {
        return answerC;
    }

    /**
     * 设置选项C
     *
     * @param answerC 选项C
     */
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    /**
     * 获取选项D
     *
     * @return answer_D - 选项D
     */
    public String getAnswerD() {
        return answerD;
    }

    /**
     * 设置选项D
     *
     * @param answerD 选项D
     */
    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    /**
     * 获取正确答案选项
     *
     * @return answer_right - 正确答案选项
     */
    public Integer getAnswerRight() {
        return answerRight;
    }

    /**
     * 设置正确答案选项
     *
     * @param answerRight 正确答案选项
     */
    public void setAnswerRight(Integer answerRight) {
        this.answerRight = answerRight;
    }


    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    /**
     * 获取更新时间<在创建新记录和修改现有记录的时候都对这个数据列刷新>
     *
     * @return update_time - 更新时间<在创建新记录和修改现有记录的时候都对这个数据列刷新>
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间<在创建新记录和修改现有记录的时候都对这个数据列刷新>
     *
     * @param updateTime 更新时间<在创建新记录和修改现有记录的时候都对这个数据列刷新>
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取题目
     *
     * @return title - 题目
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置题目
     *
     * @param title 题目
     */
    public void setTitle(String title) {
        this.title = title;
    }
}