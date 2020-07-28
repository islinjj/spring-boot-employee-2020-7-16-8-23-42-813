package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        if (StringUtils.isEmpty(companyId)) {
            return null;
        }
        return Objects.requireNonNull(this.companies.stream()
                .filter(company -> companyId == company.getId())
                .findFirst()
                .orElse(null)).getEmployees();
    }

    @Override
    public void addCompany(Company company) {
        companies.add(company);
    }

    @Override
    public List<Company> getCompaniesByPage(int page, int pageSize) {
        List<Company> result = new ArrayList<>();
        if(companies.size() < (page - 1) * pageSize){
            return result;
        }
        int startIndex = (page - 1) * pageSize;
        int endIndex = page * pageSize - 1;
        result = companies.stream().skip(startIndex).limit(endIndex).collect(Collectors.toList());
        return result;
    }

    @Override
    public void updateCompany(Company company) {
        for (int index = 0; index < companies.size(); index++) {
            if (companies.get(index).getId() == company.getId()) {
                companies.set(index, company);
                break;
            }
        }
    }

    @Override
    public void deleteEmployeesByCompanyId(int companyId) {
        companies.remove(companies.stream()
                .filter(employee -> employee.getId() == companyId)
                .findFirst()
                .orElse(null));
    }
}
