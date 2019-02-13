package ggoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home() {
        return "index";
    }

    @RequestMapping("/reports/report")
    public String reports() {
        return "/reports/report";
    }

    @RequestMapping("/villalist")
    public String list() {
        return "villalist";
    }

    @RequestMapping("/viewEmployeeDetails")
    public String viewEmployeeDetails() {
        return "viewEmployeeDetails";
    }
    @RequestMapping("/new")
    public String newEmployee() {
        return "new";
    }

    @RequestMapping("/details")
    public String details() {
        return "villaDetails";
    }

}
