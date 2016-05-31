package com.cheyipai.web.controller.user.api;

import com.cheyipai.service.user.bean.Permission;
import com.cheyipai.service.user.service.PermissionService;
import com.cheyipai.utils.page.PageVo;
import com.cheyipai.utils.string.StringUtils;
import com.cheyipai.web.controller.AbstractController;
import com.cheyipai.web.response.ResponseContent;
import com.cheyipai.web.response.ResponseEnum;
import com.cheyipai.web.vo.TablePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("permission/api")
public class PermissionApiController extends AbstractController {

    final static Logger LOG = LoggerFactory.getLogger(PermissionApiController.class);
    @Resource
    PermissionService permissionService;

    @RequestMapping("{id}")
    @ResponseBody
    public ResponseContent<Permission> get(@PathVariable Long id) {
        try {
            Permission permission = permissionService.get(id);
            if (permission != null) {
                return new ResponseContent<Permission>(ResponseEnum.OK, permission);
            } else {
                return new ResponseContent<Permission>(ResponseEnum.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.error("get error : {}", e.getMessage(), e);
            return new ResponseContent<Permission>(ResponseEnum.EXCEPTION);
        }
    }

    @RequestMapping("list")
    @ResponseBody
    public TablePage<Permission> list(@RequestParam(defaultValue = "1", required = false) int start,
                                      @RequestParam(defaultValue = "10", required = false) int size,
                                      @RequestParam(value = "order[0][column]", defaultValue = "0", required = false) String column,
                                      @RequestParam(value = "order[0][dir]", required = false) String dir,
                                      @RequestParam(defaultValue = "1", required = false) int draw,
                                      HttpServletRequest request) {
        try {
            String sort = "id";
            String order = "DESC";
            String columnName = request.getParameter("columns[" + column + "][data]");
            if (StringUtils.isNotBlank(columnName)) {
                sort = columnName;
            }
            if (StringUtils.isNotBlank(dir)) {
                order = dir;
            }
            PageVo<Permission> page = permissionService.listByPage(sort, order, start, size);
            return new TablePage<Permission>(draw, page.getTotalResults(), page.getTotalResults(), page.getResult());
        } catch (Exception e) {
            LOG.error("list error : {}", e.getMessage(), e);
            return new TablePage<Permission>(draw, 0, 0, null);
        }
    }

    /**
     * 创建权限
     *
     * @param permission
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public ResponseContent<Object> insert(Permission permission) {
        try {
            int result = permissionService.insert(permission);
            if (result > 0) {
                return new ResponseContent<>(ResponseEnum.OK);
            }
            return new ResponseContent(ResponseEnum.EXCEPTION);
        } catch (Exception e) {
            LOG.error("permission insert error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }

    /**
     * 更新权限
     *
     * @param permission
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResponseContent<Object> update(Permission permission) {
        try {
            int result = permissionService.update(permission);
            if (result > 0) {
                return new ResponseContent<>(ResponseEnum.OK);
            }
            return new ResponseContent(ResponseEnum.EXCEPTION);
        } catch (Exception e) {
            LOG.error("permission update error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }

    @RequestMapping("permission2Role")
    @ResponseBody
    public ResponseContent<Object> permission2Role(Long pid, Long rid) {
        try {
            int result = permissionService.permission2Role(pid, rid);

            if (result > 0) {
                return new ResponseContent<>(ResponseEnum.OK);
            }
            return new ResponseContent(ResponseEnum.EXCEPTION);
        } catch (Exception e) {
            LOG.error("group insert error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }

    /**
     * 输出权限JSON
     *
     * @return
     */
    @RequestMapping("tree")
    @ResponseBody
    public ResponseContent<Map<Object, List<Permission>>> tree() {
        try {
            return new ResponseContent<>(ResponseEnum.OK, permissionService.tree());
        } catch (Exception e) {
            LOG.error("json error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }
}
