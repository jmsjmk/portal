package com.cheyipai.service.user.service;

import com.cheyipai.service.base.service.AbstractService;
import com.cheyipai.service.user.bean.Group;
import com.cheyipai.service.user.dao.GroupDao;
import com.cheyipai.utils.page.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户业类
 */
@Service
public class GroupService extends AbstractService {

    final static Logger LOG = LoggerFactory.getLogger(GroupService.class);

    @Resource
    GroupDao groupDao;


    @Override
    public Group get(Long id) {
        return groupDao.get(id);
    }

    public int insert(Group t) {
        setAbstractDao(groupDao);
        return super.insert(t);
    }

    public PageVo<Group> listByPage(String sort, String order, int start, int size) {
        setAbstractDao(groupDao);
        return super.listByPage(sort,order,start,size,Group.class);
    }

    public int user2Group(Long gid,Long uid) {
        return groupDao.user2Group(gid,uid);
    }

}