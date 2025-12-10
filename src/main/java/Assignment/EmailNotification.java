package Assignment;

public class EmailNotification {

    private String recipientEmail;
    private String subject;
    private String body;
    private boolean sent;

    public void prepareLoginNotification(User user) {
        this.recipientEmail = user.getEmail();
        this.subject = "Login Notification";
        this.body = "Hello " + user.getFullName()
                + ", you have just logged in to the system.";
        this.sent = false;
    }

    public void prepareAccountCreated(User user) {
        this.recipientEmail = user.getEmail();
        this.subject = "Account Created";
        this.body = "Hello " + user.getFullName()
                + ", your account has been created.";
        this.sent = false;
    }

    public void preparePasswordReset(User user, String resetLink) {
        this.recipientEmail = user.getEmail();
        this.subject = "Password Reset Request";
        this.body = "Hello " + user.getFullName()
                + ",\n\nWe received a request to reset your password. "
                + "If you requested this, please click the link below to reset your password. "
                + "This link will expire in 15 minutes.\n\n"
                + resetLink + "\n\n"
                + "If you did not request a password reset, please ignore this email.\n\n"
                + "Regards,\nYour System";
        this.sent = false;
    }

    public void prepareAccountUpdated(User user) {
        this.recipientEmail = user.getEmail();
        this.subject = "Account Updated";
        this.body = "Hello " + user.getFullName()
                + ", your account information has been updated.";
        this.sent = false;
    }

    public void prepareAccountDeactivated(User user) {
        this.recipientEmail = user.getEmail();
        this.subject = "Account Deactivated";
        this.body = "Hello " + user.getFullName()
                + ", your account has been deactivated.";
        this.sent = false;
    }

    public void prepareLogout(User user) {
        this.recipientEmail = user.getEmail();
        this.subject = "Logout Notification";
        this.body = "Hello " + user.getFullName()
                + ", you have just logged out.";
        this.sent = false;
    }

    public void send() {
        System.out.println("Sending email to " + recipientEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        this.sent = true;
    }

    public boolean isSent() {
        return sent;
    }
}