/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportmaker;

/**
 *
 * @author WIN11PC
 */



public abstract class User {
    protected String id, name, email;
    public User(String id, String name, String email) { this.id = id; this.name = name; this.email = email; }
    public abstract String getRole();
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}