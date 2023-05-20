//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//
//class User implements UserSubject {
//    private List<LoginObserver> observers = new ArrayList<>();
//    private boolean isLoggedIn;
//    private String username;
//    private String password;
//
//
//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//
//    public void attach(LoginObserver observer) {
//        observers.add(observer);
//    }
//
//
//    public void detach(LoginObserver observer) {
//        observers.remove(observer);
//    }
//
//
//    public void notifyObservers() {
//        for (LoginObserver observer : observers) {
//            observer.update(isLoggedIn);
//        }
//    }
//
//
//    public void setLoggedIn(boolean isLoggedIn) {
//        this.isLoggedIn = isLoggedIn;
//        notifyObservers();
//    }
//
//
//    public boolean isValidUser(String username, String password) {
//        return this.username.equals(username) && this.password.equals(password);
//    }
//}
//
//
//interface LoginObserver {
//    void update(boolean isLoggedIn);
//}
//
//
//class LoginView implements LoginObserver {
//    private JFrame frame;
//    private JButton loginButton;
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private User user;
//
//
//    public LoginView(User user) {
//        this.user = user;
//
//
//        frame = new JFrame("Login");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200);
//
//
//        JPanel panel = new JPanel();
//        frame.add(panel);
//
//
//        JLabel usernameLabel = new JLabel("Username:");
//        panel.add(usernameLabel);
//
//
//        usernameField = new JTextField(20);
//        panel.add(usernameField);
//
//
//        JLabel passwordLabel = new JLabel("Password:");
//        panel.add(passwordLabel);
//
//
//        passwordField = new JPasswordField(20);
//        panel.add(passwordField);
//
//
//        loginButton = new JButton("Login");
//        panel.add(loginButton);
//
//
//        loginButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                String password = new String(passwordField.getPassword());
//
//
//                if (user.isValidUser(username, password)) {
//                    user.setLoggedIn(true);
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//    }
//
//
//    public void update(boolean isLoggedIn) {
//        if (isLoggedIn) {
//            showMainPage();
//        }
//    }
//
//
//    private void showMainPage() {
//        // Code to switch to the main page
//        frame.dispose(); // Close the login window
//        JFrame mainFrame = new JFrame("Main Page");
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.setSize(400, 300);
//        mainFrame.setVisible(true);
//        // Add components or perform any other actions for the main page
//    }
//
//
//    public void showLogin() {
//        frame.setVisible(true);
//    }
//}
//
//
//public class LoginApp {
//    public static void main(String[] args) {
//        User user = new User("john", "password123");
//        LoginView loginView = new LoginView(user);
//        user.attach(loginView);
//
//
//        loginView.showLogin();
//    }
//}


//import javax.swing.*;
//import java.awt.*;
//
//public class PaperListViewExample {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Paper List View Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200);
//        frame.setLayout(new BorderLayout()); // Create a JList with a custom ListModel
//        JList<String> listView = new JList<>(new PaperListModel());
//        frame.add(new JScrollPane(listView), BorderLayout.CENTER);
//        frame.setVisible(true);
//    }
//
//    static class Paper {
//        private String name;
//        private String author;
//
//        public Paper(String name, String author) {
//            this.name = name;
//            this.author = author;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public String getAuthor() {
//            return author;
//        }
//    }
//
//    static class PaperListModel extends AbstractListModel<String> {
//        private Paper[] papers = {new Paper("Paper 1", "Author 1"), new Paper("Paper 2", "Author 2"), new Paper("Paper 3", "Author 3")};
//
//        @Override
//        public int getSize() {
//            return papers.length;
//        }
//
//        @Override
//        public String getElementAt(int index) {
//            return papers[index].getName();
//        }
//    }
//}

//public class ComboBoxExample {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("ComboBox Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200); // Create an array of items for the ComboBox
//        String[] items = {"Option 1", "Option 2", "Option 3"}; // Create a JComboBox and add the items
//        JComboBox<String> comboBox = new JComboBox<>(items);
//        comboBox.setSelectedItem(items[0]); // Set default selected item // Register ActionListener to handle item selection
//        comboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JComboBox<String> source = (JComboBox<String>) e.getSource();
//                String selectedItem = (String) source.getSelectedItem();
//                System.out.println("Selected Item: " + selectedItem);
//            }
//        }); // Add the ComboBox to the frame
//        frame.getContentPane().add(comboBox, BorderLayout.CENTER);
//        frame.setVisible(true);
//    }
//}