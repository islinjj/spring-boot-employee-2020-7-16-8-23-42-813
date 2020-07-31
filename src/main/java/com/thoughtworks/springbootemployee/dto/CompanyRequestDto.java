package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.entity.Company;

public class CompanyRequestDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company toEntity(){
        Company company = new Company();
        company.setName(name);
        return company;
    }
}
