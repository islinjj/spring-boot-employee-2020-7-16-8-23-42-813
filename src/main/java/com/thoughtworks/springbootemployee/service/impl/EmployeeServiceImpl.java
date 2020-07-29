package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import com.thoughtworks.springbootemployee.utils.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    List<Employee> employees = new Vector<>();

    @Override
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employees.remove(findEmployeeById(employeeId));
    }

    @Override
    public void updateEmployee(int employeeId, Employee employee) {
        for (int index = 0; index < employees.size(); index++) {
            if (employees.get(index).getId() == employeeId) {
                employees.set(index, employee);
                break;
            }
        }
    }

    @Override
    public Employee findEmployeeById(int employeeId) {
        return this.employees.stream().filter(employee -> employeeId == employee.getId()).findFirst().orElse(null);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return this.employees;
    }

    @Override
    public List<Employee> findEmployeeByGender(String employeeGender) {
        if (StringUtils.isEmpty(employeeGender)) {
            return null;
        }
        return employees.stream()
                .filter(employee -> employeeGender.equals(employee.getGender()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findEmployeeByPage(int page, int pageSize) {
        return PageHelper.findByPage(page, pageSize, employees);
    }
}
