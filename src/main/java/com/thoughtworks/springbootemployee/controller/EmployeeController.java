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

    @GetMapping(params = {"gender"})
    public List<Employee> findEmployeesByGender(String gender)
            throws Exception {
        return employeeService.findEmployeeByGender(gender);
    }

    @GetMapping()
    public Page<Employee> findAllEmployees(@PageableDefault Pageable pageable, @RequestParam (defaultValue = "false") boolean unpaged) {
        if (unpaged) {
            pageable = Pageable.unpaged();
        }
        return employeeService.findAllEmployees(pageable);
    }
}
