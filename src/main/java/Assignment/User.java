package Assignment;

public class User {
    private int userId;
    private String username;
    private String fullName;
    private String password;
    private String email;
    private String role;
    private boolean isActive;

    public User(int userId, String username, String fullName,
                String password, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isActive = true;
    }
    
    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }
}