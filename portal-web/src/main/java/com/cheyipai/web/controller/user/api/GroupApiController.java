package com.cheyipai.web.controller.user.api;

import com.cheyipai.service.user.bean.Group;
import com.cheyipai.service.user.bean.User;
import com.cheyipai.service.user.service.GroupService;
import com.cheyipai.service.user.service.UserService;
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

@Controller
@RequestMapping("group/api")
public class GroupApiController extends AbstractController {

    final static Logger LOG = LoggerFactory.getLogger(GroupApiController.class);
    @Resource
    GroupService groupService;

    @Resource
    UserService userService;

    @RequestMapping("{id}")
    @ResponseBody
    public ResponseContent<Group> get(@PathVariable Long id) {
        try {
            Group group = groupService.get(id);
            if (group != null) {
                return new ResponseContent<Group>(ResponseEnum.OK, group);
            } else {
                return new ResponseContent<Group>(ResponseEnum.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.error("get error : {}", e.getMessage(), e);
            return new ResponseContent<Group>(ResponseEnum.EXCEPTION);
        }
    }

    @RequestMapping("list")
    @ResponseBody
    public TablePage<Group> list(@RequestParam(defaultValue = "1", required = false) int start,
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
            PageVo<Group> page = groupService.listByPage(sort, order, start, size);
            return new TablePage<Group>(draw, page.getTotalResults(), page.getTotalResults(), page.getResult());
        } catch (Exception e) {
            LOG.error("list error : {}", e.getMessage(), e);
            return new TablePage<Group>(draw, 0, 0, null);
        }
    }

    /**
     * 创建用户组
     *
     * @param group
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public ResponseContent<Object> insert(Group group) {
        try {
            int result = groupService.insert(group);
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
     * 将用户添加至用户组
     *
     * @param uid
     * @return
     */
    @RequestMapping("user2Group")
    @ResponseBody
    public ResponseContent<Object> user2Group(Long gid, Long uid) {
        try {
            int result = groupService.user2Group(gid, uid);
            if (result > 0) {
                return new ResponseContent<>(ResponseEnum.OK);
            }
            return new ResponseContent(ResponseEnum.EXCEPTION);
        } catch (Exception e) {
            LOG.error("group insert error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }

    @RequestMapping("users")
    @ResponseBody
    public ResponseContent<List<User>> users(Long gid) {
        try {
            List<User> list = userService.getUsersByGroup(gid);
            return new ResponseContent<>(ResponseEnum.OK, list);
        } catch (Exception e) {
            LOG.error("group insert error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }
}
