package com.thoughtworks.springbootemployee.entity;

import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 7:40 PM
 * @Version 1.0
 */
public class Company {
    private int id;
    private List<Employee> employees;

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
