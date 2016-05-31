package com.cheyipai.web.shiro.realm;

import com.alibaba.fastjson.JSONObject;
import com.cheyipai.service.user.bean.User;
import com.cheyipai.service.user.service.UserService;
import com.cheyipai.utils.string.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.cheyipai.utils.Constants.current_user_key;

/**
 * @author: zhanghanlin
 * @Date: 2016/4/19 13:35
 */
public class UserRealm extends CasRealm {

    static final Logger LOG = LoggerFactory.getLogger(UserRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
        List list = principalCollection.asList();
        if (list != null && !list.isEmpty()) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("user info : {}", JSONObject.toJSONString(list));
            }
            try {
                User user = getUser(list);
                Subject currentUser = SecurityUtils.getSubject();
                currentUser.getSession().setAttribute(current_user_key, user);
            } catch (Exception e) {
                LOG.error("doGetAuthorizationInfo set user to session error : {}", e.getMessage(), e);
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        addRoles(simpleAuthorizationInfo, split(getDefaultRoles()));
        return simpleAuthorizationInfo;
    }

    @Resource
    UserService userService;

    private User getUser(List list) {
        String userName = list.get(0).toString();
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            Map<String, Object> map = (Map<String, Object>) list.get(1);
            String displayName = map.get("displayName").toString();
            String mail = map.get("mail") == null ? "" : map.get("mail").toString();
            String phone = map.get("phone") == null ? "" : map.get("phone").toString();
            user = new User();
            user.setUserName(userName);
            user.setDisplayName(displayName);
            user.setPhone(phone);
            user.setEmail(mail);
            int result = userService.insert(user);
            if (result > 0) {
                user = userService.getUserByUserName(userName);
            }
        }
        return user;
    }

    protected List split(String s) {
        List list = new ArrayList();
        String elements[] = StringUtils.split(s, ',');
        if (elements != null && elements.length > 0) {
            String arr$[] = elements;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
                String element = arr$[i$];
                if (StringUtils.hasText(element))
                    list.add(element.trim());
            }
        }
        return list;
    }

    protected void addRoles(SimpleAuthorizationInfo simpleAuthorizationInfo, List roles) {
        String role;
        for (Iterator i$ = roles.iterator(); i$.hasNext(); simpleAuthorizationInfo.addRole(role))
            role = (String) i$.next();

    }
}
