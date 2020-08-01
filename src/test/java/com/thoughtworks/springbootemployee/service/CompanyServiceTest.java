package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @InjectMocks
    CompanyServiceImpl companyService;

    @Mock
    CompanyRepository companyRepository;

    @Test
    void should_return_2_employee_when_finding_in_company_given_company_id() {
        //given
        int companyId = 1;
        Company company = initCompany();
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        //when
        List<Employee> result = companyService.findEmployeesByCompanyId(companyId);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void should_empty_employee_when_finding_in_company_given_error_company_id() {
        //given
        int companyId = 8;
        Company company = new Company();
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        //when
        List<Employee> result = companyService.findEmployeesByCompanyId(companyId);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void should_empty_employee_when_finding_in_company_given_company_id_and_empty_employees() {
        //given
        int companyId = 8;
        Company company = new Company();
        company.setEmployees(new ArrayList<>());
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        //when
        List<Employee> result = companyService.findEmployeesByCompanyId(companyId);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void should_return_1_company_response_dto_when_add_company_given_1_company() {
        //given
        Company company = new Company(1,new ArrayList<>(),"oocl");
        when(companyRepository.save(company)).thenReturn(company);

        //when
        CompanyResponseDto companyResponseDto = companyService.addCompany(company);

        //then
        Assertions.assertEquals(company.getName(),companyResponseDto.getName());
    }

    public Company initCompany() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            employees.add(new Employee(22 + i, "man", "male"));
        }
        Company company = new Company();
        company.setId(1);
        company.setEmployees(employees);
        return company;
    }
}