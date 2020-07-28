package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 7:51 PM
 * @Version 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    List<Company> companies = new ArrayList<Company>();

    @Override
    public List<Company> findAllCompanies() {
        return companies;
    }

    @Override
    public Company findCompanyById(int companyId) {
        return companies.stream().filter(company -> company.getId() == companyId).findFirst().orElse(null);
    }

    @Override
    public List<Employee> findEmployeesByCompanyId(int companyId) {
        return null;
    }

    @Override
    public void addCompany(Company company) {

    }

    @Override
    public List<Company> getCompaniesByPage() {
        return null;
    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteEmployeesByCompanyId(int companyId) {

    }
}
