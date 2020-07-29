package com.thoughtworks.springbootemployee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 4:49 PM
 * @Version 1.0
 */
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String name;
    private String gender;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonIgnore
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Employee() {
    }

    public Employee(int age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }
}
