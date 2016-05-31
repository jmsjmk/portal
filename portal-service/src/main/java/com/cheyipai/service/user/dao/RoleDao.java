package com.cheyipai.service.user.dao;

import com.cheyipai.service.base.dao.AbstractDao;
import com.cheyipai.service.user.bean.*;
import com.cheyipai.service.utils.CurrentUser;
import com.cheyipai.utils.reflect.ReflectUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RoleDao extends AbstractDao<Role> {

    public GroupRole getGroupRole(Long rid, Long gid) {
        String sql = "SELECT * FROM " + ReflectUtils.class2Table(GroupRole.class) + " WHERE ROLE_ID = ? AND GROUP_ID = ?";
        List<GroupRole> list = jdbcTemplate.query(sql, new Object[]{rid, gid},
                BeanPropertyRowMapper.newInstance(GroupRole.class));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


    public UserRole getUserRole(Long rid, Long uid) {
        String sql = "SELECT * FROM " + ReflectUtils.class2Table(UserRole.class) + " WHERE ROLE_ID = ? AND USER_ID = ?";
        List<UserRole> list = jdbcTemplate.query(sql, new Object[]{rid, uid},
                BeanPropertyRowMapper.newInstance(UserRole.class));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public int role2Group(Long rid, Long gid) {
        GroupRole groupRole = getGroupRole(rid, gid);
        if (groupRole == null) {
            GroupRole gr = new GroupRole();
            Date date = new Date();
            gr.setChangedBy(CurrentUser.userName());
            gr.setCreatedAt(date);
            gr.setChangedAt(date);
            gr.setCreatedBy(CurrentUser.userName());
            gr.setGroupId(gid);
            gr.setRoleId(rid);
            String sql = ReflectUtils.getInsertSQL(gr);
            Object[] objs = ReflectUtils.getBeanValue(gr).toArray();
            return jdbcTemplate.update(sql, objs);
        }
        return 0;
    }

    public int role2User(Long rid, Long uid) {
        UserRole userRole = getUserRole(rid, uid);
        if (userRole == null) {
            UserRole ur = new UserRole();
            Date date = new Date();
            ur.setChangedBy(CurrentUser.userName());
            ur.setCreatedAt(date);
            ur.setChangedAt(date);
            ur.setCreatedBy(CurrentUser.userName());
            ur.setRoleId(rid);
            ur.setUserId(uid);
            String sql = ReflectUtils.getInsertSQL(ur);
            Object[] objs = ReflectUtils.getBeanValue(ur).toArray();
            return jdbcTemplate.update(sql, objs);
        }
        return 0;
    }
}
