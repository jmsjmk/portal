package com.cheyipai.service.user.bean;

import com.cheyipai.service.base.bean.AbstractEntity;
import com.cheyipai.utils.annotation.Table;

/**
 * @author: zhanghanlin
 * @Date: 2016/4/21 13:18
 */
@Table(name = "M_ROLE_PERMISSION")
public class RolePermission extends AbstractEntity {

    private static final long serialVersionUID = -937348095659503995L;

    private Long roleId;

    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
