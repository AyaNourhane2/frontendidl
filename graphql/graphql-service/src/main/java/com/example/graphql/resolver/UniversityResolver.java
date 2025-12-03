package com.example.graphql.resolver;

import com.example.graphql.model.University;
import com.example.graphql.service.UniversityService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UniversityResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private UniversityService universityService;

    // QUERIES
    public List<University> universities() {
        System.out.println("🏛️ Fetching all universities");
        return universityService.getAllUniversities();
    }

    public University universityById(Long id) {
        System.out.println("🔍 Fetching university by ID: " + id);
        return universityService.getUniversityById(id);
    }

    // MUTATIONS
    public University createUniversity(UniversityInput input) {
        System.out.println("🆕 Creating university: " + input.getName() + " in " + input.getLocation());
        
        University university = new University();
        university.setName(input.getName());
        university.setLocation(input.getLocation());
        
        return universityService.createUniversity(university);
    }

    // ❌ SUPPRIMER CETTE MÉTHODE - ELLE EXISTE DÉJÀ DANS StudentResolver
    // public Student assignUniversityToStudent(Long studentId, Long universityId) {
    //     // Cette méthode doit être uniquement dans StudentResolver
    // }

    // INPUT CLASS
    public static class UniversityInput {
        private String name;
        private String location;
        
        public UniversityInput() {}
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
    }
}