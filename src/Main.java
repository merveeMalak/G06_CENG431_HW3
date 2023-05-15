import fileIO.BibFileIO;
import fileIO.CsvFileIO;
import fileIO.IFileIO;
import fileIO.IReadFileIO;
import fileIO.XmlFileIO;
import fileManagement.BibFileManagement;
import fileManagement.CsvFileManagement;
import model.Paper;

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
        IFileIO xmlFileIO = new XmlFileIO();
        Map<String,String> lines = new HashMap<>();
        lines.put("follower_researcher_names", "");
        lines.put("following_researcher_names", "ali;fatma");
        lines.put("password", "123");
        lines.put("name", "merve");
        xmlFileIO.writeFile(lines);
        Map<String,String> lines1 = new HashMap<>();
        lines1.put("follower_researcher_names", "veli;ayşe");
        lines1.put("following_researcher_names", "ali;fatma");
        lines1.put("password", "1234");
        lines1.put("name", "merve1");
        xmlFileIO.writeFile(lines1);
    }
}