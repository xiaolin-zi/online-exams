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

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 题目
     */
    private String title;

    @TableField("optionA")
    private String optionA;
    @TableField("optionB")
    private String optionB;

    @TableField("optionC")
    private String optionC;
    @TableField("optionD")
    private String optionD;

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
     * 逻辑删除：1删除，0未删除
     */
    @TableField("isDeleted")
    private Integer isdeleted;

    private Integer uid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
            && (this.getOptionA() == null ? other.getOptionA() == null : this.getOptionA().equals(other.getOptionA()))
            && (this.getOptionB() == null ? other.getOptionB() == null : this.getOptionB().equals(other.getOptionB()))
            && (this.getOptionC() == null ? other.getOptionC() == null : this.getOptionC().equals(other.getOptionC()))
            && (this.getOptionD() == null ? other.getOptionD() == null : this.getOptionD().equals(other.getOptionD()))
            && (this.getAnswer() == null ? other.getAnswer() == null : this.getAnswer().equals(other.getAnswer()))
            && (this.getTypes() == null ? other.getTypes() == null : this.getTypes().equals(other.getTypes()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getIsdeleted() == null ? other.getIsdeleted() == null : this.getIsdeleted().equals(other.getIsdeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getOptionA() == null) ? 0 : getOptionA().hashCode());
        result = prime * result + ((getOptionB() == null) ? 0 : getOptionB().hashCode());
        result = prime * result + ((getOptionC() == null) ? 0 : getOptionC().hashCode());
        result = prime * result + ((getOptionD() == null) ? 0 : getOptionD().hashCode());
        result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
        result = prime * result + ((getTypes() == null) ? 0 : getTypes().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
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
        sb.append(", optiona=").append(optionA);
        sb.append(", optionb=").append(optionB);
        sb.append(", optionc=").append(optionC);
        sb.append(", optiond=").append(optionD);
        sb.append(", answer=").append(answer);
        sb.append(", types=").append(types);
        sb.append(", image=").append(image);
        sb.append(", remark=").append(remark);
        sb.append(", uid=").append(uid);
        sb.append(", isdeleted=").append(isdeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}