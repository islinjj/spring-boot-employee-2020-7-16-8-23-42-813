package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import com.thoughtworks.springbootemployee.utils.RelationMapperUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        return RelationMapperUtils.enAndDtoMapper(employeeResponseDto, employee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeResponseDto updateEmployee(int employeeId, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepository.findById(employeeId).get();
        if (employee != null) {
            BeanUtils.copyProperties(employeeRequestDto, employee);
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            employee = this.employeeRepository.save(employee);
            return RelationMapperUtils.enAndDtoMapper(employeeResponseDto, employee);
        }
        return null;
    }

    @Override
    public EmployeeResponseDto findEmployeeById(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            return RelationMapperUtils.enAndDtoMapper(employeeResponseDto, employee);
        }
        return null;
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
