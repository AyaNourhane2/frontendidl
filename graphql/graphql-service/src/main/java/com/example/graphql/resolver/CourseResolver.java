package com.example.graphql.resolver;

import com.example.graphql.model.Course;
import com.example.graphql.service.CourseService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CourseResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CourseService courseService;

    // QUERIES
    public List<Course> courses() {
        System.out.println("📚 Fetching all courses");
        return courseService.getAllCourses();
    }

    public Course courseById(Long id) {
        System.out.println("🔍 Fetching course by ID: " + id);
        return courseService.getCourseById(id);
    }

    // MUTATIONS
    public Course createCourse(CourseInput input) {
        System.out.println("🆕 Creating course: " + input.getName());
        
        Course course = new Course();
        course.setName(input.getName());
        course.setInstructor(input.getInstructor());
        course.setCategory(input.getCategory());
        course.setDescription(input.getDescription());
        
        return courseService.createCourse(course);
    }

    // INPUT CLASS
    public static class CourseInput {
        private String name;
        private String instructor;
        private String category;
        private String description;
        
        public CourseInput() {}
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getInstructor() { return instructor; }
        public void setInstructor(String instructor) { this.instructor = instructor; }
        
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}