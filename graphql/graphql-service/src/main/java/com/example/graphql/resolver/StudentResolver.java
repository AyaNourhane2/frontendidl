package com.example.graphql.resolver;

import com.example.graphql.model.Student;
import com.example.graphql.model.Course;
import com.example.graphql.model.University;
import com.example.graphql.model.EnrollmentResult;
import com.example.graphql.model.StudentWithCourses;
import com.example.graphql.service.StudentService;
import com.example.graphql.service.CourseService;
import com.example.graphql.service.UniversityService;
import com.example.graphql.service.EnrollmentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

@Component
public class StudentResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private EnrollmentService enrollmentService;

    // ========== QUERIES ==========

    /**
     * Récupérer tous les étudiants
     */
    public List<Student> students() {
        System.out.println("👥 StudentResolver: Fetching all students");
        
        List<Student> students = studentService.getAllStudents();
        System.out.println("✅ Found " + students.size() + " students");
        
        return students;
    }

    /**
     * Récupérer un étudiant par son ID
     */
    public Student studentById(Long id) {
        System.out.println("🔍 StudentResolver: Fetching student by ID: " + id);
        
        Student student = studentService.getStudentById(id);
        if (student != null) {
            System.out.println("✅ Found student: " + student.getFirstName() + " " + student.getLastName());
        } else {
            System.out.println("❌ Student not found with ID: " + id);
        }
        
        return student;
    }

    /**
     * Récupérer tous les étudiants avec leurs cours et universités
     */
    public List<StudentWithCourses> studentsWithCourses() {
        System.out.println("🎓 StudentResolver: Fetching students with courses and universities");
        
        List<Student> students = studentService.getAllStudents();
        List<StudentWithCourses> result = new ArrayList<>();
        
        for (Student student : students) {
            if (student != null) {
                StudentWithCourses swc = new StudentWithCourses();
                swc.setStudent(student);
                
                // Récupérer les cours de l'étudiant
                List<Course> enrolledCourses = enrollmentService.getStudentCourses(student.getId());
                swc.setCourses(enrolledCourses);
                
                // Récupérer l'université de l'étudiant
                swc.setUniversity(student.getUniversity());
                
                result.add(swc);
                
                System.out.println("✅ Student: " + student.getFirstName() + " " + student.getLastName() + 
                                 " | Courses: " + enrolledCourses.size() + 
                                 " | University: " + (student.getUniversity() != null ? student.getUniversity().getName() : "None"));
            }
        }
        
        System.out.println("🎯 Total students with courses: " + result.size());
        return result;
    }

    // ========== MUTATIONS ==========

    /**
     * Créer un nouvel étudiant
     */
    public Student createStudent(StudentInput input) {
        System.out.println("🆕 StudentResolver: Creating student - " + input.getFirstName() + " " + input.getLastName());
        
        Student student = new Student();
        student.setFirstName(input.getFirstName());
        student.setLastName(input.getLastName());
        student.setEmail(input.getEmail());
        
        // Assigner l'université si l'ID est fourni
        if (input.getUniversityId() != null) {
            University university = universityService.getUniversityById(input.getUniversityId());
            if (university != null) {
                student.setUniversity(university);
                System.out.println("🎓 University assigned: " + university.getName());
            } else {
                System.out.println("⚠️ University not found with ID: " + input.getUniversityId());
            }
        }
        
        Student createdStudent = studentService.createStudent(student);
        
        if (createdStudent != null) {
            System.out.println("✅ Student created successfully: " + createdStudent.getFirstName() + " " + 
                             createdStudent.getLastName() + " (ID: " + createdStudent.getId() + ")");
        } else {
            System.out.println("❌ Failed to create student");
        }
        
        return createdStudent;
    }

    /**
     * Créer un étudiant avec université (méthode alternative)
     */
    public Student createStudentWithUniversity(StudentWithUniversityInput input) {
        System.out.println("🆕 StudentResolver: Creating student with university - " + 
                         input.getFirstName() + " " + input.getLastName());
        
        Student student = new Student();
        student.setFirstName(input.getFirstName());
        student.setLastName(input.getLastName());
        student.setEmail(input.getEmail());
        
        // Assigner l'université
        if (input.getUniversityId() != null) {
            University university = universityService.getUniversityById(input.getUniversityId());
            if (university != null) {
                student.setUniversity(university);
                System.out.println("🎓 University assigned: " + university.getName());
            } else {
                System.out.println("⚠️ University not found with ID: " + input.getUniversityId());
            }
        }
        
        Student createdStudent = studentService.createStudent(student);
        
        if (createdStudent != null) {
            System.out.println("✅ Student with university created successfully");
        } else {
            System.out.println("❌ Failed to create student with university");
        }
        
        return createdStudent;
    }

    /**
     * Associer un étudiant à un cours
     */
    public EnrollmentResult enrollStudent(Long studentId, Long courseId) {
        System.out.println("🔗 StudentResolver: Enrolling student " + studentId + " in course " + courseId);
        
        // Vérifier si l'étudiant existe
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("❌ Student not found with ID: " + studentId);
            return createErrorResult("Student not found with ID: " + studentId);
        }
        
        // Vérifier si le cours existe
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            System.out.println("❌ Course not found with ID: " + courseId);
            return createErrorResult("Course not found with ID: " + courseId);
        }
        
        // Effectuer l'association
        Boolean success = enrollmentService.enrollStudent(studentId, courseId);
        
        EnrollmentResult result = new EnrollmentResult();
        result.setSuccess(success);
        
        if (success) {
            result.setMessage("✅ Student " + student.getFirstName() + " successfully enrolled in course: " + course.getName());
            System.out.println("✅ Enrollment successful");
        } else {
            result.setMessage("❌ Failed to enroll student in course");
            System.out.println("❌ Enrollment failed");
        }
        
        result.setStudent(student);
        result.setCourse(course);
        
        return result;
    }

    /**
     * Assigner une université à un étudiant existant
     */
    public Student assignUniversityToStudent(Long studentId, Long universityId) {
        System.out.println("🎓 StudentResolver: Assigning university " + universityId + " to student " + studentId);
        
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("❌ Student not found with ID: " + studentId);
            return null;
        }
        
        University university = universityService.getUniversityById(universityId);
        if (university == null) {
            System.out.println("❌ University not found with ID: " + universityId);
            return null;
        }
        
        student.setUniversity(university);
        System.out.println("✅ University '" + university.getName() + "' assigned to student '" + 
                         student.getFirstName() + " " + student.getLastName() + "'");
        
        return student;
    }

    // ========== MÉTHODES PRIVÉES ==========

    /**
     * Créer un résultat d'erreur
     */
    private EnrollmentResult createErrorResult(String message) {
        EnrollmentResult result = new EnrollmentResult();
        result.setSuccess(false);
        result.setMessage(message);
        result.setStudent(null);
        result.setCourse(null);
        return result;
    }

    // ========== CLASSES INTERNES POUR LES INPUTS ==========

    /**
     * Input pour la création d'étudiant standard
     */
    public static class StudentInput {
        private String firstName;
        private String lastName;
        private String email;
        private Long universityId;
        
        public StudentInput() {}
        
        // ========== GETTERS ==========
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getEmail() { return email; }
        public Long getUniversityId() { return universityId; }
        
        // ========== SETTERS ==========
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setEmail(String email) { this.email = email; }
        public void setUniversityId(Long universityId) { this.universityId = universityId; }

        @Override
        public String toString() {
            return "StudentInput{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", universityId=" + universityId +
                    '}';
        }
    }

    /**
     * Input pour la création d'étudiant avec université
     */
    public static class StudentWithUniversityInput {
        private String firstName;
        private String lastName;
        private String email;
        private Long universityId;
        
        public StudentWithUniversityInput() {}
        
        // ========== GETTERS ==========
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getEmail() { return email; }
        public Long getUniversityId() { return universityId; }
        
        // ========== SETTERS ==========
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setEmail(String email) { this.email = email; }
        public void setUniversityId(Long universityId) { this.universityId = universityId; }

        @Override
        public String toString() {
            return "StudentWithUniversityInput{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", universityId=" + universityId +
                    '}';
        }
    }
}