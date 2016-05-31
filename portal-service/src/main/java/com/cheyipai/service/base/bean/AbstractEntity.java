package com.cheyipai.service.base.bean;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.cheyipai.utils.annotation.Column;
import com.cheyipai.utils.annotation.Ignore;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Bean基类
 *
 * @author: zhanghanlin
 * @Date: 2016/4/15 10:26
 */
public abstract class AbstractEntity extends Object implements Serializable {
    private static final long serialVersionUID = 8837462308822725620L;

    //主键Id
    private Long id;


    @Column(name = "created_at")
    private Date createdAt;

    @JSONField(serialize = false)
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "changed_at")
    private Date changedAt;

    @JSONField(serialize = false)
    @Column(name = "changed_by")
    private String changedBy;

    @Ignore
    @JSONField(serialize = false)
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String toJson() {
        return JSONObject.toJSONString(this);
    }
}
