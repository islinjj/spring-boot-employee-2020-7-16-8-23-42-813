package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 7:51 PM
 * @Version 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

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
        return Objects.requireNonNull(companyRepository.findById(companyId).orElse(null)).getEmployees();
    }

    @Override
    public CompanyResponseDto addCompany(Company company) {
        Company result = companyRepository.save(company);
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        BeanUtils.copyProperties(result,companyResponseDto);
        return companyResponseDto;
    }

    @Override
    public CompanyResponseDto updateCompany(int companyId, Company company) {
        company.setId(companyId);
        Company updateCompany = companyRepository.save(company);
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        BeanUtils.copyProperties(updateCompany,companyResponseDto);
        return companyResponseDto;
    }

    @Modifying
    @Override
    public void deleteEmployeesByCompanyId(int companyId) {
        companyRepository.deleteById(companyId);
    }
}
