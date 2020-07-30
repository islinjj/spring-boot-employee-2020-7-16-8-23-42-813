package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void should_return_ok_when_find_employees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")).andExpect(status().isOk());
    }

    @BeforeEach
    void clearWhenBeforeTest () {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_1_employee_when_add_given_1_employee() throws Exception {
        String employeeJson = "{\n" +
                "    \"age\": 22,\n" +
                "    \"name\": \"sam\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"companyId\" :1\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJson));
        Employee employee = employeeRepository.findById(1).orElse(null);

        Assertions.assertEquals(employee.getName(), "sam");
    }
}