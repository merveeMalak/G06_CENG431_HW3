import fileIO.BibFileIO;
import fileIO.CsvFileIO;
import fileIO.IFileIO;
import fileIO.IReadFileIO;
import fileManagement.BibFileManagement;
import fileManagement.CsvFileManagement;
import model.Paper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BibFileManagement bibFileManagement = new BibFileManagement();
        List<Paper> papers = bibFileManagement.getPapers();
        CsvFileManagement csvFileManagement = new CsvFileManagement(papers);
        csvFileManagement.writePapers();
        for (Paper paper : papers) {

//            System.out.println(paper.getTitle());
        }
    }
}