package com.example.graphql.model;

public class EnrollmentResult {
    private Boolean success;
    private String message;
    private Student student;
    private Course course;

    public EnrollmentResult() {}

    // GETTERS
    public Boolean getSuccess() { return success; }
    public String getMessage() { return message; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }

    // SETTERS
    public void setSuccess(Boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setStudent(Student student) { this.student = student; }
    public void setCourse(Course course) { this.course = course; }
}