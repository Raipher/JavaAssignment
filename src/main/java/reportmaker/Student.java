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

public class Student extends User {
    private String program;
    private int currentSemester;
    private List<Course> courses;

    public Student(String id, String name, String program, int sem, String email) {
        super(id, name, email);
        this.program = program;
        this.currentSemester = sem;
        this.courses = new ArrayList<>();
    }

    @Override
    public String getRole() { return "Student"; }

   
    public void addCourse(String code, String title, int credit, String grade, double point, int sem, String instructor) {
        this.courses.add(new Course(code, title, credit, grade, point, sem, instructor));
    }

    public List<Course> getCoursesBySemester(int sem) {
        List<Course> filtered = new ArrayList<>();
        for (Course c : courses) if (c.getSemester() == sem) filtered.add(c);
        return filtered;
    }

    public List<Course> getCourses() { return courses; }
    public String getProgram() { return program; }
    public void setProgram(String p) { this.program = p; }
    public int getCurrentSemester() { return currentSemester; }
    public void setCurrentSemester(int s) { this.currentSemester = s; }

    public double getCGPA() {
        if (courses.isEmpty()) return 0.0;
        double totalP = 0, totalC = 0;
        for (Course c : courses) { totalP += c.getPoint()*c.getCredit(); totalC += c.getCredit(); }
        return (totalC==0) ? 0.0 : totalP/totalC;
    }

    public int getFailedCourseCount() {
        int c = 0; for(Course course : courses) if(course.getPoint() < 2.0) c++; return c;
    }
}