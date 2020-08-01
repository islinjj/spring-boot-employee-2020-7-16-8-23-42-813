package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 4:53 PM
 * @Version 1.0
 */
public interface EmployeeService {
    EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto);

    void deleteEmployeeById(int employeeId);

    EmployeeResponseDto updateEmployee(int employeeId, EmployeeRequestDto employeeRequestDto);

    Employee findEmployeeById(int employeeId);

    Page<Employee> findAllEmployees(Pageable pageable);

    List<Employee> findEmployeeByGender(String employeeGender) throws Exception;

}
