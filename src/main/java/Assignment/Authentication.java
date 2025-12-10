package Assignment;

import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private String lastLoginBinary;
    private String lastLogoutBinary;
    private long lastLoginMillis;
    private long lastLogoutMillis;
    
    private final Map<String, Integer> failedLoginCounts = new HashMap<>();
    private final Map<String, User> usersByUsername;

    public Authentication(Map<String, User> usersByUsername) {
        this.usersByUsername = usersByUsername;
        this.lastLoginBinary = null;
        this.lastLogoutBinary = null;
        this.lastLoginMillis = 0L;
        this.lastLogoutMillis = 0L;
    }
    
    public boolean login(String username, String password) {
        User user = usersByUsername.get(username);
        
        if (user == null || !user.isActive()) {
            return false;
        }

        if (!user.getPassword().equals(password)) {
            int count = failedLoginCounts.getOrDefault(username, 0) + 1;
            failedLoginCounts.put(username, count);

            if (count >= 3) {
                user.deactivate();
            }

            return false; 
        }

        recordLoginTime();
        failedLoginCounts.put(username, 0);
        return true;
    }   

    public void logout(User user) {
        if (user != null) {
            recordLogoutTime();
        }
    }

    public void recordLoginTime() {
        long now = System.currentTimeMillis();
        this.lastLoginMillis = now;
        this.lastLoginBinary = Long.toBinaryString(now);
    }

    public void recordLogoutTime() {
        long now = System.currentTimeMillis();
        this.lastLogoutMillis = now;
        this.lastLogoutBinary = Long.toBinaryString(now);
    }
    
    public User getUser(String username) {
        return usersByUsername.get(username);
    }

    public String getLastLoginBinary() {
        return lastLoginBinary;
    }

    public String getLastLogoutBinary() {
        return lastLogoutBinary;
    }
    
    public long getLastLoginMillis() {
        return lastLoginMillis;
    }

    public long getLastLogoutMillis() {
        return lastLogoutMillis;
    }

    public int getFailedLoginCount(String username) {
        return failedLoginCounts.getOrDefault(username, 0);
    }
}