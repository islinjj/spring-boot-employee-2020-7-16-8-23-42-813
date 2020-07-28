package com.thoughtworks.springbootemployee.entity;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 4:49 PM
 * @Version 1.0
 */
public class Employee {
    private int age;
    private String name;
    private String id;
    private String gender;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Employee() {
    }

    public Employee(int age, String name, String id, String gender) {
        this.age = age;
        this.name = name;
        this.id = id;
        this.gender = gender;
    }
}
