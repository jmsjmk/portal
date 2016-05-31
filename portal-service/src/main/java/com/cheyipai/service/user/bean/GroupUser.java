package com.cheyipai.service.user.bean;

import com.cheyipai.service.base.bean.AbstractEntity;
import com.cheyipai.utils.annotation.Table;

/**
 * Created by andy on 16/4/22.
 */
@Table(name = "M_GROUP_USER")
public class GroupUser extends AbstractEntity {
    private static final long serialVersionUID = -791185109058120162L;

    private Long groupId;

    private Long userId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
