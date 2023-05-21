package view;

import controller.ResearcherController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import java.awt.*;
import java.util.Objects;

public class LoginView extends JPanel {
    private JLabel loginTitle;
    private JLabel usernameTag;
    private JTextField usernameField;
    private JLabel passwordTag;
    private JPasswordField passwordField;
    private JPopupMenu.Separator seperator;
    private JButton loginButton;
    private ResearcherController researcherController;
    private JFrame frame;
    private JPanel homePageView;

    public LoginView(JFrame frame, ResearcherController researcherController, JPanel homePageView) {
        //construct components
        this.loginTitle = new JLabel();
        this.usernameTag = new JLabel();
        this.usernameField = new JTextField();
        this.passwordTag = new JLabel();
        this.passwordField = new JPasswordField();
        this.seperator = new JPopupMenu.Separator();
        this.loginButton = new JButton();
        this.researcherController = researcherController;
        this.frame = frame;
        LoginActionListener actionListener = new LoginActionListener(frame, usernameField, passwordField, researcherController, homePageView);

        setLayout(new MigLayout(
                "insets 50,hidemode 3,align center center",
                // columns
                "[fill]" +
                        "[left]" +
                        "[fill]",
                // rows
                "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[15]" +
                        "[]" +
                        "[]" +
                        "[]"));

        //---- LoginTitle ----
        loginTitle.setText("Welcome to OpenResearcher");
        loginTitle.setForeground(new Color(0x00cccc));
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        add(loginTitle, "cell 1 0,align center top,grow 0 0");

        //---- UsernameTag ----
        usernameTag.setText("Username");
        usernameTag.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        usernameTag.setLabelFor(usernameField);
        add(usernameTag, "cell 0 2,alignx right,growx 0");

        //---- UsernameField ----
        usernameField.addCaretListener(e -> usernameFieldCaretUpdate(e));
        add(usernameField, "pad 0,cell 1 2,align left center,grow 0 0,wmin 450");

        //---- PasswordTag ----
        passwordTag.setText("Password");
        passwordTag.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordTag.setLabelFor(passwordField);
        add(passwordTag, "cell 0 3,alignx right,growx 0");

        //---- PasswordField ----
        passwordField.addCaretListener(e -> passwordFieldCaretUpdate(e));
        add(passwordField, "cell 1 3,alignx left,growx 0,wmin 450");
        add(seperator, "cell 0 4 2 1");

        //---- LoginButton ----
        loginButton.setText("LOG IN");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        loginButton.setBackground(new Color(0x00cccc));
        loginButton.setForeground(Color.white);
        loginButton.setEnabled(false);
        add(loginButton, "cell 1 5,growx");
        loginButton.addActionListener(actionListener);
    }
    private void usernameFieldCaretUpdate(CaretEvent e) {
        char[] password = passwordField.getPassword();
        String username = usernameField.getText();
        loginButton.setEnabled(password.length != 0 && !Objects.equals(username, ""));
    }

    private void passwordFieldCaretUpdate(CaretEvent e) {
        char[] password = passwordField.getPassword();
        String username = usernameField.getText();
        loginButton.setEnabled(password.length != 0 && !Objects.equals(username, ""));
    }


}
