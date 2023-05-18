package view;

import controller.UserController;
import model.Researcher;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class LoginActionListener implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserController userController;

    public LoginActionListener(JTextField usernameField, JPasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.userController = new UserController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // Perform login validation here

        userController.setUsername(username);
        userController.setPassword(password);
        if (userController.isValidResearcher()){

        }
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }


}