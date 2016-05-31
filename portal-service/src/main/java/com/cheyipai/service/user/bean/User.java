package com.cheyipai.service.user.bean;

import com.cheyipai.service.base.bean.AbstractEntity;
import com.cheyipai.utils.annotation.Table;

/**
 * 用户信息Bean
 *
 * @author: zhanghanlin
 * @Date: 2016/4/21 13:12
 */
@Table(name = "M_USER")
public class User extends AbstractEntity {

    private static final long serialVersionUID = -6881495143327331064L;

    private String userName;

    private String displayName;

    private String email;

    private String phone;

    private int status = 0;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
