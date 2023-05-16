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
        List<Researcher> researchers = xmlFileManagement.getResearchers();
        xmlFileManagement.setFollowResearcher(researchers.get(1),researchers.get(4));

    }
}