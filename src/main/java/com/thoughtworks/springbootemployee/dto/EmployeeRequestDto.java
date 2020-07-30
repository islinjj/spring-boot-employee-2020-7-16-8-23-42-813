package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.entity.Employee;

/**
 * @Author ZHUDO2
 * @Date 7/29/2020 5:27 PM
 * @Version 1.0
 */
public class EmployeeRequestDto {
    private int age;
    private String name;
    private String gender;
    private int companyId;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCompanyId() {
        return companyId;
    }

    public EmployeeRequestDto() {
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public EmployeeRequestDto(int age, String name, String gender, int companyId) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.companyId = companyId;
    }

    public Employee toEntity() {
        return new Employee(age,name,gender);
    }
}
