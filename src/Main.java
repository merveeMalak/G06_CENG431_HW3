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
        List<Researcher> researchers = new ArrayList<>();
        Researcher researcher2 = new Researcher("Dilek Öztürk", "dilek123",new ArrayList<>(), new ArrayList<>());
        Researcher researcher3 = new Researcher("Serhat Caner", "serhat123",new ArrayList<>(), new ArrayList<>());
        xmlFileManagement.setFollowResearcher(researcher2, researcher3);
        IFileIO xmlFileIO = new XmlFileIO();
        xmlFileIO.readFile();
    }
}