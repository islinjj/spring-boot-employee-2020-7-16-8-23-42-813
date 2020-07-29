package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRespository;
import com.thoughtworks.springbootemployee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRespository employeeRespository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void should_return_2_employee_when_find_employee_by_page_given_page_1_and_pagesize_2_and_3_employee() {
        //given
        int page = 1;
        int pageSize = 2;
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            employees.add(new Employee(22, "Dong", i, "male"));
        }

        when(employeeRespository.findAll()).thenReturn(employees);
        //when
        List<Employee> result = employeeService.findEmployeeByPage(page, pageSize);
        //then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void should_return_empty_list_when_find_employee_bu_page_given_3_employee_and_page_2_page_size_5() {
        int page = 2;
        int pageSize = 5;
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            employees.add(new Employee(22, "Dong", i, "male"));
        }
        when(employeeRespository.findAll()).thenReturn(employees);
        //when
        List<Employee> result = employeeService.findEmployeeByPage(page, pageSize);
        //then
        Assertions.assertEquals(0, result.size());
    }


    @Test
    void should_return_2_male_employee_when_find_employee_by_gender_given_2_male_employee_and_1_female_employee_and_gender_is_male() {
        String gender = "male";
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            employees.add(new Employee(22, "Dong", i, "male"));
        }
        employees.add(new Employee(23, "Vickey", 4, "female"));
        when(employeeRespository.findAll()).thenReturn(employees);
        //when
        List<Employee> result = employeeService.findEmployeeByGender(gender);

        //then
        Assertions.assertEquals(2, result.size());
    }
}