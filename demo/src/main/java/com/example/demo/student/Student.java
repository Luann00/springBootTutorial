package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Student {
    
    @Id
    private long id;
    private String name;
    private String email; 
    private LocalDate dob;
    
    @Transient
    private Integer age;
    
    public Student(long id, String name, String email, LocalDate dob) { 
        this.id = id;
        this.name = name;
        this.email = email;  
        this.dob = dob;
    }
    
    public Student(String name, String email, LocalDate dob) { 
        this.name = name;
        this.email = email;  
        this.dob = dob;
    }

    public Student() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {  
        return email;
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age + "]";
    }
}
