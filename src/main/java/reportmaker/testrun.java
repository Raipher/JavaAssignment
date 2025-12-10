/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportmaker;

/**
 *
 * @author WIN11PC
 */


import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class testrun {
    public static void main(String[] args) {
        
        List<Student> db = new ArrayList<>();
        List<CourseDefinition> catalog = new ArrayList<>();

        
        DataLoader.loadStudents(db, "student_information.csv");
        DataLoader.loadCourses(catalog, "course_assessment_information.csv");

       
        SwingUtilities.invokeLater(() -> {
            new Login(db, catalog).setVisible(true);
        });
    }
}