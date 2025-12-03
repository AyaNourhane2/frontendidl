package com.example.graphql.model;

import java.util.List;

public class StudentWithCourses {
    private Student student;
    private List<Course> courses;
    private University university;

    public StudentWithCourses() {}

    // GETTERS
    public Student getStudent() { return student; }
    public List<Course> getCourses() { return courses; }
    public University getUniversity() { return university; }

    // SETTERS
    public void setStudent(Student student) { this.student = student; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
    public void setUniversity(University university) { this.university = university; }
}