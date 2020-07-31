package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 7:42 PM
 * @Version 1.0
 */
public interface CompanyService {
    List<Company> findAllCompanies(Pageable pageable);

    Company findCompanyById(int companyId);

    List<Employee> findEmployeesByCompanyId(int companyId);

    CompanyResponseDto addCompany(Company company);

    void updateCompany(int companyId, Company company);

    void deleteEmployeesByCompanyId(int companyId);
}

