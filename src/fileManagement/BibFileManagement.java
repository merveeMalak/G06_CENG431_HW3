package fileManagement;

import fileIO.BibFileIO;
import fileIO.IReadFileIO;
import model.Article;
import model.ConferencePaper;
import model.Paper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibFileManagement {
    private List<Paper> papers;

    public BibFileManagement() {
        this.papers = new ArrayList<>();
        createPaper();
    }

    private void createPaper(){
        IReadFileIO bibFileIO = new BibFileIO();
        List<String[]> stringList = bibFileIO.readFile();
        for (String[] paperString : stringList){
            if (paperString.length == 7){
                Paper articlePaper = new Article(new ArrayList<>(Arrays.stream(paperString[0].split(",")).toList()), paperString[1], paperString[2] == null ? 0 : Integer.parseInt(paperString[2]), paperString[3], paperString[4] == null ? 0 : Integer.parseInt(paperString[4]), paperString[5], paperString[6]);
                papers.add(articlePaper);
            } else {
                Paper conferencePaper = new ConferencePaper(new ArrayList<>(Arrays.stream(paperString[0].split(",")).toList()), paperString[1], paperString[2] == null ? 0 : Integer.parseInt(paperString[2]), paperString[3], paperString[4]);
                papers.add(conferencePaper);
            }
        }
    }

    public List<Paper> getPapers() {
        return new ArrayList<>(papers);
    }
}
