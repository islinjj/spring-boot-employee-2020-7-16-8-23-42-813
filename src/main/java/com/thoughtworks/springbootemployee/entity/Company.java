package com.thoughtworks.springbootemployee.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 7:40 PM
 * @Version 1.0
 */
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL})
    private List<Employee> employees;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company(int id, List<Employee> employees) {
        this.id = id;
        this.employees = employees;
    }

    public Company(int id, List<Employee> employees, String name) {
        this.id = id;
        this.employees = employees;
        this.name = name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Company(String name) {
        this.name = name;
    }
}
