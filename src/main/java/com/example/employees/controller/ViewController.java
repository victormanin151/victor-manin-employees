package com.example.employees.controller;

import com.example.employees.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    private final EmployeeService employeeService;

    public ViewController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/view")
    public String showResult(Model model) {

        var result = employeeService.calculateLongestWorkingPair();

        if (result == null) {
            model.addAttribute("pair", "No pair found");
            model.addAttribute("days", 0);
        } else {
            model.addAttribute("pair", result.getKey().empIds());
            model.addAttribute("days", result.getValue());
        }

        return "view";
    }
}
