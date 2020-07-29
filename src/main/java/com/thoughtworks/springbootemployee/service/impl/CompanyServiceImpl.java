package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.utils.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 7:51 PM
 * @Version 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    List<Company> companies = new Vector<>();

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
        return findCompanyById(companyId).getEmployees();
    }

    @Override
    public void addCompany(Company company) {
        companies.add(company);
    }

    @Override
    public List<Company> findCompaniesByPage(int page, int pageSize) {
        return PageHelper.findByPage(page, pageSize, companies);
    }

    @Override
    public void updateCompany(int companyId, Company company) {
        for (int index = 0; index < companies.size(); index++) {
            if (companies.get(index).getId() == companyId) {
                companies.set(index, company);
                break;
            }
        }
    }

    @Override
    public void deleteEmployeesByCompanyId(int companyId) {
        for (int index = 0; index < companies.size(); index++) {
            Company company = companies.get(index);
            if (company.getId() == companyId) {
                company.setEmployees(new ArrayList<>());
                break;
            }
        }
    }
}
