package com.thoughtworks.springbootemployee.utils;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RelationMapperUtilsTest {

    @Test
    void should_return_true_when_translate_to_employee_request_dto_given_1_employee_entity() {
        Employee employee = new Employee(22, "vicky", "female");
        employee.setId(1);
        employee.setCompany(new Company());

        EmployeeRequestDto requestDto = new EmployeeRequestDto();
        RelationMapperUtils.enAndDtoMapper(requestDto, employee);

        Assertions.assertEquals(requestDto.getName(), employee.getName());

    }
}