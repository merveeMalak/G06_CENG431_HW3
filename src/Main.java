import fileIO.BibFileIO;
import fileIO.IReadFileIO;
import fileManagement.BibFileManagement;
import model.Paper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IReadFileIO bibFileIO = new BibFileIO();
        BibFileManagement bibFileManagement = new BibFileManagement();
        bibFileManagement.createPaper(bibFileIO.readFile());
        List<Paper> papers = bibFileManagement.getPapers();
        for(Paper paper : papers){
            System.out.println(paper.getTitle());
        }
    }
}