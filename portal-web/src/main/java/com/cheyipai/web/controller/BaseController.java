package com.cheyipai.web.controller;

import com.cheyipai.service.user.bean.User;
import com.cheyipai.service.utils.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhanghanlin
 * @Date: 2016/4/11 19:08
 */
@Controller
@RequestMapping
public class BaseController extends AbstractController {
    /**
     * 进入首页.<br/>
     *
     * @param request
     * @return
     * @author zhanghanlin
     * @since JDK 1.7
     */
    @RequestMapping
    public ModelAndView toMain(HttpServletRequest request) {
        User user = CurrentUser.user();
        LOG.info("toMain get session user : {}", user);
        return new ModelAndView("index");
    }
}
