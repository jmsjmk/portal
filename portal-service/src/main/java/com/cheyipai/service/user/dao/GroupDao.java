package com.cheyipai.service.user.dao;


import com.cheyipai.service.base.dao.AbstractDao;
import com.cheyipai.service.user.bean.Group;
import com.cheyipai.service.user.bean.GroupUser;
import com.cheyipai.service.utils.CurrentUser;
import com.cheyipai.utils.reflect.ReflectUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class GroupDao extends AbstractDao<Group> {

    public GroupUser getGroupUser(Long gid, Long uid) {
        String sql = "SELECT * FROM " + ReflectUtils.class2Table(GroupUser.class) + " WHERE GROUP_ID = ? AND USER_ID = ?";
        List<GroupUser> list = jdbcTemplate.query(sql, new Object[]{gid, uid},
                BeanPropertyRowMapper.newInstance(GroupUser.class));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public int user2Group(Long gid, Long uid) {
        GroupUser groupUser = getGroupUser(gid, uid);
        if (groupUser == null) {
            GroupUser gu = new GroupUser();
            Date date = new Date();
            gu.setChangedBy(CurrentUser.userName());
            gu.setCreatedAt(date);
            gu.setChangedAt(date);
            gu.setCreatedBy(CurrentUser.userName());
            gu.setGroupId(gid);
            gu.setUserId(uid);
            String sql = ReflectUtils.getInsertSQL(gu);
            Object[] objs = ReflectUtils.getBeanValue(gu).toArray();
            return jdbcTemplate.update(sql, objs);
        }
        return 0;
    }
}
