package view;

import controller.ResearcherController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel implements ICustomObserver {
    private JLabel jcomp1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private ResearcherController researcherController;
    private JFrame frame;

    public LoginView(JFrame frame, ResearcherController researcherController) {
        //construct components
        this.researcherController = researcherController;
        this.frame = frame;
        jcomp1 = new JLabel("Login");
        usernameField = new JTextField(5);
        passwordField = new JPasswordField(5);
        loginButton = new JButton("Login");
        LoginActionListener actionListener = new LoginActionListener(frame, usernameField, passwordField, researcherController);
        loginButton.addActionListener(actionListener);
        jcomp5 = new JLabel("Name");
        jcomp6 = new JLabel("Password");
        //adjust size and set layout
        setPreferredSize(new Dimension(771, 509));
        setLayout(null);
        //add components
        add(jcomp1);
        add(usernameField);
        add(passwordField);
        add(loginButton);
        add(jcomp5);
        add(jcomp6);
        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(360, 45, 50, 25);
        usernameField.setBounds(325, 85, 100, 25);
        passwordField.setBounds(325, 110, 100, 25);
        loginButton.setBounds(325, 160, 100, 25);
        jcomp5.setBounds(285, 85, 35, 25);
        jcomp6.setBounds(260, 110, 65, 25);
    }


    @Override
    public void update(ICustomSubject subject) {
        if (researcherController.getLoggedIn()) {
            switchDashboardView();
        }
    }

    private void switchDashboardView() {
        // Code to switch to the main page
//        frame.dispose(); // Close the login window
//        JFrame mainFrame = new JFrame("Main Page");
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.setSize(400, 300);
        frame.getContentPane().remove(0);
        frame.getContentPane().add(new DashboardView(frame, researcherController));
        frame.pack();
        frame.setVisible(true);
        // Add components or perform any other actions for the main page
    }
}
