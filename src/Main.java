import fileIO.BibFileIO;
import fileIO.CsvFileIO;
import fileIO.IFileIO;
import fileIO.IReadFileIO;
import fileIO.XmlFileIO;
import fileManagement.BibFileManagement;
import fileManagement.CsvFileManagement;


import fileManagement.XmlFileManagement;
import model.Paper;
import model.Researcher;
import view.LoginView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BibFileManagement bibFileManagement = new BibFileManagement();
        List<Paper> papers = bibFileManagement.getPapers();
        CsvFileManagement csvFileManagement = new CsvFileManagement(papers);
        csvFileManagement.writePapers();
        XmlFileManagement xmlFileManagement = new XmlFileManagement();
        List<Researcher> researchers = xmlFileManagement.getResearchers();

        xmlFileManagement.setFollowResearcher(researchers.get(1),researchers.get(4));
        xmlFileManagement.setUnfollowResearcher(researchers.get(1),researchers.get(4));
        xmlFileManagement.setFollowResearcher(researchers.get(1),researchers.get(4));
        xmlFileManagement.setFollowResearcher(researchers.get(2),researchers.get(3));
        xmlFileManagement.setFollowResearcher(researchers.get(0),researchers.get(1));
        xmlFileManagement.setUnfollowResearcher(researchers.get(0),researchers.get(4));
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add (new LoginView());
        frame.pack();
        frame.setVisible (true);

    }
}