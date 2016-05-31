package com.cheyipai.web.controller.user.api;

import com.cheyipai.service.user.bean.Role;
import com.cheyipai.service.user.service.RoleService;
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

@Controller
@RequestMapping("role/api")
public class RoleApiController extends AbstractController {

    final static Logger LOG = LoggerFactory.getLogger(RoleApiController.class);
    @Resource
    RoleService roleService;

    @RequestMapping("{id}")
    @ResponseBody
    public ResponseContent<Role> get(@PathVariable Long id) {
        try {
            Role role = roleService.get(id);
            if (role != null) {
                return new ResponseContent<Role>(ResponseEnum.OK, role);
            } else {
                return new ResponseContent<Role>(ResponseEnum.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.error("get error : {}", e.getMessage(), e);
            return new ResponseContent<Role>(ResponseEnum.EXCEPTION);
        }
    }

    @RequestMapping("list")
    @ResponseBody
    public TablePage<Role> list(@RequestParam(defaultValue = "1", required = false) int start,
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
            PageVo<Role> page = roleService.listByPage(sort, order, start, size);
            return new TablePage<Role>(draw, page.getTotalResults(), page.getTotalResults(), page.getResult());
        } catch (Exception e) {
            LOG.error("list error : {}", e.getMessage(), e);
            return new TablePage<Role>(draw, 0, 0, null);
        }
    }

    /**
     * 创建用户
     *
     * @param role
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public ResponseContent<Object> insert(Role role) {
        try {
            int result = roleService.insert(role);
            if (result > 0) {
                return new ResponseContent<>(ResponseEnum.OK);
            }
            return new ResponseContent(ResponseEnum.EXCEPTION);
        } catch (Exception e) {
            LOG.error("role insert error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }

    /**
     * 将角色给至用户组
     *
     * @param paramId
     * @return
     */
    @RequestMapping("role2GU")
    @ResponseBody
    public ResponseContent<Object> role2GU(Long rid, Long paramId, String action) {
        try {
            int result = 0;
            if (action.equals("group")) {
                result = roleService.role2Group(rid, paramId);
            } else if (action.equals("user")) {
                result = roleService.role2User(rid, paramId);
            }
            if (result > 0) {
                return new ResponseContent<>(ResponseEnum.OK);
            }
            return new ResponseContent(ResponseEnum.EXCEPTION);
        } catch (Exception e) {
            LOG.error("group insert error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }
}
