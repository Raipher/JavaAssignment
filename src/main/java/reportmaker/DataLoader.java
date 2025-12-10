/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportmaker;

/**
 *
 * @author WIN11PC
 */



import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    
    public static void loadStudents(List<Student> db, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                String[] parts = line.split(",");
                if (parts.length < 6) continue;
                String id = parts[0].trim();
                String name = parts[1].trim() + " " + parts[2].trim();
                String major = parts[3].trim();
                String year = parts[4].trim();
                String email = parts[5].trim();
                int sem = mapYearToSemester(year);
                db.add(new Student(id, name, major, sem, email));
            }
            System.out.println("[System] Loaded " + db.size() + " students.");
        } catch (Exception e) { System.out.println("Error loading students: " + e.getMessage()); }
    }

    public static void loadCourses(List<CourseDefinition> catalog, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                String[] parts = line.split(",");
                if (parts.length < 5) continue; // Ensure we have instructor

                String id = parts[0].trim();
                String name = parts[1].trim();
                int credits = Integer.parseInt(parts[2].trim());
                String sem = parts[3].trim();
                String instructor = parts[4].trim(); // [Read Instructor]

                catalog.add(new CourseDefinition(id, name, credits, sem, instructor));
            }
            System.out.println("[System] Loaded " + catalog.size() + " courses.");
        } catch (Exception e) { System.out.println("Error loading courses: " + e.getMessage()); }
    }

    private static int mapYearToSemester(String year) {
        switch (year.toLowerCase()) {
            case "freshman": return 1; case "sophomore": return 3; case "junior": return 5; case "senior": return 7; default: return 1;
        }
    }
}