package com.example.graphql.service;

import com.example.graphql.model.Course;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    public List<Course> getAllCourses() {
        try {
            System.out.println("🔍 Fetching all courses...");
            
            // Données simulées pour tester
            Course course1 = new Course();
            course1.setId(1L);
            course1.setName("Mathematics");
            course1.setInstructor("Dr. Smith");
            course1.setCategory("Science");
            course1.setDescription("Advanced mathematics course");
            
            Course course2 = new Course();
            course2.setId(2L);
            course2.setName("Programming");
            course2.setInstructor("Prof. Johnson");
            course2.setCategory("IT");
            course2.setDescription("Java programming fundamentals");
            
            return List.of(course1, course2);
            
        } catch (Exception e) {
            System.out.println("❌ Error fetching courses: " + e.getMessage());
            return List.of();
        }
    }

    public Course getCourseById(Long id) {
        try {
            System.out.println("🔍 Fetching course with id: " + id);
            
            Course course = new Course();
            course.setId(id);
            course.setName("Course " + id);
            course.setInstructor("Instructor " + id);
            course.setCategory("Category " + id);
            course.setDescription("Description for course " + id);
            
            return course;
            
        } catch (Exception e) {
            System.out.println("❌ Error fetching course: " + e.getMessage());
            return null;
        }
    }

    // MÉTHODE CREATE
    public Course createCourse(Course input) {
        try {
            System.out.println("📝 Creating course: " + input.getName());
            
            // Simulation de création
            Course createdCourse = new Course();
            createdCourse.setId(System.currentTimeMillis()); // ID unique
            createdCourse.setName(input.getName());
            createdCourse.setInstructor(input.getInstructor());
            createdCourse.setCategory(input.getCategory());
            createdCourse.setDescription(input.getDescription());
            
            System.out.println("✅ Course created with ID: " + createdCourse.getId());
            return createdCourse;
            
        } catch (Exception e) {
            System.out.println("❌ Error creating course: " + e.getMessage());
            return null;
        }
    }
}