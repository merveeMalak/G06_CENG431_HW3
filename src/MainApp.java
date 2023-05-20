import java.beans.*;
import javax.swing.event.*;

import controller.PaperController;
import controller.ReadingListController;
import controller.ResearcherController;
import model.Paper;
import model.ReadingList;
import net.miginfocom.swing.MigLayout;
//import org.jdesktop.swingx.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
/*
 * Created by JFormDesigner on Sat May 20 02:04:12 TRT 2023
 */


/**
 * @author hazim
 */
public class MainApp extends JPanel {
    ResearcherController researcherController;
    PaperController paperController;
    ReadingListController readingListController;

    public MainApp() {
        this.researcherController = new ResearcherController();
        this.paperController = new PaperController();
        this.readingListController = new ReadingListController();
        initComponents();
        MainFrame.getContentPane().add(LoginView);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }

    private void Login(ActionEvent e) {
        String username = UsernameField.getText();
        String password = new String(PasswordField.getPassword());
        researcherController.setUsername(username);
        researcherController.setPassword(password);
        researcherController.setLoggedIn(researcherController.checkCredentials());
        if (!researcherController.getLoggedIn()) {
            UsernameField.setText("");
            PasswordField.setText("");
            JOptionPane.showMessageDialog(MainFrame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            MainFrame.getContentPane().removeAll();
            MainFrame.getContentPane().add(HomePageView);
            MainFrame.pack();
            MainFrame.setVisible(true);
        }
    }


    private void UsernameFieldCaretUpdate(CaretEvent e) {
        char[] password = PasswordField.getPassword();
        String username = UsernameField.getText();
        LoginButton.setEnabled(password.length != 0 && !Objects.equals(username, ""));
    }

    private void PasswordFieldCaretUpdate(CaretEvent e) {
        char[] password = PasswordField.getPassword();
        String username = UsernameField.getText();
        LoginButton.setEnabled(password.length != 0 && !Objects.equals(username, ""));
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
    }

    private void UsernameFieldPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void Papers(ActionEvent e) {
        PaperList.setListData(paperController.getPapers().toArray(new Paper[0]));
        PaperList.setSelectedValue(paperController.getPapers().get(0), true);
        for (ReadingList readingList : readingListController.getReadingLists(researcherController.getResearcherName())) {
            ReadingListDropdown.addItem(readingList);
        }
        MainFrame.getContentPane().removeAll();
        MainFrame.getContentPane().add(PapersView);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }

    private void PaperListValueChanged(ListSelectionEvent e) {
        Paper selectedPaper = PaperList.getSelectedValue();
        PaperTitle.setText(selectedPaper.getTitle());
        PaperInfo.setText(selectedPaper.getInfo());
    }

    private void Download(ActionEvent e) {
        Paper selectedPaper = PaperList.getSelectedValue();
        selectedPaper.increaseOneNumOfDownloads();
        paperController.notifyObservers();
    }

    private void MyReadingList(ActionEvent e) {
        MyReadingList.setListData(readingListController.getReadingLists(researcherController.getResearcherName()).toArray(new ReadingList[0]));
        if (readingListController.getReadingLists(researcherController.getResearcherName()).size() > 0) {
            MyReadingList.setSelectedValue(readingListController.getReadingLists(researcherController.getResearcherName()).get(0), true);
        }
        MainFrame.getContentPane().removeAll();
        MainFrame.getContentPane().add(ReadingListView);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }

    private void CreateInputFieldCaretUpdate(CaretEvent e) {
        CreateReadingListButton.setEnabled(!Objects.equals(CreateInputField.getText(), ""));
    }


    private void CreateReadingList(ActionEvent e) {
        String newReadingListName = CreateInputField.getText();
        readingListController.addReadingList(0, newReadingListName, researcherController.getResearcherName());
        MyReadingList.setListData(readingListController.getReadingLists(researcherController.getResearcherName()).toArray(new ReadingList[0]));
        CreateInputField.setText("");
    }

    private void AddPaperToReadingList(ActionEvent e) {
        Paper selectedPaper = PaperList.getSelectedValue();
        ReadingList selectedReadingList = (ReadingList) ReadingListDropdown.getSelectedItem();
        if (selectedReadingList != null) {
            selectedReadingList.addPaper(selectedPaper);
            System.out.println(selectedReadingList.getNameOfPapers());
            System.out.println(selectedReadingList.getNumberOfPapers());
            readingListController.setReadingList(selectedReadingList);
        }
        readingListController.notifyObservers();
    }

    private void Add(ActionEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Hazim Alper Ata
        MainFrame = new JFrame();
        HomePageView = new JPanel();
        HelloText = new JLabel();
        AskText = new JLabel();
        PapersButton = new JButton();
        ResearchersButton = new JButton();
        MyReadingListButton = new JButton();
        LoginView = new JPanel();
        LoginTitle = new JLabel();
        UsernameTag = new JLabel();
        UsernameField = new JTextField();
        PasswordTag = new JLabel();
        PasswordField = new JPasswordField();
        Seperator = new JPopupMenu.Separator();
        LoginButton = new JButton();
        ControlPanelView2 = new JPanel();
        label4 = new JLabel();
        button2 = new JButton();
        label5 = new JLabel();
        scrollPane2 = new JScrollPane();
        list2 = new JList();
        button12 = new JButton();
        label6 = new JLabel();
        label7 = new JLabel();
        scrollPane3 = new JScrollPane();
        list3 = new JList();
        scrollPane4 = new JScrollPane();
        list4 = new JList();
        PapersView = new JPanel();
        Title = new JLabel();
        ReturnButton = new JButton();
        PaperTitle = new JLabel();
        scrollPane1 = new JScrollPane();
        PaperList = new JList<>();
        PaperInfo = new JLabel();
        ReadingListDropdown = new JComboBox<ReadingList>();
        DownloadButton = new JButton();
        AddButton = new JButton();
        ReadingListView = new JPanel();
        ReadingListViewTitle = new JLabel();
        ReturnHomePageButton = new JButton();
        scrollPane5 = new JScrollPane();
        MyReadingList = new JList<>();
        label10 = new JLabel();
        scrollPane6 = new JScrollPane();
        ReadingListPapers = new JList();
        label9 = new JLabel();
        CreateInputField = new JTextField();
        CreateReadingListButton = new JButton();
        RemoveSelectedPaperButton = new JButton();

        //======== MainFrame ========
        {
            MainFrame.setTitle("OpenResearcher");
            var MainFrameContentPane = MainFrame.getContentPane();
            MainFrameContentPane.setLayout(new BoxLayout(MainFrameContentPane, BoxLayout.PAGE_AXIS));
            MainFrame.setSize(790, 425);
            MainFrame.setLocationRelativeTo(MainFrame.getOwner());
        }

        //======== HomePageView ========
        {
            HomePageView.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
            border.EmptyBorder(0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax.swing.border.TitledBorder.CENTER
            ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069al\u006fg",java.awt.Font
            .BOLD,12),java.awt.Color.red),HomePageView. getBorder()));HomePageView. addPropertyChangeListener(
            new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062or\u0064er"
            .equals(e.getPropertyName()))throw new RuntimeException();}});
            HomePageView.setLayout(new MigLayout(
                "insets panel,hidemode 3,align center center",
                // columns
                "[205,fill]20" +
                "[236,fill]20" +
                "[205,fill]",
                // rows
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- HelloText ----
            HelloText.setText("Hello Name");
            HelloText.setForeground(new Color(0x00cccc));
            HelloText.setFont(new Font("Segoe UI", Font.BOLD, 30));
            HomePageView.add(HelloText, "cell 1 0,alignx center,growx 0");

            //---- AskText ----
            AskText.setText(" What would you like to display?");
            AskText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            HomePageView.add(AskText, "cell 1 2,alignx center,growx 0");

            //---- PapersButton ----
            PapersButton.setText("Papers");
            PapersButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            PapersButton.setBackground(new Color(0x00cccc));
            PapersButton.setForeground(Color.white);
            PapersButton.setIcon(UIManager.getIcon("FileView.fileIcon"));
            PapersButton.addActionListener(e -> {
            button1(e);
            Papers(e);
        });
            HomePageView.add(PapersButton, "cell 0 4,growx");

            //---- ResearchersButton ----
            ResearchersButton.setText("Researchers");
            ResearchersButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            ResearchersButton.setBackground(new Color(0x00cccc));
            ResearchersButton.setForeground(Color.white);
            ResearchersButton.setIcon(UIManager.getIcon("PasswordField.revealIcon"));
            ResearchersButton.addActionListener(e -> button1(e));
            HomePageView.add(ResearchersButton, "cell 1 4,growx");

            //---- MyReadingListButton ----
            MyReadingListButton.setText("My Reading Lists");
            MyReadingListButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            MyReadingListButton.setBackground(new Color(0x00cccc));
            MyReadingListButton.setForeground(Color.white);
            MyReadingListButton.setIcon(UIManager.getIcon("FileChooser.detailsViewIcon"));
            MyReadingListButton.addActionListener(e -> {
            button1(e);
            MyReadingList(e);
        });
            HomePageView.add(MyReadingListButton, "cell 2 4,alignx center,growx 0");
        }

        //======== LoginView ========
        {
            LoginView.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
            javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax
            . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
            .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,LoginView. getBorder( )) ); LoginView. addPropertyChangeListener (new java. beans.
            PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .
            equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            LoginView.setLayout(new MigLayout(
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
            LoginTitle.setText("Welcome to OpenResearcher");
            LoginTitle.setForeground(new Color(0x00cccc));
            LoginTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
            LoginView.add(LoginTitle, "cell 1 0,align center top,grow 0 0");

            //---- UsernameTag ----
            UsernameTag.setText("Username");
            UsernameTag.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            UsernameTag.setLabelFor(UsernameField);
            LoginView.add(UsernameTag, "cell 0 2,alignx right,growx 0");

            //---- UsernameField ----
            UsernameField.addPropertyChangeListener("text", e -> UsernameFieldPropertyChange(e));
            UsernameField.addCaretListener(e -> UsernameFieldCaretUpdate(e));
            UsernameField.addPropertyChangeListener(e -> UsernameFieldPropertyChange(e));
            LoginView.add(UsernameField, "pad 0,cell 1 2,align left center,grow 0 0,wmin 450");

            //---- PasswordTag ----
            PasswordTag.setText("Password");
            PasswordTag.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            PasswordTag.setLabelFor(PasswordField);
            LoginView.add(PasswordTag, "cell 0 3,alignx right,growx 0");

            //---- PasswordField ----
            PasswordField.addCaretListener(e -> PasswordFieldCaretUpdate(e));
            LoginView.add(PasswordField, "cell 1 3,alignx left,growx 0,wmin 450");
            LoginView.add(Seperator, "cell 0 4 2 1");

            //---- LoginButton ----
            LoginButton.setText("LOG IN");
            LoginButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            LoginButton.setBackground(new Color(0x00cccc));
            LoginButton.setForeground(Color.white);
            LoginButton.setEnabled(false);
            LoginButton.addActionListener(e -> {
            button1(e);
            Login(e);
        });
            LoginView.add(LoginButton, "cell 1 5,growx");
        }

        //======== ControlPanelView2 ========
        {
            ControlPanelView2.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0
            ,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
            ,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red),
            ControlPanelView2. getBorder()));ControlPanelView2. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}});
            ControlPanelView2.setLayout(new MigLayout(
                "fill,hidemode 3,align center center",
                // columns
                "[205,fill]" +
                "[103,fill]" +
                "[20,fill]" +
                "[164,fill]" +
                "[fill]",
                // rows
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- label4 ----
            label4.setText("Researchers");
            label4.setFont(new Font("Segoe UI", Font.BOLD, 28));
            ControlPanelView2.add(label4, "cell 0 0 3 1");

            //---- button2 ----
            button2.setText("Return Homepage");
            button2.setIcon(UIManager.getIcon("FileChooser.homeFolderIcon"));
            ControlPanelView2.add(button2, "cell 4 0,align right top,grow 0 0");

            //---- label5 ----
            label5.setText("Researcher Name");
            label5.setFont(new Font("Segoe UI", Font.BOLD, 36));
            ControlPanelView2.add(label5, "cell 2 1 3 1,alignx center,growx 0");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(list2);
            }
            ControlPanelView2.add(scrollPane2, "cell 0 1 1 5,growy");

            //---- button12 ----
            button12.setText("Follow");
            button12.setFont(new Font("Segoe UI", Font.BOLD, 22));
            button12.setBackground(new Color(0x00cccc));
            button12.setForeground(Color.white);
            button12.setIcon(UIManager.getIcon("FileView.computerIcon"));
            button12.addActionListener(e -> button1(e));
            ControlPanelView2.add(button12, "cell 1 1,alignx center,growx 0");

            //---- label6 ----
            label6.setText("Reading Lists");
            label6.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            ControlPanelView2.add(label6, "cell 1 2");

            //---- label7 ----
            label7.setText("Papers");
            label7.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            ControlPanelView2.add(label7, "cell 3 2");

            //======== scrollPane3 ========
            {
                scrollPane3.setViewportView(list3);
            }
            ControlPanelView2.add(scrollPane3, "cell 1 3 2 3,growy");

            //======== scrollPane4 ========
            {
                scrollPane4.setViewportView(list4);
            }
            ControlPanelView2.add(scrollPane4, "cell 3 3 2 3,growy");
        }

        //======== PapersView ========
        {
            PapersView.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
            ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,PapersView. getBorder
            ( )) ); PapersView. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );
            PapersView.setLayout(new MigLayout(
                "fill,hidemode 3,align center center",
                // columns
                "[205,fill]" +
                "[103,fill]" +
                "[20,fill]" +
                "[164,fill]" +
                "[fill]",
                // rows
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- Title ----
            Title.setText("Papers");
            Title.setFont(new Font("Segoe UI", Font.BOLD, 28));
            PapersView.add(Title, "cell 0 0 3 1");

            //---- ReturnButton ----
            ReturnButton.setText("Return Homepage");
            ReturnButton.setIcon(UIManager.getIcon("FileChooser.homeFolderIcon"));
            PapersView.add(ReturnButton, "cell 4 0,align right top,grow 0 0");

            //---- PaperTitle ----
            PaperTitle.setText("PAPER TITLE");
            PaperTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
            PapersView.add(PaperTitle, "cell 3 1 2 1,alignx center,growx 0");

            //======== scrollPane1 ========
            {

                //---- PaperList ----
                PaperList.addListSelectionListener(e -> PaperListValueChanged(e));
                scrollPane1.setViewportView(PaperList);
            }
            PapersView.add(scrollPane1, "cell 0 1 2 5,growy");

            //---- PaperInfo ----
            PaperInfo.setText("PAPER HAKKINDA BILGILER");
            PaperInfo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            PapersView.add(PaperInfo, "cell 3 2 2 2,aligny top,growy 0");
            PapersView.add(ReadingListDropdown, "cell 3 4 2 1");

            //---- DownloadButton ----
            DownloadButton.setText("Download");
            DownloadButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            DownloadButton.setBackground(new Color(0x00cccc));
            DownloadButton.setForeground(Color.white);
            DownloadButton.addActionListener(e -> {
            button1(e);
            Download(e);
        });
            PapersView.add(DownloadButton, "cell 3 5,alignx center,growx 0");

            //---- AddButton ----
            AddButton.setText("Add My Reading List");
            AddButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            AddButton.setBackground(new Color(0x00cccc));
            AddButton.setForeground(Color.white);
            AddButton.addActionListener(e -> {
            button1(e);
            Add(e);
            AddPaperToReadingList(e);
        });
            PapersView.add(AddButton, "cell 4 5,alignx center,growx 0");
        }

        //======== ReadingListView ========
        {
            ReadingListView.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
            EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing
            .border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),
            java.awt.Color.red),ReadingListView. getBorder()));ReadingListView. addPropertyChangeListener(new java.beans.PropertyChangeListener()
            {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))
            throw new RuntimeException();}});
            ReadingListView.setLayout(new MigLayout(
                "fill,hidemode 3,align center center",
                // columns
                "[118,fill]" +
                "[205,fill]" +
                "[103,fill]" +
                "[20,fill]" +
                "[164,fill]" +
                "[fill]",
                // rows
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- ReadingListViewTitle ----
            ReadingListViewTitle.setText("My Reading Lists");
            ReadingListViewTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
            ReadingListView.add(ReadingListViewTitle, "cell 0 0 4 1");

            //---- ReturnHomePageButton ----
            ReturnHomePageButton.setText("Return Homepage");
            ReturnHomePageButton.setIcon(UIManager.getIcon("FileChooser.homeFolderIcon"));
            ReadingListView.add(ReturnHomePageButton, "cell 5 0,align right top,grow 0 0");

            //======== scrollPane5 ========
            {
                scrollPane5.setViewportView(MyReadingList);
            }
            ReadingListView.add(scrollPane5, "cell 0 1 3 5,growy");

            //---- label10 ----
            label10.setText("Papers");
            label10.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            ReadingListView.add(label10, "cell 4 1 2 1");

            //======== scrollPane6 ========
            {
                scrollPane6.setViewportView(ReadingListPapers);
            }
            ReadingListView.add(scrollPane6, "cell 4 2 2 4,growy");

            //---- label9 ----
            label9.setText("Reading List Name");
            ReadingListView.add(label9, "cell 0 6");

            //---- CreateInputField ----
            CreateInputField.addCaretListener(e -> CreateInputFieldCaretUpdate(e));
            ReadingListView.add(CreateInputField, "cell 1 6 2 1");

            //---- CreateReadingListButton ----
            CreateReadingListButton.setText("Create Reading List");
            CreateReadingListButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            CreateReadingListButton.setBackground(new Color(0x00cccc));
            CreateReadingListButton.setForeground(Color.white);
            CreateReadingListButton.setIcon(UIManager.getIcon("FileChooser.newFolderIcon"));
            CreateReadingListButton.setEnabled(false);
            CreateReadingListButton.addActionListener(e -> {
            button1(e);
            CreateReadingList(e);
        });
            ReadingListView.add(CreateReadingListButton, "cell 0 7 3 1,alignx center,growx 0");

            //---- RemoveSelectedPaperButton ----
            RemoveSelectedPaperButton.setText("Remove Selected Paper");
            RemoveSelectedPaperButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            RemoveSelectedPaperButton.setBackground(new Color(0x00cccc));
            RemoveSelectedPaperButton.setForeground(Color.white);
            RemoveSelectedPaperButton.setIcon(UIManager.getIcon("InternalFrame.closeIcon"));
            RemoveSelectedPaperButton.setEnabled(false);
            RemoveSelectedPaperButton.addActionListener(e -> button1(e));
            ReadingListView.add(RemoveSelectedPaperButton, "cell 4 7 2 1,alignx center,growx 0");
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Hazim Alper Ata
    private JFrame MainFrame;
    private JPanel HomePageView;
    private JLabel HelloText;
    private JLabel AskText;
    private JButton PapersButton;
    private JButton ResearchersButton;
    private JButton MyReadingListButton;
    private JPanel LoginView;
    private JLabel LoginTitle;
    private JLabel UsernameTag;
    private JTextField UsernameField;
    private JLabel PasswordTag;
    private JPasswordField PasswordField;
    private JPopupMenu.Separator Seperator;
    private JButton LoginButton;
    private JPanel ControlPanelView2;
    private JLabel label4;
    private JButton button2;
    private JLabel label5;
    private JScrollPane scrollPane2;
    private JList list2;
    private JButton button12;
    private JLabel label6;
    private JLabel label7;
    private JScrollPane scrollPane3;
    private JList list3;
    private JScrollPane scrollPane4;
    private JList list4;
    private JPanel PapersView;
    private JLabel Title;
    private JButton ReturnButton;
    private JLabel PaperTitle;
    private JScrollPane scrollPane1;
    private JList<Paper> PaperList;
    private JLabel PaperInfo;
    private JComboBox<ReadingList> ReadingListDropdown;
    private JButton DownloadButton;
    private JButton AddButton;
    private JPanel ReadingListView;
    private JLabel ReadingListViewTitle;
    private JButton ReturnHomePageButton;
    private JScrollPane scrollPane5;
    private JList<ReadingList> MyReadingList;
    private JLabel label10;
    private JScrollPane scrollPane6;
    private JList ReadingListPapers;
    private JLabel label9;
    private JTextField CreateInputField;
    private JButton CreateReadingListButton;
    private JButton RemoveSelectedPaperButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
