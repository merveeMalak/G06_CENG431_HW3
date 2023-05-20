package view;

import controller.ResearcherController;

import java.awt.*;
import javax.swing.*;

public class DashboardView extends JPanel {
    private JLabel welcomeText;
    private JButton jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JButton jcomp5;
    private JButton jcomp6;
    private ResearcherController researcherController;
    private JFrame frame;

    public DashboardView(JFrame frame, ResearcherController researcherController) {
        //construct components
        this.researcherController = researcherController;
        this.frame = frame;
        welcomeText = new JLabel("Welcome");
        jcomp2 = new JButton("Show Papers");
        jcomp3 = new JLabel("Please select an option");
        jcomp4 = new JLabel(this.researcherController.getResearcherName());
        jcomp5 = new JButton("Show Other Researchers");
        jcomp6 = new JButton("My Reading Lists");

        //adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        //add components
        add(welcomeText);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);
        add(jcomp6);

        //set component bounds (only needed by Absolute Positioning)
        welcomeText.setBounds(405, 15, 55, 20);
        jcomp2.setBounds(65, 125, 185, 35);
        jcomp3.setBounds(405, 40, 100, 25);
        jcomp4.setBounds(465, 15, 100, 20);
        jcomp5.setBounds(380, 125, 180, 35);
        jcomp6.setBounds(695, 125, 155, 35);
    }
}
