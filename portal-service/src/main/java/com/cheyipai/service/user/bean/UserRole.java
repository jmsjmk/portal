package com.cheyipai.service.user.bean;

import com.cheyipai.service.base.bean.AbstractEntity;
import com.cheyipai.utils.annotation.Table;

/**
 * @author: zhanghanlin
 * @Date: 2016/4/21 13:17
 */
@Table(name = "M_USER_ROLE")
public class UserRole extends AbstractEntity {

    private static final long serialVersionUID = 4989266856582299334L;

    private Long userId;

    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
