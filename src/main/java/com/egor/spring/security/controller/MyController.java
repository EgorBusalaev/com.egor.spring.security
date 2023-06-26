package com.egor.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @GetMapping("/")
    public String getInfoAllEmployee() {

        return "view_for_all_employees";
    }

    @GetMapping("/hr_info")
    public String getInfoHR() {
        return "view_for_hr";
    }
        @GetMapping("/manager_info")
        public String getInfoManager () {
            return "view_for_managers";
        }

}
