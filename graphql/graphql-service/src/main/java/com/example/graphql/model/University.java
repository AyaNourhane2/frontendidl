package com.example.graphql.model;

import java.util.List;

public class University {
    private Long id;
    private String name;
    private String location;
    private List<Student> students;  // 🔥 NOUVEAU

    public University() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}