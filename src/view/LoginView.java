package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel implements ICustomObserver {
    private JLabel jcomp1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel jcomp5;
    private JLabel jcomp6;
    public LoginView() {
        //construct components
        jcomp1 = new JLabel("Login");
        usernameField = new JTextField (5);
        passwordField = new JPasswordField (5);
        loginButton = new JButton ("Login");
        loginButton.addActionListener(new LoginActionListener(usernameField, passwordField));
        jcomp5 = new JLabel ("Name");
        jcomp6 = new JLabel ("Password");
        //adjust size and set layout
        setPreferredSize (new Dimension (771, 509));
        setLayout (null);
        //add components
        add (jcomp1);
        add (usernameField);
        add (passwordField);
        add (loginButton);
        add (jcomp5);
        add (jcomp6);
        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (360, 45, 50, 25);
        usernameField.setBounds (325, 85, 100, 25);
        passwordField.setBounds (325, 110, 100, 25);
        loginButton.setBounds (325, 160, 100, 25);
        jcomp5.setBounds (285, 85, 35, 25);
        jcomp6.setBounds (260, 110, 65, 25); }


    @Override
    public void update(ICustomSubject subject) {

    }
}
