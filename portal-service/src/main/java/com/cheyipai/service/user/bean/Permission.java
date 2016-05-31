package com.cheyipai.service.user.bean;

import com.cheyipai.service.base.bean.AbstractEntity;
import com.cheyipai.utils.annotation.Table;

/**
 * @author: zhanghanlin
 * @Date: 2016/4/21 13:15
 */
@Table(name = "M_PERMISSION")
public class Permission extends AbstractEntity {
    private static final long serialVersionUID = 3343974245724817114L;

    private String name;

    private String note;

    private String code;

    private String uniqueKey;

    private Long parentId;

    private String link;

    private int type;

    private String icon;

    private int weight;

    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
