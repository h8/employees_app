package com.alexample.employees.web;

import com.alexample.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatusController {

    private EmployeeService employeeService;

    @Autowired
    public StatusController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseBody
    @RequestMapping("/status")
    public String status() {
        return "<h1>Status is OK. " + employeeService.getCount() + " employees in DB.</h1>";
    }
}
