package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRespository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import com.thoughtworks.springbootemployee.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 4:57 PM
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRespository employeeRespository;

    @Override
    public void addEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployeeById(int employeeId) {

    }

    @Override
    public void updateEmployee(int employeeId, Employee employee) {

    }

    @Override
    public Employee findEmployeeById(int employeeId) {
        return null;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return null;
    }

    @Override
    public List<Employee> findEmployeeByGender(String employeeGender) {
        List<Employee> employees = employeeRespository.findAll();
        return employees.stream().filter(employee -> employee.getGender().equals(employeeGender)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findEmployeeByPage(int page, int pageSize) {
        List<Employee> employees = employeeRespository.findAll();
        return PageHelper.findByPage(page, pageSize, employees);
    }
}
