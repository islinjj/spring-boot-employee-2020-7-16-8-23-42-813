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

    @OneToMany(mappedBy = "company")
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
