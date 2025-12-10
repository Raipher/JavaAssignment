package Assignment;

import java.util.Map;

public class UserManagementService {

    private final UserManager userManager;
    private final AccessControl accessControl;
    private final Authentication authentication;

    public UserManagementService(UserManager userManager, AccessControl accessControl, Authentication authentication) {
    this.userManager = userManager;
    this.accessControl = accessControl;
    this.authentication = authentication;
}

public static class OperationResult {
    public final boolean success;
    public final String message;
    
    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

public OperationResult addUser(User currentUser, String username, 
                                String fullName, String email, 
                                String role, String password) {

    if (!accessControl.hasPermission(currentUser, "MANAGE_USERS")) {
        return new OperationResult(false, "You do not have permission to add users.");
    }
    
    if (username == null || username.trim().isEmpty()) {
        return new OperationResult(false, "Username cannot be empty.");
    }
    if (fullName == null || fullName.trim().isEmpty()) {
        return new OperationResult(false, "Full name cannot be empty.");
    }
    if (email == null || email.trim().isEmpty()) {
        return new OperationResult(false, "Email cannot be empty.");
    }
    if (role == null || role.trim().isEmpty()) {
        return new OperationResult(false, "Role cannot be empty.");
    }
    if (password == null || password.isEmpty()) {
        return new OperationResult(false, "Password cannot be empty.");
    }
    
    if (userManager.usernameExists(username)) {
        return new OperationResult(false, "Username already exists.");
    }
    
    User newUser = new User(0, username.trim(), fullName.trim(), 
                            password, email.trim(), role.trim());
    
    boolean success = userManager.addUser(newUser);
    
    if (success) {
        EmailNotification emailNotif = new EmailNotification();
        emailNotif.prepareAccountCreated(newUser);
        emailNotif.send();
        
        return new OperationResult(true, "User '" + username + "' created successfully.");
    } else {
        return new OperationResult(false, "Failed to create user.");
    }
}

public OperationResult updateUser(User currentUser, String username, 
                                   String fullName, String email, 
                                   String role, String password) {
    if (username == null || username.trim().isEmpty()) {
        return new OperationResult(false, "Username cannot be empty.");
    }
 
    User user = userManager.getUser(username);
    if (user == null) {
        return new OperationResult(false, "User not found.");
    }
    
    boolean isSelf = currentUser.getUsername().equals(username);
    boolean canManage = accessControl.hasPermission(currentUser, "MANAGE_USERS");
    
    if (!isSelf && !canManage) {
        return new OperationResult(false, "You can only update your own account.");
    }
    
    if (!canManage && role != null && !role.trim().isEmpty() 
            && !role.equals(user.getRole())) {
        return new OperationResult(false, "You cannot change your own role.");
    }
    
    if (fullName != null && !fullName.trim().isEmpty()) {
        user.setFullName(fullName.trim());
    }
    if (email != null && !email.trim().isEmpty()) {
        user.setEmail(email.trim());
    }
    if (canManage && role != null && !role.trim().isEmpty()) {
        user.setRole(role.trim());
    }
    if (password != null && !password.isEmpty()) {
        user.setPassword(password);
    }
    
    boolean success = userManager.updateUser(user);
    
    if (success) {
        EmailNotification emailNotif = new EmailNotification();
        emailNotif.prepareAccountUpdated(user);
        emailNotif.send();
        
        return new OperationResult(true, "User '" + username + "' updated successfully.");
    } else {
        return new OperationResult(false, "Failed to update user.");
    }
}

public OperationResult deactivateUser(User currentUser, String username) {
    if (!accessControl.hasPermission(currentUser, "MANAGE_USERS")) {
        return new OperationResult(false, "You do not have permission to deactivate users.");
    }
    
    if (username == null || username.trim().isEmpty()) {
        return new OperationResult(false, "Username cannot be empty.");
    }
    
    if (username.equals(currentUser.getUsername())) {
        return new OperationResult(false, "You cannot deactivate yourself.");
    }
    
    User user = userManager.getUser(username);
    if (user == null) {
        return new OperationResult(false, "User not found.");
    }
    
    if (!user.isActive()) {
        return new OperationResult(false, "User is already deactivated.");
    }
    
    user.deactivate();
    boolean success = userManager.updateUser(user);
    
    if (success) {
        EmailNotification emailNotif = new EmailNotification();
        emailNotif.prepareAccountDeactivated(user);
        emailNotif.send();
        
        return new OperationResult(true, "User '" + username + "' deactivated successfully.");
    } else {
        return new OperationResult(false, "Failed to deactivate user.");
    }
}

public OperationResult activateUser(User currentUser, String username) {
    if (!accessControl.hasPermission(currentUser, "MANAGE_USERS")) {
        return new OperationResult(false, "You do not have permission to activate users.");
    }
    
    if (username == null || username.trim().isEmpty()) {
        return new OperationResult(false, "Username cannot be empty.");
    }
    
    User user = userManager.getUser(username);
    if (user == null) {
        return new OperationResult(false, "User not found.");
    }
    
    if (user.isActive()) {
        return new OperationResult(false, "User is already active.");
    }
    
    user.activate();
    boolean success = userManager.updateUser(user);
    
    if (success) {
        return new OperationResult(true, "User '" + username + "' activated successfully.");
    } else {
        return new OperationResult(false, "Failed to activate user.");
    }
}

public void logout(User user) {
    if (user == null) return;
    
    authentication.logout(user);
    
    EmailNotification emailNotif = new EmailNotification();
    emailNotif.prepareLogout(user);
    emailNotif.send();
}

public UserManager getUserManager() {
    return userManager;
}

public AccessControl getAccessControl() {
    return accessControl;
}

public Authentication getAuthentication() {
    return authentication;
}
}