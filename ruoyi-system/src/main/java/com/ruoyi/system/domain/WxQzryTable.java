package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 求职人员信息对象 wx_qzry_table
 * 
 * @author lgh
 * @date 2022-11-23
 */
public class WxQzryTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 手机号（微信关联） */
    @Excel(name = "手机号", readConverterExp = "微=信关联")
    private String mobile;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNumber;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 性别（0 女，1 男） */
    @Excel(name = "性别", readConverterExp = "0=,女=，1,男=")
    private String sex;

    /** 婚姻状况（0 已婚，1 未婚） */
    @Excel(name = "婚姻状况", readConverterExp = "0=,已=婚，1,未=婚")
    private String maritalStatus;

    /** 户籍地址 */
    @Excel(name = "户籍地址")
    private String hjAddress;

    /** 居住地址 */
    @Excel(name = "居住地址")
    private String jzAddress;

    /** 求职id */
    @Excel(name = "求职id")
    private Long jobId;

    /** 岗位 */
    @Excel(name = "岗位")
    private String post;

    /** 学历 */
    @Excel(name = "学历")
    private String xl;

    /** 毕业学校 */
    @Excel(name = "毕业学校")
    private String school;

    /** 技能职称 */
    @Excel(name = "技能职称")
    private String jnzc;

    /** 期望待遇（上） */
    @Excel(name = "期望待遇", readConverterExp = "上=")
    private String qwdyUp;

    /** 期望待遇（上） */
    @Excel(name = "期望待遇", readConverterExp = "上=")
    private String qwdyDown;

    /** 能否接受上夜班（0 能，1 否） */
    @Excel(name = "能否接受上夜班", readConverterExp = "0=,能=，1,否=")
    private String ifNightShift;

    /** 是否住宿（0 是，1 否） */
    @Excel(name = "是否住宿", readConverterExp = "0=,是=，1,否=")
    private String ifZs;

    /** 学习经历 */
    @Excel(name = "学习经历")
    private String learningExperience;

    /** 工作简历 */
    @Excel(name = "工作简历")
    private String jobResume;

    /** 是否发布（0 是，1 否） */
    @Excel(name = "是否发布", readConverterExp = "0=,是=，1,否=")
    private String ifPublish;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIdNumber(String idNumber) 
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber() 
    {
        return idNumber;
    }
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setMaritalStatus(String maritalStatus) 
    {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() 
    {
        return maritalStatus;
    }
    public void setHjAddress(String hjAddress) 
    {
        this.hjAddress = hjAddress;
    }

    public String getHjAddress() 
    {
        return hjAddress;
    }
    public void setJzAddress(String jzAddress) 
    {
        this.jzAddress = jzAddress;
    }

    public String getJzAddress() 
    {
        return jzAddress;
    }
    public void setJobId(Long jobId) 
    {
        this.jobId = jobId;
    }

    public Long getJobId() 
    {
        return jobId;
    }
    public void setPost(String post) 
    {
        this.post = post;
    }

    public String getPost() 
    {
        return post;
    }
    public void setXl(String xl) 
    {
        this.xl = xl;
    }

    public String getXl() 
    {
        return xl;
    }
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }
    public void setJnzc(String jnzc) 
    {
        this.jnzc = jnzc;
    }

    public String getJnzc() 
    {
        return jnzc;
    }
    public void setQwdyUp(String qwdyUp) 
    {
        this.qwdyUp = qwdyUp;
    }

    public String getQwdyUp() 
    {
        return qwdyUp;
    }
    public void setQwdyDown(String qwdyDown) 
    {
        this.qwdyDown = qwdyDown;
    }

    public String getQwdyDown() 
    {
        return qwdyDown;
    }
    public void setIfNightShift(String ifNightShift) 
    {
        this.ifNightShift = ifNightShift;
    }

    public String getIfNightShift() 
    {
        return ifNightShift;
    }
    public void setIfZs(String ifZs) 
    {
        this.ifZs = ifZs;
    }

    public String getIfZs() 
    {
        return ifZs;
    }
    public void setLearningExperience(String learningExperience) 
    {
        this.learningExperience = learningExperience;
    }

    public String getLearningExperience() 
    {
        return learningExperience;
    }
    public void setJobResume(String jobResume) 
    {
        this.jobResume = jobResume;
    }

    public String getJobResume() 
    {
        return jobResume;
    }
    public void setIfPublish(String ifPublish) 
    {
        this.ifPublish = ifPublish;
    }

    public String getIfPublish() 
    {
        return ifPublish;
    }
    public void setPublishTime(Date publishTime) 
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime() 
    {
        return publishTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mobile", getMobile())
            .append("name", getName())
            .append("idNumber", getIdNumber())
            .append("age", getAge())
            .append("sex", getSex())
            .append("maritalStatus", getMaritalStatus())
            .append("hjAddress", getHjAddress())
            .append("jzAddress", getJzAddress())
            .append("jobId", getJobId())
            .append("post", getPost())
            .append("xl", getXl())
            .append("school", getSchool())
            .append("jnzc", getJnzc())
            .append("qwdyUp", getQwdyUp())
            .append("qwdyDown", getQwdyDown())
            .append("ifNightShift", getIfNightShift())
            .append("ifZs", getIfZs())
            .append("learningExperience", getLearningExperience())
            .append("jobResume", getJobResume())
            .append("ifPublish", getIfPublish())
            .append("publishTime", getPublishTime())
            .toString();
    }
}
