package cc.antx.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @RequestMapping(value = "/dns/domainList")
    public String showRecordPage(Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        return "index";
    }
}

