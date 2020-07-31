package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void clearDataBase () {
        companyRepository.deleteAll();
    }
    @Test
    void should_return_1_company_when_add_company_given_1_company_request_dto() throws Exception {
        String companyRequestDtoJson = "{ \"name\" : \"oocl\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/companies").contentType(MediaType.APPLICATION_JSON).content(companyRequestDtoJson));
        Company company = companyRepository.findAll().get(0);
        Assertions.assertEquals("oocl",company.getName());
    }
}
