package com.cheyipai.web.controller.user;

import com.cheyipai.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserController extends AbstractController {

    @RequestMapping("list")
    public ModelAndView list() {
        return new ModelAndView("user/userList");
    }

    @RequestMapping("role/list")
    public ModelAndView roleList() {
        return new ModelAndView("user/roleList");
    }

    @RequestMapping("group/list")
    public ModelAndView groupList() {
        return new ModelAndView("user/groupList");
    }

    @RequestMapping("permission/list")
    public ModelAndView permissionList() {
        return new ModelAndView("user/permissionList");
    }
}
