package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 4:45 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public void addEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.addEmployee(employeeRequestDto);
    }

    @GetMapping("/{employeeId}")
    public Employee findEmployeeById(@PathVariable("employeeId") int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable("employeeId") int employeeId,
                               @RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.updateEmployee(employeeId, employeeRequestDto);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeById(@PathVariable("employeeId") int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    @GetMapping
    public List<Employee> findEmployees(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "gender", required = false) String gender) {
        if (!StringUtils.isEmpty(gender)) {
            return employeeService.findEmployeeByGender(gender);
        }
        if (page != null && pageSize != null) {
            return employeeService.findEmployeeByPage(page, pageSize);
        }
        return employeeService.findAllEmployees();
    }
}
