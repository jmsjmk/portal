package com.cheyipai.service.user.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cheyipai.service.base.service.AbstractService;
import com.cheyipai.service.user.bean.Permission;
import com.cheyipai.service.user.dao.PermissionDao;
import com.cheyipai.utils.page.PageVo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class PermissionService extends AbstractService {

    final static Logger LOG = LoggerFactory.getLogger(PermissionService.class);

    @Resource
    PermissionDao permissionDao;

    @Override
    public Permission get(Long id) {
        return permissionDao.get(id);
    }

    public int insert(Permission permission) {
        setAbstractDao(permissionDao);
        return super.insert(permission);
    }

    public int update(Permission permission) {
        Date date = new Date();
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        permission.setChangedAt(date);
        permission.setChangedBy(currentUser);
        return permissionDao.update(permission);
    }

    public PageVo<Permission> listByPage(String sort, String order, int start, int size) {
        setAbstractDao(permissionDao);
        return super.listByPage(sort, order, start, size, Permission.class);
    }

    public JSONArray json() {
        JSONArray array = new JSONArray();
        List<Permission> list = permissionDao.all();
        for (Permission p : list) {
            JSONObject o = new JSONObject();
            o.put("id", p.getId());
            o.put("name", p.getName());
            o.put("pid", p.getParentId());
            array.add(o);
        }
        return array;
    }


    public Map<Object, List<Permission>> tree() {
        List<Permission> list = permissionDao.all();
        Map<Object, List<Permission>> map = new HashMap<>();
        for (Permission p : list) {
            List<Permission> subList = map.get(p.getParentId().toString());
            if (subList == null) {
                subList = new ArrayList<>();
            }
            subList.add(p);
            map.put(p.getParentId().toString(), subList);
        }
        return map;
    }

    public int permission2Role(Long pid, Long rid) {
        return permissionDao.permission2Role(pid, rid);
    }
}