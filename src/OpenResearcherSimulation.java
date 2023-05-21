import javax.swing.event.*;

import controller.PaperController;
import controller.ReadingListController;
import controller.ResearcherController;
import model.Paper;
import model.ReadingList;
import model.Researcher;
import net.miginfocom.swing.MigLayout;
import view.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class OpenResearcherSimulation extends JPanel {
    private JFrame MainFrame;
    private JPanel HomePageView;
    private JLabel HelloText;
    private JLabel AskText;
    private JButton PapersButton;
    private JButton ResearchersButton;
    private JButton MyReadingListButton;
    private JPanel LoginView;
    private JPanel ResearchersView;
    private JLabel label4;
    private JButton ReturnResearchers;
    private JLabel OtherResearcherNameTitle;
    private JScrollPane scrollPane2;
    private JList<Researcher> ResearchersList;
    private JButton FollowButton;
    private JLabel label6;
    private JLabel label7;
    private JScrollPane scrollPane3;
    private JList<ReadingList> OtherResearcherReadingList;
    private JScrollPane scrollPane4;
    private JList<Object> OtherResearcherPapersList;
    private JPanel ProfileView;
    private JLabel ReadingListViewTitle4;
    private JButton ReturnProfile;
    private JLabel ReadingListViewTitle2;
    private JLabel ReadingListViewTitle3;
    private JScrollPane scrollPane7;
    private JList<Object> MyFollowersList;
    private JScrollPane scrollPane8;
    private JList<Object> MyFollowingList;
    private JLabel ReadingListViewTitle;
    private JScrollPane scrollPane5;
    private JList<ReadingList> MyReadingList;
    private JLabel label10;
    private JScrollPane scrollPane6;
    private JList<String> ReadingListPapers;
    private JLabel label9;
    private JTextField CreateInputField;
    private JButton CreateReadingListButton;
    private JButton RemoveSelectedPaperButton;
    private JPanel PapersView;
    private JLabel Title;
    private JButton ReturnPapers;
    private JLabel PaperTitle;
    private JScrollPane scrollPane1;
    private JList<Paper> PaperList;
    private JLabel PaperInfo;
    private JComboBox<ReadingList> ReadingListDropdown;
    private JButton DownloadButton;
    private JButton AddButton;
    private JLabel NumberOfDownloads;
    private final ResearcherController researcherController;
    private final PaperController paperController;
    private final ReadingListController readingListController;

    public OpenResearcherSimulation() {
        this.researcherController = new ResearcherController();
        this.paperController = new PaperController();
        this.readingListController = new ReadingListController();
        initComponents();
        MainFrame.getContentPane().add(LoginView);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }

    private void Researchers(ActionEvent e) {
        MainFrame.getContentPane().removeAll();
        ResearchersList.setListData(researcherController.getOtherResearchers(researcherController.getResearcherName()).toArray(new Researcher[0]));
        ResearchersList.setSelectedValue(researcherController.getOtherResearchers(researcherController.getResearcherName()).get(0), true);
        OtherResearcherNameTitle.setText(researcherController.getOtherResearchers(researcherController.getResearcherName()).get(0).getName());
        MainFrame.getContentPane().add(ResearchersView);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }

    private void OtherResearcherPropertyChange(ListSelectionEvent e) {
        Researcher selectedResearcher = ResearchersList.getSelectedValue();
        if (selectedResearcher != null) {
            OtherResearcherNameTitle.setText(selectedResearcher.getName());
            List<ReadingList> readingLists = readingListController.getReadingLists(selectedResearcher.getName());
            if (readingLists != null && readingLists.size() > 0) {
                OtherResearcherReadingList.setListData(readingLists.toArray(new ReadingList[0]));
            } else {
                OtherResearcherReadingList.setListData(new Vector<>());
            }
        }
    }

    private void ReadingListsPropertyChange(ListSelectionEvent e) {
        ReadingList readingList = OtherResearcherReadingList.getSelectedValue();
        if (readingList != null && readingList.getNumberOfPapers() > 0) {
            OtherResearcherPapersList.setListData(readingList.getNameOfPapers().toArray());
        } else {
            OtherResearcherPapersList.setListData(new Vector<>());
        }
    }

    private void ChangeFollowButton(ListSelectionEvent e) {
        Researcher selectedResearcher = ResearchersList.getSelectedValue();
        if (selectedResearcher != null && researcherController.getIsFollowThisResearcher(selectedResearcher)) {
            FollowButton.setText("Unfollow");
        } else {
            FollowButton.setText("Follow");
        }
    }

    private void ChangeFollowStatus(ActionEvent e) {
        Researcher selectedResearcher = ResearchersList.getSelectedValue();
        if (selectedResearcher != null) {
            if (researcherController.getIsFollowThisResearcher(selectedResearcher)) {
                researcherController.setUnfollowResearcher(researcherController.getCurrentResearcher(), selectedResearcher);
                FollowButton.setText("Follow");
            } else {
                researcherController.setFollowResearcher(researcherController.getCurrentResearcher(), selectedResearcher);
                FollowButton.setText("Unfollow");
            }
            researcherController.notifyObservers();
        }
    }

    public void Papers(ActionEvent e) {
        MainFrame.getContentPane().removeAll();
        PaperList.setListData(paperController.getPapers().toArray(new Paper[0]));
        PaperList.setSelectedValue(paperController.getPapers().get(0), true);
        ReadingListDropdown.removeAllItems();
        for (ReadingList readingList : readingListController.getReadingLists(researcherController.getResearcherName())) {
            ReadingListDropdown.addItem(readingList);
        }
        MainFrame.getContentPane().add(PapersView);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }

    private void PaperListValueChanged(ListSelectionEvent e) {
        Paper selectedPaper = PaperList.getSelectedValue();
        if (selectedPaper != null) {
            NumberOfDownloads.setText(selectedPaper.getNumOfDownloads());
            PaperTitle.setText("<html><div style='text-align: center'>" + selectedPaper.getTitle() + "</div></html>");
            PaperInfo.setText("<html><div style='white-space: pre-line'>" + selectedPaper.getInfo() + "</div></html>");
        }
    }

    private void Download(ActionEvent e) {
        Paper selectedPaper = PaperList.getSelectedValue();
        selectedPaper.increaseOneNumOfDownloads();
        NumberOfDownloads.setText(selectedPaper.getNumOfDownloads());
        paperController.notifyObservers();
    }


    private void MyProfile(ActionEvent e) {
        MainFrame.getContentPane().removeAll();
        List<ReadingList> readingLists = readingListController.getReadingLists(researcherController.getResearcherName());
        ReadingList[] readingListArray = readingLists.toArray(new ReadingList[0]);
        MyReadingList.setListData(readingListArray);
        if (readingLists.size() > 0) {
            MyReadingList.setSelectedValue(readingLists.get(0), true);
            if (readingListController.getReadingLists(researcherController.getResearcherName()).get(0).getNumberOfPapers() > 0) {
                ReadingListPapers.setSelectedValue(readingListController.getReadingLists(researcherController.getResearcherName()).get(0).getNameOfPapers().get(0), true);
            }
        }
        List<Researcher> myFollowers = researcherController.getFollowers();
        if (myFollowers != null) {
            MyFollowersList.setListData(myFollowers.toArray());
        }
        List<Researcher> myFollowings = researcherController.getFollowings();
        if (myFollowings != null) {
            MyFollowingList.setListData(myFollowings.toArray());
        }


        MainFrame.getContentPane().add(ProfileView);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }

    private void CreateInputFieldCaretUpdate(CaretEvent e) {
        CreateReadingListButton.setEnabled(!Objects.equals(CreateInputField.getText(), ""));
    }


    private void CreateReadingList(ActionEvent e) {
        String newReadingListName = CreateInputField.getText();
        readingListController.addReadingList(newReadingListName, researcherController.getResearcherName());
        ReadingList[] readingLists = readingListController.getReadingLists(researcherController.getResearcherName()).toArray(new ReadingList[0]);
        MyReadingList.setListData(readingLists);
        CreateInputField.setText("");
    }

    private void AddPaperToReadingList(ActionEvent e) {
        Paper selectedPaper = PaperList.getSelectedValue();
        ReadingList selectedReadingList = (ReadingList) ReadingListDropdown.getSelectedItem();
        if (selectedReadingList != null && selectedReadingList.addPaper(selectedPaper)) {
            readingListController.setReadingList(selectedReadingList);
            readingListController.notifyObservers();
            JOptionPane.showMessageDialog(MainFrame, "Success", "Success", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(MainFrame, "This paper is already in this reading list", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void ReturnHomePage(ActionEvent e) {
        MainFrame.getContentPane().removeAll();
        MainFrame.getContentPane().add(HomePageView);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }

    private void MyReadingListValueChanged(ListSelectionEvent e) {
        ReadingList readingList = MyReadingList.getSelectedValue();
        RemoveSelectedPaperButton.setEnabled(false);
        if (readingList != null) {
            ReadingListPapers.setListData(readingList.getNameOfPapers().toArray(new String[0]));
        }
    }

    private void RemoveSelectedPaper(ActionEvent e) {
        ReadingList selectedReadingList = MyReadingList.getSelectedValue();
        if (selectedReadingList != null) {
            String selectedPaperName = ReadingListPapers.getSelectedValue();
            if (selectedPaperName != null) {
                if (selectedReadingList.removePaper(selectedPaperName)) {
                    readingListController.setReadingList(selectedReadingList);
                    JOptionPane.showMessageDialog(MainFrame, "Success", "Success", JOptionPane.PLAIN_MESSAGE);
                    RemoveSelectedPaperButton.setEnabled(false);
                    readingListController.notifyObservers();
                    ReadingList readingList = MyReadingList.getSelectedValue();
                    if (readingList != null) {
                        ReadingListPapers.setListData(readingList.getNameOfPapers().toArray(new String[0]));
                    }
                } else {
                    JOptionPane.showMessageDialog(MainFrame, "This paper is NOT in the selected reading list", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void SelectedPaperValueChanged(ListSelectionEvent e) {
        String selectedPaperName = ReadingListPapers.getSelectedValue();
        if (selectedPaperName != null) {
            RemoveSelectedPaperButton.setEnabled(true);
        }
    }

    private void initComponents() {
        MainFrame = new JFrame();
        HomePageView = new JPanel();
        HelloText = new JLabel();
        AskText = new JLabel();
        PapersButton = new JButton();
        ResearchersButton = new JButton();
        MyReadingListButton = new JButton();
        ResearchersView = new JPanel();
        label4 = new JLabel();
        ReturnResearchers = new JButton();
        OtherResearcherNameTitle = new JLabel();
        scrollPane2 = new JScrollPane();
        ResearchersList = new JList<>();
        FollowButton = new JButton();
        label6 = new JLabel();
        label7 = new JLabel();
        scrollPane3 = new JScrollPane();
        OtherResearcherReadingList = new JList<>();
        scrollPane4 = new JScrollPane();
        OtherResearcherPapersList = new JList<>();
        ProfileView = new JPanel();
        ReadingListViewTitle4 = new JLabel();
        ReturnProfile = new JButton();
        ReadingListViewTitle2 = new JLabel();
        ReadingListViewTitle3 = new JLabel();
        scrollPane7 = new JScrollPane();
        MyFollowersList = new JList<>();
        scrollPane8 = new JScrollPane();
        MyFollowingList = new JList<>();
        ReadingListViewTitle = new JLabel();
        scrollPane5 = new JScrollPane();
        MyReadingList = new JList<>();
        label10 = new JLabel();
        scrollPane6 = new JScrollPane();
        ReadingListPapers = new JList<String>();
        label9 = new JLabel();
        CreateInputField = new JTextField();
        CreateReadingListButton = new JButton();
        RemoveSelectedPaperButton = new JButton();
        PapersView = new JPanel();
        Title = new JLabel();
        ReturnPapers = new JButton();
        PaperTitle = new JLabel();
        scrollPane1 = new JScrollPane();
        PaperList = new JList<>();
        PaperInfo = new JLabel();
        ReadingListDropdown = new JComboBox<ReadingList>();
        DownloadButton = new JButton();
        AddButton = new JButton();
        NumberOfDownloads = new JLabel();


        //======== MainFrame ========
        {
            MainFrame.setTitle("OpenResearcher");
            var MainFrameContentPane = MainFrame.getContentPane();
            MainFrameContentPane.setLayout(new BoxLayout(MainFrameContentPane, BoxLayout.PAGE_AXIS));
            MainFrame.setSize(790, 800);
            MainFrame.setMinimumSize(new Dimension(800,800));
            MainFrame.setLocationRelativeTo(MainFrame.getOwner());
        }

        //======== HomePageView ========
        {
            HomePageView.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,HomePageView. getBorder( )) ); HomePageView. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
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
            PapersButton.addActionListener(e -> {Papers(e);});
            HomePageView.add(PapersButton, "cell 0 4,growx");

            //---- ResearchersButton ----
            ResearchersButton.setText("Researchers");
            ResearchersButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            ResearchersButton.setBackground(new Color(0x00cccc));
            ResearchersButton.setForeground(Color.white);
            ResearchersButton.setIcon(UIManager.getIcon("PasswordField.revealIcon"));
            ResearchersButton.addActionListener(e -> Researchers(e));
            HomePageView.add(ResearchersButton, "cell 1 4,growx");

            //---- MyReadingListButton ----
            MyReadingListButton.setText("My Profile");
            MyReadingListButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            MyReadingListButton.setBackground(new Color(0x00cccc));
            MyReadingListButton.setForeground(Color.white);
            MyReadingListButton.setIcon(UIManager.getIcon("FileChooser.detailsViewIcon"));
            MyReadingListButton.addActionListener(e -> {
            MyProfile(e);
        });
            HomePageView.add(MyReadingListButton, "cell 2 4,alignx center,growx 0");
        }

        //======== ResearchersView ========
        {
            ResearchersView.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0
            ,0,0,0), "",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
            ,new java.awt.Font("Dialo\u0067",java.awt.Font.BOLD,12),java.awt.Color.red),
            ResearchersView. getBorder()));ResearchersView. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException();}});
            ResearchersView.setLayout(new MigLayout(
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
            ResearchersView.add(label4, "cell 0 0 3 1");

            //---- ReturnResearchers ----
            ReturnResearchers.setText("Return Homepage");
            ReturnResearchers.setIcon(UIManager.getIcon("FileChooser.homeFolderIcon"));
            ReturnResearchers.addActionListener(e -> ReturnHomePage(e));
            ResearchersView.add(ReturnResearchers, "cell 4 0,align right top,grow 0 0");

            //---- OtherResearcherNameTitle ----
            OtherResearcherNameTitle.setText("Researcher Name");
            OtherResearcherNameTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
            ResearchersView.add(OtherResearcherNameTitle, "cell 2 1 3 1,alignx center,growx 0");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(ResearchersList);
                ResearchersList.addListSelectionListener(e -> {
                    OtherResearcherPropertyChange(e);
                    ChangeFollowButton(e);
                });
            }
            ResearchersView.add(scrollPane2, "cell 0 1 1 5,growy");

            //---- FollowButton ----
            FollowButton.setText("Follow");
            FollowButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            FollowButton.setBackground(new Color(0x00cccc));
            FollowButton.setForeground(Color.white);
            FollowButton.setIcon(UIManager.getIcon("FileView.computerIcon"));
            FollowButton.addActionListener(e -> ChangeFollowStatus(e));
            ResearchersView.add(FollowButton, "cell 1 1,alignx center,growx 0");

            //---- label6 ----
            label6.setText("Reading Lists");
            label6.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            ResearchersView.add(label6, "cell 1 2");

            //---- label7 ----
            label7.setText("Papers");
            label7.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            ResearchersView.add(label7, "cell 3 2");

            //======== scrollPane3 ========
            {
                scrollPane3.setViewportView(OtherResearcherReadingList);
                OtherResearcherReadingList.addListSelectionListener(e -> ReadingListsPropertyChange(e));
            }
            ResearchersView.add(scrollPane3, "cell 1 3 2 3,growy");

            //======== scrollPane4 ========
            {
                scrollPane4.setViewportView(OtherResearcherPapersList);
            }
            ResearchersView.add(scrollPane4, "cell 3 3 2 3,growy");
        }

        //======== ProfileView ========
        {
            ProfileView.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,ProfileView. getBorder( )) ); ProfileView. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );
            ProfileView.setLayout(new MigLayout(
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
                "[107]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- ReadingListViewTitle4 ----
            ReadingListViewTitle4.setText("My Profile");
            ReadingListViewTitle4.setFont(new Font("Segoe UI", Font.BOLD, 28));
            ProfileView.add(ReadingListViewTitle4, "cell 0 0");

            //---- ReturnProfile ----
            ReturnProfile.setText("Return Homepage");
            ReturnProfile.setIcon(UIManager.getIcon("FileChooser.homeFolderIcon"));
            ReturnProfile.addActionListener(e -> ReturnHomePage(e));
            ProfileView.add(ReturnProfile, "cell 5 0,align right top,grow 0 0");

            //---- ReadingListViewTitle2 ----
            ReadingListViewTitle2.setText("My Followers");
            ReadingListViewTitle2.setFont(new Font("Segoe UI", Font.BOLD, 28));
            ProfileView.add(ReadingListViewTitle2, "cell 0 1");

            //---- ReadingListViewTitle3 ----
            ReadingListViewTitle3.setText("Following Researchers");
            ReadingListViewTitle3.setFont(new Font("Segoe UI", Font.BOLD, 28));
            ProfileView.add(ReadingListViewTitle3, "cell 4 1 2 1");

            //======== scrollPane7 ========
            {
                scrollPane7.setViewportView(MyFollowersList);
            }
            ProfileView.add(scrollPane7, "cell 0 2 2 1");

            //======== scrollPane8 ========
            {
                scrollPane8.setViewportView(MyFollowingList);
            }
            ProfileView.add(scrollPane8, "cell 4 2 2 1");

            //---- ReadingListViewTitle ----
            ReadingListViewTitle.setText("My Reading Lists");
            ReadingListViewTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
            ProfileView.add(ReadingListViewTitle, "cell 0 3 4 1");

            //======== scrollPane5 ========
            {

                //---- MyReadingList ----
                MyReadingList.addListSelectionListener(e -> MyReadingListValueChanged(e));
                scrollPane5.setViewportView(MyReadingList);
            }
            ProfileView.add(scrollPane5, "cell 0 4 3 5,growy");

            //---- label10 ----
            label10.setText("Papers");
            label10.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            ProfileView.add(label10, "cell 4 4 2 1");

            //======== scrollPane6 ========
            {
                scrollPane6.setViewportView(ReadingListPapers);
                ReadingListPapers.addListSelectionListener(e -> SelectedPaperValueChanged(e));
            }
            ProfileView.add(scrollPane6, "cell 4 5 2 4,growy");

            //---- label9 ----
            label9.setText("Reading List Name");
            ProfileView.add(label9, "cell 0 9");

            //---- CreateInputField ----
            CreateInputField.addCaretListener(e -> CreateInputFieldCaretUpdate(e));
            ProfileView.add(CreateInputField, "cell 1 9 2 1");

            //---- CreateReadingListButton ----
            CreateReadingListButton.setText("Create Reading List");
            CreateReadingListButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            CreateReadingListButton.setBackground(new Color(0x00cccc));
            CreateReadingListButton.setForeground(Color.white);
            CreateReadingListButton.setIcon(UIManager.getIcon("FileChooser.newFolderIcon"));
            CreateReadingListButton.setEnabled(false);
            CreateReadingListButton.addActionListener(e -> {
            CreateReadingList(e);
        });
            ProfileView.add(CreateReadingListButton, "cell 0 10 3 1,alignx center,growx 0");

            //---- RemoveSelectedPaperButton ----
            RemoveSelectedPaperButton.setText("Remove Selected Paper");
            RemoveSelectedPaperButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            RemoveSelectedPaperButton.setBackground(new Color(0x00cccc));
            RemoveSelectedPaperButton.setForeground(Color.white);
            RemoveSelectedPaperButton.setIcon(UIManager.getIcon("InternalFrame.closeIcon"));
            RemoveSelectedPaperButton.setEnabled(false);
            RemoveSelectedPaperButton.addActionListener(e -> RemoveSelectedPaper(e));
            ProfileView.add(RemoveSelectedPaperButton, "cell 4 10 2 1,alignx center,growx 0");
        }
        //======== PapersView ========
        {
            PapersView.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
                    EmptyBorder( 0, 0, 0, 0) , "", javax. swing. border. TitledBorder. CENTER, javax. swing
                    . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
                    java. awt. Color. red) ,PapersView. getBorder( )) );
            PapersView. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () ))
                throw new RuntimeException( ); }} );
            PapersView.setMinimumSize(new Dimension(800,600));
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

            //---- ReturnPapers ----
            ReturnPapers.setText("Return Homepage");
            ReturnPapers.setIcon(UIManager.getIcon("FileChooser.homeFolderIcon"));
            ReturnPapers.addActionListener(e -> ReturnHomePage(e));
            PapersView.add(ReturnPapers, "cell 4 0,align right top,grow 0 0");

            //---- PaperTitle ----
            PaperTitle.setText("PAPER TITLE");
            PaperTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
            PaperTitle.setMaximumSize(new Dimension(600,300));
            PapersView.add(PaperTitle, "cell 3 1 2 1,alignx center,growx 0");

            //======== scrollPane1 ========
            {

                //---- PaperList ----
                PaperList.addListSelectionListener(e -> PaperListValueChanged(e));
                scrollPane1.setViewportView(PaperList);
            }
            PapersView.add(scrollPane1, "cell 0 1 2 5,growy,width 300:300");

            //---- PaperInfo ----
            PaperInfo.setText("PAPER HAKKINDA BILGILER");
            PaperInfo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            PaperInfo.setMaximumSize(new Dimension(800,100));
            PapersView.add(PaperInfo, "cell 3 2 2 2,aligny top,growy 0");
            PapersView.add(ReadingListDropdown, "cell 3 4 2 1");

            //---- DownloadButton ----
            DownloadButton.setText("Download");
            DownloadButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            DownloadButton.setBackground(new Color(0x00cccc));
            DownloadButton.setForeground(Color.white);
            DownloadButton.addActionListener(e -> {
                Download(e);
            });
            PapersView.add(DownloadButton, "cell 3 5,alignx center,growx 0");

            NumberOfDownloads.setFont(new Font("Segoe UI", Font.PLAIN, 16));

            PapersView.add(NumberOfDownloads, "cell 3 5,alignx center,growx 0");

            //---- AddButton ----
            AddButton.setText("Add My Reading List");
            AddButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
            AddButton.setBackground(new Color(0x00cccc));
            AddButton.setForeground(Color.white);
            AddButton.addActionListener(e -> {
                AddPaperToReadingList(e);
            });
            PapersView.add(AddButton, "cell 3 5,alignx center,growx 0");


        }
        LoginView = new LoginView(MainFrame, researcherController, HomePageView);


    }


}
