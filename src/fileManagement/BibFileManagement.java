package fileManagement;

import model.Article;
import model.ConferencePaper;
import model.Paper;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibFileManagement {
    private List<Paper> papers;
    public BibFileManagement() {
        this.papers = new ArrayList<>();
    }

    public void createPaper(List<String[]> stringList){
        for (String[] paperString : stringList){
            if (paperString.length == 7){
                Paper articlePaper = new Article(new ArrayList<>(Arrays.stream(paperString[0].split(",")).toList()), paperString[1], paperString[2] == null ? 0 : Integer.parseInt(paperString[2]), paperString[3], paperString[4] == null ? 0 : Integer.parseInt(paperString[4]), paperString[5], paperString[6]);
                papers.add(articlePaper);
            }else {
                Paper conferencePaper = new ConferencePaper(new ArrayList<>(Arrays.stream(paperString[0].split(",")).toList()), paperString[1],paperString[2] == null ? 0 :Integer.parseInt(paperString[2]),paperString[3], paperString[4]);
                papers.add(conferencePaper);
            }
        }

    }
    public List<Paper> getPapers(){
        return new ArrayList<>(papers);
    }
}
