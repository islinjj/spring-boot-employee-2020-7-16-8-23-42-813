package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Test
    void should_return_new_employee_when_update_given_employee_id_and_employee_request_dto() {
        //given
        Integer employeeId = 1;
        Employee employee = new Employee(22, "vicky", "female");
        employee.setId(employeeId);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        employee.setAge(18);//remove
        when(employeeRepository.save(employee)).thenReturn(employee);//remove,should not mock save? but will error with employee null
        EmployeeRequestDto newEmployeeRequestDto = new EmployeeRequestDto(18,"vicky","female",1);

        //when
        EmployeeResponseDto employeeResponseDto = employeeService.updateEmployee(employeeId,newEmployeeRequestDto);

        //then
        Assertions.assertEquals(18,employeeResponseDto.getAge());
    }

    @Test
    void should_return_1_employee_when_find_employee_by_id_given_employee_id() {
        //given
        Integer employeeId = 1;
        Employee employee = new Employee(22,"vicky","female");
        employee.setId(employeeId);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        //when
        EmployeeResponseDto employeeResponseDto = employeeService.findEmployeeById(employeeId);

        //then
        Assertions.assertEquals(employee.getName(),employeeResponseDto.getName());
    }

    @Test
    void should_return_employees_when_find_all_employees_given_1_employee() {
        //given
        Employee employee = new Employee(22,"sam","male");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Page<Employee> employeePage = new PageImpl<>(employees);
        when(employeeRepository.findAll(Pageable.unpaged())).thenReturn(employeePage);

        //when
        Page<Employee> allEmployees = employeeService.findAllEmployees(Pageable.unpaged());

        //then
        Assertions.assertEquals(employeePage.getTotalElements(),allEmployees.getTotalElements());
    }

    @Test
    void should_return_1_employee_when_find_employee_by_page_given_page_0_size_1() {
        //given
        Employee employee = new Employee(22,"sam","male");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Page<Employee> employeePage = new PageImpl<Employee>(employees);
        when(employeeRepository.findAll(PageRequest.of(0,1))).thenReturn(employeePage);

        //when
        Page<Employee> employeeByPage = employeeService.findAllEmployees(PageRequest.of(0,1));

        //then
        Assertions.assertEquals(1,employeeByPage.getTotalElements());
    }

    @Test
    void should_return_male_employee_when_find_employee_given_gender_male() throws Exception {
        //given
        String gender = "male";
        Employee employee = new Employee(22,"sam","male");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepository.findByGender(gender)).thenReturn(employees);

        //when
        List<Employee> employeesByGender = employeeService.findEmployeeByGender(gender);

        //then
        Assertions.assertEquals(employees,employeesByGender);
    }
}