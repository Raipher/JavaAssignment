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

public class DepartmentAnalyticsGenerator implements IReportService {
    @Override
    public void printHeader(String title) {
        System.out.println("\n=============================================================");
        System.out.println("      " + title.toUpperCase());
        System.out.println("=============================================================");
    }

    public void generateAnalytics(List<Student> db, String keyword) {
        printHeader("Department Analytics: " + keyword);
        
        int count = 0, eligible = 0;
        double totalCGPA = 0;
        List<Course> deptCourses = new ArrayList<>();

       
        for (Student s : db) {
            if (s.getProgram().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                totalCGPA += s.getCGPA();
                if (s.getCGPA() >= 2.0 && s.getFailedCourseCount() <= 3) eligible++;
                deptCourses.addAll(s.getCourses()); // Collect courses for analysis
            }
        }

        if (count > 0) {
            System.out.printf(" Total Students    : %d\n", count);
            System.out.printf(" Average CGPA      : %.2f\n", (totalCGPA / count));
            System.out.printf(" Eligibility Rate  : %.1f%%\n", ((double)eligible / count) * 100);
            
           
            System.out.println("\n-------------------------------------------------------------");
            System.out.println(" COURSE & INSTRUCTOR PERFORMANCE");
            System.out.println("-------------------------------------------------------------");
            System.out.printf("%-25s %-15s %-10s\n", "Course", "Instructor", "Avg Grade");
            System.out.println("-------------------------------------------------------------");
            
            
            List<String> printed = new ArrayList<>();
            for(Course c : deptCourses) {
                String key = c.getCode();
                if(!printed.contains(key)) {
                    System.out.printf("%-25s %-15s %-10s\n", 
                        truncate(c.getTitle(), 25), 
                        truncate(c.getInstructor(), 15), // [SHOW INSTRUCTOR]
                        "N/A (Ref)"); 
                    printed.add(key);
                }
            }
        } else {
            System.out.println("No data found.");
        }
        System.out.println("=============================================================\n");
    }

    private String truncate(String s, int len) {
        if(s==null) return "Unknown";
        if(s.length() > len) return s.substring(0, len-3) + "..";
        return s;
    }
}