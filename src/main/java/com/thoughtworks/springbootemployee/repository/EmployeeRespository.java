package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRespository {

    List<Employee> findAll();

    Employee findById(String employeeId);

    void delete(String employeeId);

    void update(Employee employee);
}
