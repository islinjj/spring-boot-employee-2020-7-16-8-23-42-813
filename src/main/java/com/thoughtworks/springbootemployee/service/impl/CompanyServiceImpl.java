package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 7:51 PM
 * @Version 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Company> findAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable).toList();
    }

    @Override
    public Company findCompanyById(int companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    @Override
    public List<Employee> findEmployeesByCompanyId(int companyId) {
        return companyRepository.findById(companyId).orElse(null).getEmployees();
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) {
        company.setId(companyId);
        companyRepository.save(company);
    }

    @Modifying
    @Override
    public void deleteEmployeesByCompanyId(int companyId) {
        companyRepository.deleteById(companyId);
    }
}
