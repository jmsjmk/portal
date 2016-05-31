package com.cheyipai.utils.reflect;

import com.cheyipai.utils.annotation.Column;
import com.cheyipai.utils.annotation.Ignore;
import com.cheyipai.utils.annotation.Table;
import com.cheyipai.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by andy on 16/4/26.
 */
public class ReflectUtils {

    static final Logger LOG = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     * 获取Bean字段列表
     *
     * @param clz
     * @return
     */
    private static List<Field> fields(Class clz) {
        List<Field> list = new ArrayList<Field>();
        list.addAll(Arrays.asList(clz.getDeclaredFields()));
        if (clz.getGenericSuperclass() != null) {
            list.addAll(Arrays.asList(clz.getSuperclass().getDeclaredFields()));
        }
        return list;
    }

    /**
     * 拼装插入sql
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String getInsertSQL(T t) {
        String _table = class2Table(t.getClass());
        String strSQL = new String("INSERT INTO " + _table + " (#COLS) VALUES (#VALS)");
        StringBuffer cols = new StringBuffer("");
        StringBuffer values = new StringBuffer("");
        List<Field> fields = fields(t.getClass());
        for (Field field : fields) {
            String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Method method = t.getClass().getMethod(methodName);
                if (field.isAnnotationPresent(Ignore.class)) {
                    continue;
                }
                Object o = method.invoke(t);
                if (null != o) {
                    cols.append(field2Column(field) + ",");
                    values.append("?,");
                }
            } catch (Exception e) {
                LOG.error("getInsertSQL error:{}", e.getMessage(), e);
            }
        }
        if ((cols.length() > 1) && (values.length() > 1)) {
            cols.append("VERSION");
            values.append("1");
            strSQL = strSQL.replace("#COLS", cols).replace("#VALS", values);
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug(strSQL);
        }
        return strSQL;
    }

    /**
     * 通过bean对象获取插入时的SQL.<br/>
     *
     * @param t
     * @return
     * @author zhanghanlin
     * @since JDK 1.7
     */
    public static <T> String getUpdateSQL(T t) {
        String _table = class2Table(t.getClass());
        String strSQL = new String("UPDATE " + _table + " SET #UPDATE VERSION = VERSION + 1 WHERE ID = #ID");
        StringBuffer cols = new StringBuffer("");
        List<Field> fields = fields(t.getClass());
        Object id = null;
        for (Field field : fields) {
            String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                if (field.isAnnotationPresent(Ignore.class)) {
                    continue;
                }
                Method method = t.getClass().getMethod(methodName);
                Object o = method.invoke(t);
                if (null != o) {
                    if (field.getName().toUpperCase().equals("ID")) {
                        id = o;
                        continue;
                    }
                    cols.append(field2Column(field) + " = ?,");
                }
            } catch (Exception e) {
                LOG.error("getUpdateSQL error:{}", e.getMessage(), e);
            }
        }
        if (cols.length() > 1) {
            strSQL = strSQL.replace("#UPDATE", cols).replace("#ID", id.toString());
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug(strSQL);
        }
        return strSQL;
    }

    private static String field2Column(Field field) {
        if (field.isAnnotationPresent(Column.class)) {
            return field.getAnnotation(Column.class).name();
        }
        return StringUtils.camelCase2DB(field.getName());

    }

    public static <T> String class2Table(Class<T> clz) {
        if (clz.isAnnotationPresent(Table.class)) {
            return clz.getAnnotation(Table.class).name();
        }
        return StringUtils.camelCase2DB(clz.getSimpleName());
    }

    /**
     * 获得Bean中属性值
     *
     * @param t
     * @return
     */
    public static List<Object> getBeanValue(Object t) {
        List<Object> list = new ArrayList<Object>();
        List<Field> fields = fields(t.getClass());
        for (Field field : fields) {
            if (field.getName().toUpperCase().equals("ID")) {
                continue;
            }
            String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Method method = t.getClass().getMethod(methodName);
                if (method.isAnnotationPresent(Ignore.class)) {
                    continue;
                }
                Object o = method.invoke(t);
                if (null != o) {
                    list.add(o);
                }
            } catch (Exception e) {
                LOG.error("getSQLValues error:{}", e.getMessage(), e);
            }
        }
        return list;
    }

    /**
     * 获取Bean数据库映射字段列表
     *
     * @param clz
     * @return
     */
    public static Map<String, String> getFields(Class clz) {
        Map<String, String> map = new HashMap<String, String>();
        for (Field f : fields(clz)) {
            if (Modifier.isStatic(f.getModifiers())) {
                continue;
            }
            String value = "";
            if (f.isAnnotationPresent(Column.class)) {
                Column column = f.getAnnotation(Column.class);
                value = column.name();
            } else {
                value = StringUtils.camelCase2DB(f.getName());
            }
            map.put(f.getName(), value);
        }
        return map;
    }
}
