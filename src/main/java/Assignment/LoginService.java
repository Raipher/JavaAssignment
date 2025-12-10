package Assignment;

public class LoginService {

    private final Authentication authentication;
    private final AccessControl accessControl;
    private final UserManager userManager;
    private final PasswordResetService passwordResetService;
    private final UserFileRepository userRepo;

    public LoginService(Authentication authentication,
                        AccessControl accessControl,
                        UserManager userManager,
                        PasswordResetService passwordResetService,
                        UserFileRepository userRepo) {
        this.authentication = authentication;
        this.accessControl = accessControl;
        this.userManager = userManager;
        this.passwordResetService = passwordResetService;
        this.userRepo = userRepo;
    }

    public static class LoginResult {
        public final boolean success;
        public final String message;
        public final boolean canResetPassword;
        public final boolean canManageUsers;
        public final User user;

        public LoginResult(boolean success, String message,
                           boolean canResetPassword,
                           boolean canManageUsers,
                           User user) {
            this.success = success;
            this.message = message;
            this.canResetPassword = canResetPassword;
            this.canManageUsers = canManageUsers;
            this.user = user;
        }
    }

    public LoginResult login(String username, String password) {
        if (username == null || username.trim().isEmpty()
                || password == null || password.isEmpty()) {
            return new LoginResult(false,
                    "Username and password cannot be empty",
                    false, false, null);
        }

        boolean ok = authentication.login(username, password);
        User user = authentication.getUser(username);

        if (!ok) {
            if (user != null && !user.isActive()) {
                return new LoginResult(false,
                        "Account locked after 3 failed attempts. Please contact admin.",
                        false, false, null);
            } else {
                int failed = authentication.getFailedLoginCount(username);
                int remaining = Math.max(0, 3 - failed);
                return new LoginResult(false,
                        "Username or password error. Remaining attempts: " + remaining,
                        false, false, null);
            }
        }

        if (!accessControl.hasPermission(user, "LOGIN")) {
            return new LoginResult(false,
                    "The current user does not have LOGIN permission.",
                    false, false, null);
        }

        EmailNotification email = new EmailNotification();
        email.prepareLoginNotification(user);
        email.send();

        boolean canReset = true;
        boolean canManage = accessControl.hasPermission(user, "MANAGE_USERS");

        String msg = "Login successful, time(ms): " +
                authentication.getLastLoginMillis()
                + ", binary: " + authentication.getLastLoginBinary();

        return new LoginResult(true, msg, canReset, canManage, user);
    }

    public static class ResetRequestResult {
        public final boolean success;
        public final String message;

        public ResetRequestResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }

    public ResetRequestResult requestPasswordReset(String usernameOrEmail) {
        if (usernameOrEmail == null || usernameOrEmail.trim().isEmpty()) {
            return new ResetRequestResult(false,
                    "Please enter username or email to reset password.");
        }

        String username = usernameOrEmail.trim();
        User user = authentication != null ? authentication.getUser(username) : null;

        if (user == null && userRepo != null) {
            java.util.Map<String, User> users = userRepo.loadUsers();
            user = users.get(username);
        }

        if (user == null) {
            return new ResetRequestResult(true,
                    "If an account exists for that username/email, a reset link has been sent.");
        }

        if (!user.isActive()) {
            return new ResetRequestResult(false,
                    "Account is deactivated. Contact administrator.");
        }

        String token = passwordResetService.requestResetForExistingUser(user.getUsername());
        if (token == null) {
            return new ResetRequestResult(false,
                    "Too many reset requests. Please try again later.");
        }

        String resetLink = "http://localhost/reset?token=" + token;

        EmailNotification email = new EmailNotification();
        email.preparePasswordReset(user, resetLink);
        email.send();

        return new ResetRequestResult(true,
                "If an account exists for that username/email, a reset link has been sent.");
    }

    public boolean completePasswordReset(String token, String newPassword) {
        if (token == null || newPassword == null || newPassword.isEmpty()) return false;

        String username = passwordResetService.getUsernameForToken(token);
        if (username == null) {
            return false;
        }

        User user = userManager.getAllUsers().get(username);
        if (user == null) return false;

        user.setPassword(newPassword);
        userManager.updateUser(user);
        passwordResetService.consumeToken(token);
        return true;
    }
}