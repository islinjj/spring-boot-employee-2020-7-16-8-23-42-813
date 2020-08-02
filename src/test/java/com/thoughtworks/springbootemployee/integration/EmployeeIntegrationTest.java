package com.thoughtworks.springbootemployee.integration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
        Employee initEmployee = new Employee(22, "vicky", "female");
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

    @Test
    void should_return_none_when_delete_employee_given_employee_id() throws Exception {
        Employee initEmployee = new Employee(22, "vicky", "female");
        initEmployee.setCompany(new Company(1, new ArrayList<>()));
        employeeRepository.save(initEmployee);
        List<Employee> employees = employeeRepository.findAll();
        Employee selectedEmployee = employees.stream().filter(employee -> employee.getName().equals("vicky")).findFirst().orElse(null);
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/employees/%d", selectedEmployee.getId())));
        employees = employeeRepository.findAll();
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void should_return_3_employee_when_find_by_page_given_page_1_and_size_3() throws Exception {
        for (int i = 0; i < 5; i++) {
            Employee initEmployee = new Employee(22, "vicky", "female");
            initEmployee.setCompany(new Company(1, new ArrayList<>()));
            employeeRepository.save(initEmployee);
        }

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/employees?page=0&size=3")).andReturn();
        JSONObject jsonObject = JSONObject.parseObject(result.getResponse().getContentAsString());
        List<Employee> employees = JSONArray.parseArray(jsonObject.get("content").toString(), Employee.class);
        Assertions.assertEquals(3, employees.size());
    }

    @Test
    void should_return_empty_when_find_by_page_given_page_2_and_size_4() throws Exception {
        for (int i = 0; i < 5; i++) {
            Employee initEmployee = new Employee(22, "vicky", "female");
            initEmployee.setCompany(new Company(1, new ArrayList<>()));
            employeeRepository.save(initEmployee);
        }

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/employees?page=1&size=5")).andReturn();
        JSONObject jsonObject = JSONObject.parseObject(result.getResponse().getContentAsString());
        List<Employee> employees = JSONArray.parseArray(jsonObject.get("content").toString(), Employee.class);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void should_return_male_employee_when_find_employee_by_gender_given_gender() throws Exception {
        String gender = "male";
        Employee employee = new Employee(22,"sam","male");
        employee.setCompany(new Company(1,new ArrayList<>()));
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employeeRepository.save(employee);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employees?"+gender)).andReturn();
        JSONObject jsonObject = JSONObject.parseObject(result.getResponse().getContentAsString());
        List<Employee> employeesByGender = JSONArray.parseArray(jsonObject.get("content").toString(),Employee.class);
        Assertions.assertEquals(employees.get(0).getGender(),employeesByGender.get(0).getGender());
    }

    @Test
    void should_return_500_when_add_employee_given_empty_name() throws Exception {
        String employeeJson = "{\n" +
                "    \"age\": 22,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"companyId\" :1\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJson)).andReturn();
        Assertions.assertEquals(500,mvcResult.getResponse().getStatus());
    }
}
