package com.ruoyi.discioline.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 *
 * @TableName yldjk_option
 */
@TableName(value ="yldjk_option")
public class YldjkOption implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 视频id
     */
    private String vid;

    /**
     * 小程序是否展示0：不展示1：展示
     */
    private Integer wxshow;

    /**
     * 皖政通是否展示0：不展示1：展示
     */
    private Integer wztshow;

    /**
     * 备注
     */
    private String common;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 视频id
     */
    public String getVid() {
        return vid;
    }

    /**
     * 视频id
     */
    public void setVid(String vid) {
        this.vid = vid;
    }

    /**
     * 小程序是否展示0：不展示1：展示
     */
    public Integer getWxshow() {
        return wxshow;
    }

    /**
     * 小程序是否展示0：不展示1：展示
     */
    public void setWxshow(Integer show) {
        this.wxshow = show;
    }

    /**
     * 皖政通是否展示0：不展示1：展示
     */
    public Integer getWztshow() {
        return wztshow;
    }

    /**
     * 皖政通是否展示0：不展示1：展示
     */
    public void setWztshow(Integer wztshow) {
        this.wztshow = wztshow;
    }

    /**
     * 备注
     */
    public String getCommon() {
        return common;
    }

    /**
     * 备注
     */
    public void setCommon(String common) {
        this.common = common;
    }

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
        YldjkOption other = (YldjkOption) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getVid() == null ? other.getVid() == null : this.getVid().equals(other.getVid()))
            && (this.getWxshow() == null ? other.getWxshow() == null : this.getWxshow().equals(other.getWxshow()))
            && (this.getWztshow() == null ? other.getWztshow() == null : this.getWztshow().equals(other.getWztshow()))
            && (this.getCommon() == null ? other.getCommon() == null : this.getCommon().equals(other.getCommon()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getVid() == null) ? 0 : getVid().hashCode());
        result = prime * result + ((getWxshow() == null) ? 0 : getWxshow().hashCode());
        result = prime * result + ((getWztshow() == null) ? 0 : getWztshow().hashCode());
        result = prime * result + ((getCommon() == null) ? 0 : getCommon().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sort=").append(sort);
        sb.append(", vid=").append(vid);
        sb.append(", wxshow=").append(wxshow);
        sb.append(", wztshow=").append(wztshow);
        sb.append(", common=").append(common);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
