package com.cheyipai.service.user.dao;

import com.cheyipai.service.base.dao.AbstractDao;
import com.cheyipai.service.user.bean.Permission;
import com.cheyipai.service.user.bean.RolePermission;
import com.cheyipai.service.utils.CurrentUser;
import com.cheyipai.utils.reflect.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class PermissionDao extends AbstractDao<Permission> {

    final static Logger LOG = LoggerFactory.getLogger(PermissionDao.class);

    /**
     * 查询所有状态正常的权限列表
     *
     * @return
     */
    public List<Permission> all() {
        String sql = "SELECT * FROM M_PERMISSION WHERE STATUS = 0 ORDER BY code asc";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Permission.class));
    }

    public int permission2Role(Long pid, Long rid) {
        RolePermission rolePermission = getRolePermission(pid, rid);
        if (rolePermission == null) {
            RolePermission rp = new RolePermission();
            Date date = new Date();
            rp.setChangedBy(CurrentUser.userName());
            rp.setCreatedAt(date);
            rp.setChangedAt(date);
            rp.setCreatedBy(CurrentUser.userName());
            rp.setPermissionId(pid);
            rp.setRoleId(rid);
            String sql = ReflectUtils.getInsertSQL(rp);
            Object[] objs = ReflectUtils.getBeanValue(rp).toArray();
            return jdbcTemplate.update(sql, objs);
        }
        return 0;
    }

    private RolePermission getRolePermission(Long pid, Long rid) {
        String sql = "SELECT * FROM " + ReflectUtils.class2Table(RolePermission.class)
                + " WHERE PERMISSION_ID = ? AND ROLE_ID = ?";
        List<RolePermission> list = jdbcTemplate.query(sql, new Object[]{pid, rid},
                BeanPropertyRowMapper.newInstance(RolePermission.class));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
