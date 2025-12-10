/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportmaker;

/**
 *
 * @author WIN11PC
 */


public class Course {
    private String code, title, grade, instructor; // [New Field]
    private int credit, semester;
    private double point;

    
    public Course(String code, String title, int credit, String grade, double point, int semester, String instructor) {
        setCode(code); 
        setTitle(title); 
        setCredit(credit); 
        setGrade(grade); 
        setPoint(point); 
        setSemester(semester);
        this.instructor = instructor;
    }

   
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

   
    public void setCredit(int c) { if(c<1) throw new IllegalArgumentException("Invalid Credit"); this.credit=c; }
    public void setPoint(double p) { if(p<0||p>4.0) throw new IllegalArgumentException("Invalid Point"); this.point=p; }
    public void setCode(String c) { this.code=c; }
    public void setTitle(String t) { this.title=t; }
    public void setGrade(String g) { this.grade=g; }
    public void setSemester(int s) { this.semester=s; }

    
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredit() { return credit; }
    public String getGrade() { return grade; }
    public double getPoint() { return point; }
    public int getSemester() { return semester; }
}