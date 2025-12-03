package com.example.graphql.model;

public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private University university;

    public Student() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public University getUniversity() { return university; }
    public void setUniversity(University university) { this.university = university; }
}