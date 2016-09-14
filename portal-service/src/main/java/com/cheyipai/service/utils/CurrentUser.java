package com.cheyipai.service.utils;

import com.cheyipai.service.user.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import static com.cheyipai.utils.Constants.current_user_key;

public class CurrentUser {

    public static String userName() {
        return user().getUserName();
    }

    public static Long id() {
        return user().getId();
    }

    public static User user() {
//        Subject subject = SecurityUtils.getSubject();
//        return (User) subject.getSession().getAttribute(current_user_key);
        return null;
    }
}
