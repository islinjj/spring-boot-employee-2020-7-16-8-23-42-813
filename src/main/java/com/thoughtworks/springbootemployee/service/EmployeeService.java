package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 4:53 PM
 * @Version 1.0
 */
public interface EmployeeService {
    void addEmployee(Employee employee);

    void deleteEmployeeById(int employeeId);

    void updateEmployee(Employee employee);

    Employee findEmployeeById(int employeeId);

    List<Employee> findAllEmployees();

    List<Employee> findEmployeeByGender(String employeeGender);

}
