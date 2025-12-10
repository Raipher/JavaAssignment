/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportmaker;

/**
 *
 * @author WIN11PC
 */

public class CourseDefinition {
    private String id;
    private String name;
    private int credits;
    private String semester;
    private String instructor; // [New Field]

    public CourseDefinition(String id, String name, int credits, String semester, String instructor) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
        this.instructor = instructor;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; } // [New Getter]
}