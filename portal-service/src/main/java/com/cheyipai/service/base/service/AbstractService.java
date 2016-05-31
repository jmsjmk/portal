package com.cheyipai.service.base.service;

import com.cheyipai.service.base.bean.AbstractEntity;
import com.cheyipai.service.base.dao.AbstractDao;
import com.cheyipai.utils.page.PageVo;
import com.cheyipai.utils.reflect.ReflectUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class AbstractService {


    final static Logger LOG = LoggerFactory.getLogger(AbstractService.class);

    private AbstractDao abstractDao;

    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    /**
     * 根据ID获取对象信息
     *
     * @param id
     * @return
     */
    public abstract <T> T get(Long id);

    /**
     * 插入
     *
     * @param t
     * @return
     */
    public int insert(AbstractEntity t) {
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        Date date = new Date();
        t.setChangedAt(date);
        t.setCreatedAt(date);
        t.setCreatedBy(currentUser);
        t.setChangedBy(currentUser);
        return abstractDao.insert(t);
    }

    public <T> PageVo<T> listByPage(String sort, String order, int start, int size, Class<T> clz) {
        List<T> list = new ArrayList<T>();
        int total = 0;
        try {
            Map<String, String> map = ReflectUtils.getFields(clz);
            if (map.containsKey(sort)) {
                list = abstractDao.listByPage(map.get(sort), order, start, size);
                total = abstractDao.getTotalCount();
            }
        } catch (Exception e) {
            LOG.error("list error : {}", e.getMessage(), e);
        }
        return new PageVo<T>((start / size) + 1, size, total, list);
    }
}
