package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 7:42 PM
 * @Version 1.0
 */
public interface CompanyService {
    List<Company> findAllCompanies();

    Company findCompanyById(int companyId);

    List<Employee> findEmployeesByCompanyId(int companyId);

    void addCompany(Company company);

    List<Company> findCompaniesByPage(int page, int pageSize);

    void updateCompany(int companyId, Company company);

    void deleteEmployeesByCompanyId(int companyId);
}
