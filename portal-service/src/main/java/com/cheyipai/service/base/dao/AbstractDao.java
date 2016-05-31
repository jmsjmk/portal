package com.cheyipai.service.base.dao;

import com.cheyipai.utils.Constants;
import com.cheyipai.utils.reflect.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractDao<T> {

    static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);

    @Resource
    protected JdbcTemplate jdbcTemplate;


    /**
     * 插入
     *
     * @param t
     * @return
     */
    public int insert(T t) {
        String sql = ReflectUtils.getInsertSQL(t);
        Object[] objs = ReflectUtils.getBeanValue(t).toArray();
        return jdbcTemplate.update(sql, objs);
    }

    /**
     * 更新
     *
     * @param t
     * @return
     */
    public int update(T t) {
        String sql = ReflectUtils.getUpdateSQL(t);
        Object[] objs = ReflectUtils.getBeanValue(t).toArray();
        return jdbcTemplate.update(sql, objs);
    }

    /**
     * 根据Id获取对象
     *
     * @param id
     * @return
     */
    public T get(Long id) {
        String sql = "SELECT * FROM " + ReflectUtils.class2Table(tClass()) + " WHERE ID = ?";
        List<T> list = jdbcTemplate.query(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(tClass()));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 返回总行数量
     *
     * @return
     */
    public int getTotalCount() {
        String sql = "SELECT COUNT(ID) FROM " + ReflectUtils.class2Table(tClass());
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private Class<T> tClass() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<T>) params[0];
    }

    /**
     * 分页查询
     *
     * @param sort
     * @param order
     * @param start
     * @param size
     * @return
     */
    public List<T> listByPage(String sort, String order,
                              int start, int size) {
        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(ReflectUtils.class2Table(tClass()));
        sb.append(" ORDER BY ");
        sb.append(sort);
        sb.append(Constants.sql_split);
        sb.append(order);
        sb.append(Constants.sql_split);
        sb.append("LIMIT ?,?");
        List<T> list = jdbcTemplate.query(sb.toString(),
                new Object[]{start, size},
                BeanPropertyRowMapper.newInstance(tClass()));
        return list;
    }
}
