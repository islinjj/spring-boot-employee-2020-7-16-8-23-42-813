package com.thoughtworks.springbootemployee.utils;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RelationMapperUtilsTest {

    @Test
    void should_return_employee_request_dto_when_translate_given_1_employee_entity() {
        Employee employee = new Employee(22, "vicky", "female");
        employee.setId(1);
        employee.setCompany(new Company());

        EmployeeRequestDto requestDto = new EmployeeRequestDto();
        RelationMapperUtils.enAndDtoMapper(requestDto, employee);

        Assertions.assertEquals(requestDto.getName(), employee.getName());

    }

    @Test
    void should_return_employee_response_dto_when_translate_given_1_employee_entity() {
        Employee employee = new Employee(22, "vicky", "female");
        employee.setId(1);
        employee.setCompany(new Company());

        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        RelationMapperUtils.enAndDtoMapper(responseDto, employee);

        Assertions.assertEquals(responseDto.getName(), employee.getName());
    }
}