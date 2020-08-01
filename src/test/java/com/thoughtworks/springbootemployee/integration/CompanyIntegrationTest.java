package com.thoughtworks.springbootemployee.integration;

import com.alibaba.fastjson.JSONArray;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void clearDataBase() {
        companyRepository.deleteAll();
    }

    @Test
    void should_return_1_company_when_add_company_given_1_company_request_dto() throws Exception {
        String companyRequestDtoJson = "{ \"name\" : \"oocl\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/companies").contentType(MediaType.APPLICATION_JSON).content(companyRequestDtoJson));
        Company company = companyRepository.findAll().get(0);
        Assertions.assertEquals("oocl", company.getName());
    }

    @Test
    void should_return_updated_company_when_update_company_given_new_company_request_dto() throws Exception {
        Company company = new Company();
        company.setName("oocl");
        companyRepository.save(company);
        String newCompanyRequestDtoJson = "{ \"name\" : \"tw\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/companies/" + company.getId()).contentType(MediaType.APPLICATION_JSON).content(newCompanyRequestDtoJson));
        Company newCompany = companyRepository.findAll().get(0);
        Assertions.assertEquals("tw", newCompany.getName());
    }

    @Test
    void should_return_none_when_delete_company_given_company_id() throws Exception {
        Company company = new Company();
        company.setName("oocl");
        companyRepository.save(company);
        List<Company> companies = companyRepository.findAll();
        Company selectedCompany = companies.stream().filter(item -> item.getName().equals("oocl")).findFirst().orElse(null);
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/companies/%d", selectedCompany.getId())));
        companies = companyRepository.findAll();
        Assertions.assertEquals(0, companies.size());
    }

    @Test
    void should_return_1_company_when_find_company_by_page_given_page_0_and_size_1() throws Exception {
        Company company = new Company();
        company.setName("oocl");
        companyRepository.save(company);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/companies?page=0&size=1")).andReturn();
        List<Company> companies = JSONArray.parseArray(mvcResult.getResponse().getContentAsString(), Company.class);
        Assertions.assertEquals(1, companies.size());
    }

    @Test
    void should_return_1_employee_when_finding_in_company_given_company_id() throws Exception {
        Company company = new Company("oocl");
        companyRepository.save(company);
        Company selectedCompany = companyRepository.findAll().get(0);
        Employee employee = new Employee(22, "sam", "female");
        employee.setCompany(selectedCompany);
        employeeRepository.save(employee);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/companies/%d/employees", selectedCompany.getId()))).andReturn();
        List<Employee> employees = JSONArray.parseArray(result.getResponse().getContentAsString(), Employee.class);

        Assertions.assertEquals(1, employees.size());
    }
}
