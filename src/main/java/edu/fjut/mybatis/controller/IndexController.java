package edu.fjut.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面跳转控制器
 */
@Controller
public class IndexController {

    /** 根路径重定向到登录页 */
    @GetMapping("/")
    public String index() {
        return "redirect:/login.html";
    }
}
