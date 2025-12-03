package com.example.graphql.service;

import com.example.graphql.model.Student;
import com.example.graphql.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class StudentService {

    @Value("${services.student.base-url}")
    private String studentBaseUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Student> getAllStudents() {
        try {
            System.out.println("🔍 Fetching students from: " + studentBaseUrl + "/students");
            
            // Données simulées pour tester
            Student student1 = new Student();
            student1.setId(1L);
            student1.setFirstName("Aya");
            student1.setLastName("Norhane");
            student1.setEmail("aya@email.com");
            
            University university1 = new University();
            university1.setId(1L);
            university1.setName("University of Test");
            university1.setLocation("Test City");
            student1.setUniversity(university1);
            
            Student student2 = new Student();
            student2.setId(2L);
            student2.setFirstName("John");
            student2.setLastName("Doe");
            student2.setEmail("john@email.com");
            
            return List.of(student1, student2);
            
        } catch (Exception e) {
            System.out.println("❌ Error fetching students: " + e.getMessage());
            return List.of();
        }
    }

    public Student getStudentById(Long id) {
        try {
            System.out.println("🔍 Fetching student with id: " + id);
            
            Student student = new Student();
            student.setId(id);
            student.setFirstName("Student");
            student.setLastName("Number " + id);
            student.setEmail("student" + id + "@email.com");
            
            University university = new University();
            university.setId(id);
            university.setName("University " + id);
            university.setLocation("Location " + id);
            student.setUniversity(university);
            
            return student;
            
        } catch (Exception e) {
            System.out.println("❌ Error fetching student: " + e.getMessage());
            return null;
        }
    }

    // MÉTHODE CREATE - NOUVELLE
    public Student createStudent(Student input) {
        try {
            System.out.println("📝 Creating student: " + input.getFirstName() + " " + input.getLastName());
            
            // Simulation de création
            Student createdStudent = new Student();
            createdStudent.setId(System.currentTimeMillis()); // ID unique
            createdStudent.setFirstName(input.getFirstName());
            createdStudent.setLastName(input.getLastName());
            createdStudent.setEmail(input.getEmail());
            createdStudent.setUniversity(input.getUniversity());
            
            System.out.println("✅ Student created with ID: " + createdStudent.getId());
            return createdStudent;
            
        } catch (Exception e) {
            System.out.println("❌ Error creating student: " + e.getMessage());
            return null;
        }
    }
}