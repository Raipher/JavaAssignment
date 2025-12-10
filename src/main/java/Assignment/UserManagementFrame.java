package Assignment;

import java.awt.Color;

public class UserManagementFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserManagementFrame.class.getName());
    
    private UserManagementService userManagementService;
    private Authentication authentication;
    private AccessControl accessControl;
    private UserManager userManager;
    private User currentUser;

    public UserManagementFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("User Management");
        
        btnAddUser.setEnabled(false);
        btnDeactivateUser.setEnabled(false);
        txtRole.setEnabled(false);
        btnUpdateUser.setEnabled(false);
        
        lblstatus.setText("Please open via Login (insufficient context).");
    }
    
    public UserManagementFrame(Authentication authentication, AccessControl accessControl,UserManager userManager,User currentUser, UserManagementService userManagementService) {
        this.authentication = authentication;
        this.accessControl = accessControl;
        this.userManager = userManager;
        this.currentUser = currentUser;
        this.userManagementService = userManagementService;

        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("User Management");

        boolean canManage = (this.accessControl != null && this.currentUser != null)
                && this.accessControl.hasPermission(this.currentUser, "MANAGE_USERS");   //calls AccessControl.hasPermission(...)

        btnAddUser.setEnabled(canManage);
        btnDeactivateUser.setEnabled(canManage);
        txtRole.setEnabled(canManage);

        btnUpdateUser.setEnabled(this.currentUser != null);

        lblstatus.setText(" ");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtFullName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtRole = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnAddUser = new javax.swing.JButton();
        btnUpdateUser = new javax.swing.JButton();
        btnDeactivateUser = new javax.swing.JButton();
        lblstatus = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblUsername.setText("Username: ");

        lblFullName.setText("Full Name: ");

        lblEmail.setText("Email: ");

        lblRole.setText("Role: ");

        jLabel2.setText("Password: ");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnAddUser.setText("Add User");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnUpdateUser.setText("Update User");
        btnUpdateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUserActionPerformed(evt);
            }
        });

        btnDeactivateUser.setText("Deactivate User");
        btnDeactivateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeactivateUserActionPerformed(evt);
            }
        });

        lblstatus.setText(" ");

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAddUser)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateUser)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeactivateUser)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))))
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEmail)
                    .addComponent(lblUsername)
                    .addComponent(lblFullName)
                    .addComponent(lblRole)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsername)
                    .addComponent(txtFullName)
                    .addComponent(txtEmail)
                    .addComponent(txtRole)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFullName)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRole)
                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddUser)
                    .addComponent(btnUpdateUser)
                    .addComponent(btnDeactivateUser))
                .addGap(18, 18, 18)
                .addComponent(btnClose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblstatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        String username = txtUsername.getText().trim();
        String fullName = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String role = txtRole.getText().trim();
        String password = new String(txtPassword.getPassword());

        UserManagementService.OperationResult result = 
            userManagementService.addUser(currentUser, username, fullName, email, role, password);

        showResult(result);

        if (result.success) {
            clearFields();
        }
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUserActionPerformed
        String username = txtUsername.getText().trim();
        String fullName = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String role = txtRole.getText().trim();
        String password = new String(txtPassword.getPassword());

        UserManagementService.OperationResult result = 
            userManagementService.updateUser(currentUser, username, fullName, email, role, password);

        showResult(result);

        if (result.success) {
            txtPassword.setText("");
        }
    }//GEN-LAST:event_btnUpdateUserActionPerformed

    private void btnDeactivateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeactivateUserActionPerformed
        String username = txtUsername.getText().trim();

        UserManagementService.OperationResult result = 
            userManagementService.deactivateUser(currentUser, username);

        showResult(result);
    }//GEN-LAST:event_btnDeactivateUserActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed
    
    private void showResult(UserManagementService.OperationResult result) {
        lblstatus.setForeground(result.success ? new Color(0, 128, 0) : Color.RED);
        lblstatus.setText(result.message);
    }
    
    private void clearFields() {
        txtUsername.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        txtRole.setText("");
        txtPassword.setText("");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDeactivateUser;
    private javax.swing.JButton btnUpdateUser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblstatus;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
