import fileIO.*;
import fileManagement.BibFileManagement;
import fileManagement.CsvFileManagement;


import fileManagement.XmlFileManagement;
import model.Paper;
import model.ReadingList;
import model.Researcher;

import java.util.*;

public class Main {
    public static int AVAILABLE_ID = 0;

    public static void main(String[] args) {
        BibFileManagement bibFileManagement = new BibFileManagement();
        List<Paper> papers = bibFileManagement.getPapers();
        CsvFileManagement csvFileManagement = new CsvFileManagement(papers);
        csvFileManagement.writePapers();
        XmlFileManagement xmlFileManagement = new XmlFileManagement();
        List<Researcher> researchers = xmlFileManagement.getResearchers();
        xmlFileManagement.setFollowResearcher(researchers.get(1), researchers.get(4));
        JsonFileIO jsonFileIO = new JsonFileIO();
//        jsonFileIO.readFile();
        ReadingList readingList = new ReadingList(AVAILABLE_ID, researchers.get(4).getName(), "En Son Eklenen", papers.subList(3, 7));
        jsonFileIO.writeFileIO(List.of(readingList));
//        System.out.println(jsonFileIO.readFile().get(2).getReadingListName());
    }
}