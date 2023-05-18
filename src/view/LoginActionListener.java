package view;

import model.Researcher;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class LoginActionListener implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;


    public LoginActionListener(JTextField usernameField, JPasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // Perform login validation here

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}