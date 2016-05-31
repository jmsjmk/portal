package com.cheyipai.web.controller.user.api;

import com.cheyipai.service.user.bean.User;
import com.cheyipai.service.user.service.UserService;
import com.cheyipai.utils.encrypt.Encrypt;
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
@RequestMapping("user/api")
public class UserApiController extends AbstractController {

    final static Logger LOG = LoggerFactory.getLogger(UserApiController.class);
    @Resource
    UserService userService;

    @RequestMapping("{id}")
    @ResponseBody
    public ResponseContent<User> get(@PathVariable Long id) {
        try {
            User user = userService.get(id);
            if (user != null) {
                return new ResponseContent<User>(ResponseEnum.OK, user);
            } else {
                return new ResponseContent<User>(ResponseEnum.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.error("get error : {}", e.getMessage(), e);
            return new ResponseContent<User>(ResponseEnum.EXCEPTION);
        }
    }

    @RequestMapping("list")
    @ResponseBody
    public TablePage<User> list(@RequestParam(defaultValue = "1", required = false) int start,
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
            PageVo<User> page = userService.listByPage(sort, order, start, size);
            return new TablePage<User>(draw, page.getTotalResults(), page.getTotalResults(), page.getResult());
        } catch (Exception e) {
            LOG.error("search error : {}", e.getMessage(), e);
            return new TablePage<User>(draw, 0, 0, null);
        }
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public ResponseContent<Object> insert(User user) {
        try {
            int result = userService.insert(user);
            if (result > 0) {
                return new ResponseContent<>(ResponseEnum.OK);
            }
            return new ResponseContent(ResponseEnum.EXCEPTION);
        } catch (Exception e) {
            LOG.error("user insert error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }
    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResponseContent<Object> delete(Long id) {
        try {
            int result = userService.delete(id);
            if (result > 0) {
                return new ResponseContent<>(ResponseEnum.OK);
            }
            return new ResponseContent(ResponseEnum.EXCEPTION);
        } catch (Exception e) {
            LOG.error("user delete error : {}", e.getMessage(), e);
            return new ResponseContent(ResponseEnum.EXCEPTION);
        }
    }

    public static void main(String[] args) {
        System.out.print(Encrypt.MD5("admin"));
    }
}