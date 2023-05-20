package view;

import controller.ResearcherController;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginActionListener implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private ResearcherController researcherController;
    private JFrame frame;

    public LoginActionListener(JFrame frame, JTextField usernameField, JPasswordField passwordField, ResearcherController researcherController) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.researcherController = researcherController;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // Perform login validation here

        researcherController.setUsername(username);
        researcherController.setPassword(password);
        researcherController.setLoggedIn(researcherController.checkCredentials());
        if (!researcherController.getLoggedIn()) {
            JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}