package com.cheyipai.service.user.service;

import com.cheyipai.service.base.service.AbstractService;
import com.cheyipai.service.user.bean.Role;
import com.cheyipai.service.user.dao.RoleDao;
import com.cheyipai.utils.page.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService extends AbstractService {

    final static Logger LOG = LoggerFactory.getLogger(RoleService.class);

    @Resource
    RoleDao roleDao;


    @Override
    public Role get(Long id) {
        return roleDao.get(id);
    }

    public int insert(Role t) {
        setAbstractDao(roleDao);
        return super.insert(t);
    }

    public PageVo<Role> listByPage(String sort, String order, int start, int size) {
        setAbstractDao(roleDao);
        return super.listByPage(sort, order, start, size, Role.class);
    }

    public int role2User(Long rid, Long uid) {
        return roleDao.role2User(rid, uid);
    }

    public int role2Group(Long rid, Long gid) {
        return roleDao.role2Group(rid, gid);
    }
}
