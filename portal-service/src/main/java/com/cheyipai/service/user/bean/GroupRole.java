package com.cheyipai.service.user.bean;

import com.cheyipai.service.base.bean.AbstractEntity;
import com.cheyipai.utils.annotation.Table;

/**
 * Created by andy on 16/4/22.
 */
@Table(name = "M_GROUP_ROLE")
public class GroupRole extends AbstractEntity {
    private static final long serialVersionUID = 2789253112517106979L;

    private Long groupId;

    private Long roleId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
