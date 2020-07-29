package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void addEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRequestDto.toEntity();
        Company targetCompany = companyRepository.findAll().stream().filter(company -> company.getId() == employeeRequestDto.getCompanyId()).findFirst().get();
        employee.setCompany(targetCompany);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employees.remove(employees.stream()
                .filter(employee -> employee.getId() == employeeId)
                .findFirst()
                .orElse(null));
    }

    @Override
    public void updateEmployee(int employeeId, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRequestDto.toEntity();
        employee.setId(employeeId);
        Company company = companyRepository.findById(employeeRequestDto.getCompanyId()).orElse(null);
        employee.setCompany(company);
        employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
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
