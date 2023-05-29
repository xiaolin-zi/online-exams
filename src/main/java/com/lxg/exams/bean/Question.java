package com.lxg.exams.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName question
 */
@TableName(value ="question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 题目
     */
    private String title;

    /**
     * 
     */
    private String optiona;

    /**
     * 
     */
    private String optionb;

    /**
     * 
     */
    private String optionc;

    /**
     * 
     */
    private String optiond;

    /**
     * 答案：选择题的话为ABCD,填空题为字符串
     */
    private String answer;

    /**
     * 类型：计网1，操作系统2，计组3，数据结构4
     */
    private Integer types;

    /**
     * 附加图片
     */
    private String image;

    /**
     * 题目备注
     */
    private String remark;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 熟练度
     */
    private Integer rank;

    /**
     * 是否公开：1是，0否（默认）
     */
    private Integer ispublic;




    private String updateTime;

    public String createTime;

    private Integer isdeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    //添加自定义字段
    @TableField(exist = false)
    private String username;

    //添加自定义字段
    @TableField(exist = false)
    private String userAvatar;


    //添加自定义字段
    @TableField(exist = false)
    private String typesName;




    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Question other = (Question) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getOptiona() == null ? other.getOptiona() == null : this.getOptiona().equals(other.getOptiona()))
            && (this.getOptionb() == null ? other.getOptionb() == null : this.getOptionb().equals(other.getOptionb()))
            && (this.getOptionc() == null ? other.getOptionc() == null : this.getOptionc().equals(other.getOptionc()))
            && (this.getOptiond() == null ? other.getOptiond() == null : this.getOptiond().equals(other.getOptiond()))
            && (this.getAnswer() == null ? other.getAnswer() == null : this.getAnswer().equals(other.getAnswer()))
            && (this.getTypes() == null ? other.getTypes() == null : this.getTypes().equals(other.getTypes()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getRank() == null ? other.getRank() == null : this.getRank().equals(other.getRank()))
            && (this.getIspublic() == null ? other.getIspublic() == null : this.getIspublic().equals(other.getIspublic()))
            && (this.getIsdeleted() == null ? other.getIsdeleted() == null : this.getIsdeleted().equals(other.getIsdeleted()))    ;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getOptiona() == null) ? 0 : getOptiona().hashCode());
        result = prime * result + ((getOptionb() == null) ? 0 : getOptionb().hashCode());
        result = prime * result + ((getOptionc() == null) ? 0 : getOptionc().hashCode());
        result = prime * result + ((getOptiond() == null) ? 0 : getOptiond().hashCode());
        result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
        result = prime * result + ((getTypes() == null) ? 0 : getTypes().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getRank() == null) ? 0 : getRank().hashCode());
        result = prime * result + ((getIspublic() == null) ? 0 : getIspublic().hashCode());
        result = prime * result + ((getIsdeleted() == null) ? 0 : getIsdeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", optiona=").append(optiona);
        sb.append(", optionb=").append(optionb);
        sb.append(", optionc=").append(optionc);
        sb.append(", optiond=").append(optiond);
        sb.append(", answer=").append(answer);
        sb.append(", types=").append(types);
        sb.append(", image=").append(image);
        sb.append(", remark=").append(remark);
        sb.append(", uid=").append(uid);
        sb.append(", rank=").append(rank);
        sb.append(", ispublic=").append(ispublic);
        sb.append(", isdeleted=").append(isdeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}