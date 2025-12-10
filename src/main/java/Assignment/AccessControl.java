package Assignment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AccessControl {

    private Map<String, Set<String>> rolePermissions = new HashMap<>();

    public void addPermissionToRole(String role, String permission) {
        rolePermissions
                .computeIfAbsent(role, r -> new HashSet<>())
                .add(permission);
    }

    public void assignRole(User user, String role) {
        user.setRole(role);
    }

    public void revokeRole(User user, String role) {
        if (role.equals(user.getRole())) {
            user.setRole(null);
        }
    }

    public boolean hasPermission(User user, String permission) {
        if (user == null || user.getRole() == null) {
            return false;
        }
        Set<String> permissions = rolePermissions.get(user.getRole());
        return permissions != null && permissions.contains(permission);
    }
}