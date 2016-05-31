package com.cheyipai.service.user.service;

import com.cheyipai.service.base.service.AbstractService;
import com.cheyipai.service.user.bean.User;
import com.cheyipai.service.user.dao.UserDao;
import com.cheyipai.service.utils.CurrentUser;
import com.cheyipai.utils.Status;
import com.cheyipai.utils.page.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户业类
 */
@Service
public class UserService extends AbstractService {

    final static Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserDao userDao;

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    /**
     * 插入
     *
     * @param t
     * @return
     */
    public int insert(User t) {
        setAbstractDao(userDao);
        return super.insert(t);
    }

    /**
     * 分页列表
     *
     * @param sort
     * @param order
     * @param start
     * @param size
     * @return
     */
    public PageVo<User> listByPage(String sort, String order, int start, int size) {
        setAbstractDao(userDao);
        return super.listByPage(sort, order, start, size, User.class);
    }

    public List<User> getUsersByGroup(Long gid) {
        return userDao.getUsersByGroup(gid);
    }

    public int delete(Long id) {
        User user = get(id);
        if (user != null) {
            user.setStatus(Status.DELETE);
            user.setChangedAt(new Date());
            user.setChangedBy(CurrentUser.userName());
            return userDao.update(user);
        }
        return 0;
    }

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }
}
