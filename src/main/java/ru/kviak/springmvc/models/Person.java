package ru.kviak.springmvc.models;

import jakarta.validation.constraints.*;


public class Person {
    private int id;


    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Incorrect email")
    private String email;
    @Size(min=2, max=50, message = "Name length should be between 2 and 50 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public Person() {}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
