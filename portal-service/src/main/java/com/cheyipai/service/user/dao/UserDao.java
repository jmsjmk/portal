package com.cheyipai.service.user.dao;

import com.cheyipai.service.base.dao.AbstractDao;
import com.cheyipai.service.user.bean.GroupUser;
import com.cheyipai.service.user.bean.User;
import com.cheyipai.utils.reflect.ReflectUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends AbstractDao<User> {

    public List<User> getUsersByGroup(Long gid) {
        String sql = "SELECT u.* FROM " + ReflectUtils.class2Table(User.class)
                + " AS u ," + ReflectUtils.class2Table(GroupUser.class)
                + " AS g WHERE u.id = g.user_id AND g.group_id = ?";
        return jdbcTemplate.query(sql, new Object[]{gid}, BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getUserByUserName(String userName) {
        String sql = "SELECT * FROM " + ReflectUtils.class2Table(User.class)
                + " WHERE user_name = ?";
        List<User> list = jdbcTemplate.query(sql, new Object[]{userName}, BeanPropertyRowMapper.newInstance(User.class));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;


    }
}
