package com.example.graphql.service;

import com.example.graphql.model.Student;
import com.example.graphql.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EnrollmentService {
    private final Map<Long, Set<Long>> studentCourses = new ConcurrentHashMap<>();
    private final Map<Long, Set<Long>> courseStudents = new ConcurrentHashMap<>();

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    public Boolean enrollStudent(Long studentId, Long courseId) {
        try {
            System.out.println("🔗 Enrolling student " + studentId + " in course " + courseId);
            
            studentCourses.computeIfAbsent(studentId, k -> new HashSet<>()).add(courseId);
            courseStudents.computeIfAbsent(courseId, k -> new HashSet<>()).add(studentId);
            
            System.out.println("✅ Enrollment successful");
            return true;
            
        } catch (Exception e) {
            System.out.println("❌ Error enrolling student: " + e.getMessage());
            return false;
        }
    }

    public List<Course> getStudentCourses(Long studentId) {
        try {
            Set<Long> courseIds = studentCourses.get(studentId);
            if (courseIds == null) {
                return new ArrayList<>();
            }
            
            List<Course> courses = new ArrayList<>();
            for (Long courseId : courseIds) {
                Course course = courseService.getCourseById(courseId);
                if (course != null) {
                    courses.add(course);
                }
            }
            
            System.out.println("📚 Found " + courses.size() + " courses for student " + studentId);
            return courses;
            
        } catch (Exception e) {
            System.out.println("❌ Error getting student courses: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Student> getCourseStudents(Long courseId) {
        try {
            Set<Long> studentIds = courseStudents.get(courseId);
            if (studentIds == null) {
                return new ArrayList<>();
            }
            
            List<Student> students = new ArrayList<>();
            for (Long studentId : studentIds) {
                Student student = studentService.getStudentById(studentId);
                if (student != null) {
                    students.add(student);
                }
            }
            
            System.out.println("👥 Found " + students.size() + " students for course " + courseId);
            return students;
            
        } catch (Exception e) {
            System.out.println("❌ Error getting course students: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}