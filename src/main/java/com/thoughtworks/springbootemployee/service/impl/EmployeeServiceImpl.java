package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.BeanUtils;
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

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRequestDto.toEntity();
        Company targetCompany = companyRepository.findById(employeeRequestDto.getCompanyId()).orElse(null);
        employee.setCompany(targetCompany);
        employeeRepository.save(employee);
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(employee, new EmployeeResponseDto());
        return employeeResponseDto;
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeRepository.deleteById(employeeId);
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
    public Page<Employee> findAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> findEmployeeByGender(String employeeGender) throws Exception {
        if (StringUtils.isEmpty(employeeGender)) {
            throw new Exception();
        }
        return employeeRepository.findByGender(employeeGender);
    }
}
