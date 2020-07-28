package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 4:57 PM
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees = new ArrayList<>();

    @Override
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    @Override
    public Employee findEmployeeById(String employeeId) {
        if(StringUtils.isEmpty(employeeId)){
            return null;
        }
        return this.employees.stream().filter(employee -> employeeId.equals(employee.getId())).findFirst().get();
    }

    @Override
    public List<Employee> findAllEmployees() {
        return this.employees;
    }
}
