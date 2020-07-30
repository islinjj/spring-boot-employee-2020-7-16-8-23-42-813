package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;


    @AfterEach
    void clearWhenBeforeTest() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_ok_when_find_employees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")).andExpect(status().isOk());
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
        Employee employee = employeeRepository.findAll().get(0);

        Assertions.assertEquals("sam", employee.getName());
    }

    @Test
    void should_return_1_updated_employee_when_update_given_1_new_employee() throws Exception {
        Employee initEmployee = new Employee(22,"vicky", "female");
        initEmployee.setCompany(new Company(1, new ArrayList<>()));
        employeeRepository.save(initEmployee);
        String employeeNewJson = "{\n" +
                "    \"age\": 22,\n" +
                "    \"name\": \"sam\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"companyId\" :1\n" +
                "}";
        List<Employee> employees = employeeRepository.findAll();
        Employee selectedEmployee = employees.stream().filter(employee -> employee.getName().equals("vicky")).findFirst().orElse(null);
        mockMvc.perform(MockMvcRequestBuilders.put(String.format("/employees/%d", selectedEmployee.getId())).contentType(MediaType.APPLICATION_JSON).content(employeeNewJson));
        Employee newEmployee = employeeRepository.findAll().get(0);
        Assertions.assertEquals("sam", newEmployee.getName());
    }


}
