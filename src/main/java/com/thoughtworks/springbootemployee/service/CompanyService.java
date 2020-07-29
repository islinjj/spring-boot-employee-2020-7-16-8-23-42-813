package com.thoughtworks.springbootemployee.service;

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

    void addCompany(List<Integer> employeeIds);

    void updateCompany(int companyId, Company company);

    void deleteEmployeesByCompanyId(int companyId);
}

