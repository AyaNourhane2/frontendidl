package com.example.graphql.service;

import com.example.graphql.model.University;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UniversityService {

    public List<University> getAllUniversities() {
        try {
            System.out.println("🏛️ Fetching all universities");
            
            // Données simulées
            University university1 = new University();
            university1.setId(1L);
            university1.setName("University of Science and Technology");
            university1.setLocation("Casablanca");
            
            University university2 = new University();
            university2.setId(2L);
            university2.setName("Engineering School");
            university2.setLocation("Rabat");
            
            University university3 = new University();
            university3.setId(3L);
            university3.setName("Business School");
            university3.setLocation("Marrakech");
            
            return List.of(university1, university2, university3);
            
        } catch (Exception e) {
            System.out.println("❌ Error fetching universities: " + e.getMessage());
            return List.of();
        }
    }

    public University getUniversityById(Long id) {
        try {
            System.out.println("🔍 Fetching university with id: " + id);
            
            University university = new University();
            university.setId(id);
            university.setName("University " + id);
            university.setLocation("City " + id);
            
            return university;
            
        } catch (Exception e) {
            System.out.println("❌ Error fetching university: " + e.getMessage());
            return null;
        }
    }

    public University createUniversity(University input) {
        try {
            System.out.println("📝 Creating university: " + input.getName());
            
            University createdUniversity = new University();
            createdUniversity.setId(System.currentTimeMillis());
            createdUniversity.setName(input.getName());
            createdUniversity.setLocation(input.getLocation());
            
            System.out.println("✅ University created with ID: " + createdUniversity.getId());
            return createdUniversity;
            
        } catch (Exception e) {
            System.out.println("❌ Error creating university: " + e.getMessage());
            return null;
        }
    }
}