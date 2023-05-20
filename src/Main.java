import controller.ResearcherController;
import fileManagement.BibFileManagement;
import fileManagement.CsvFileManagement;


import fileManagement.XmlFileManagement;
import model.Paper;
import model.Researcher;
import view.LoginView;

import javax.swing.*;
import java.util.List;


public class Main {
    public static int AVAILABLE_ID = 0;

    public static void main(String[] args) {
//        BibFileManagement bibFileManagement = new BibFileManagement();
//        List<Paper> papers = bibFileManagement.getPapers();
//        CsvFileManagement csvFileManagement = new CsvFileManagement(papers);
//        papers.get(0).increaseOneNumOfDownloads();
//        csvFileManagement.writePapers();
//        XmlFileManagement xmlFileManagement = new XmlFileManagement();
//        List<Researcher> researchers = xmlFileManagement.getResearchers();
//        xmlFileManagement.setFollowResearcher(researchers.get(1), researchers.get(4));
//        JsonFileIO jsonFileIO = new JsonFileIO();
//        jsonFileIO.readFile();
//        ReadingList readingList = new ReadingList(AVAILABLE_ID, researchers.get(4).getName(), "En Son Eklenen", papers.subList(3, 7));
//        jsonFileIO.writeFileIO(List.of(readingList));
//        System.out.println(jsonFileIO.readFile().get(2).getReadingListName());
//        xmlFileManagement.setFollowResearcher(researchers.get(1), researchers.get(4));
//        xmlFileManagement.setUnfollowResearcher(researchers.get(1), researchers.get(4));
//        xmlFileManagement.setFollowResearcher(researchers.get(1), researchers.get(4));
//        xmlFileManagement.setFollowResearcher(researchers.get(2), researchers.get(3));
//        xmlFileManagement.setFollowResearcher(researchers.get(0), researchers.get(1));
//        xmlFileManagement.setUnfollowResearcher(researchers.get(0), researchers.get(4));
        MainApp mainApp = new MainApp();
//        ResearcherController researcherController = new ResearcherController();
//        JFrame frame = new JFrame("OpenResearcher");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        LoginView view = new LoginView(frame, researcherController);
//        researcherController.attach(view);
//        frame.getContentPane().add(view);
//        frame.pack();
//        frame.setVisible(true);
    }
}