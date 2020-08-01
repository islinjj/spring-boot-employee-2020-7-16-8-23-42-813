package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.impl.CompanyServiceImpl;
import com.thoughtworks.springbootemployee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    CompanyRepository companyRepository;

    @Test
    void should_return_employee_response_dto_when_add_1_employee_given_1_employee_request_dto() {
        //given
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(22, "Vicky", "female", 1);

        Company company = new Company(1, new ArrayList<>(), "OOCL");
        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        //when
        EmployeeResponseDto result = employeeService.addEmployee(employeeRequestDto);

        //then
        Assertions.assertEquals(employeeRequestDto.getName(), result.getName());
    }

    @Test
    void should_return_empty_employees_when_delete_given_1_employee_id() {
        //given
        int employeeId = 1;

        //when
        employeeService.deleteEmployeeById(employeeId);

        //then
        verify(employeeRepository).deleteById(employeeId);
    }
}