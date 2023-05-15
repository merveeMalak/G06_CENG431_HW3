package fileManagement;

import fileIO.CsvFileIO;
import fileIO.IFileIO;
import model.Article;
import model.ConferencePaper;
import model.Paper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CsvFileManagement {
    private List<Paper> papers;

    public CsvFileManagement(List<Paper> createdPapers) {
        this.papers = createdPapers;
    }

    public void writePapers() {
        IFileIO<List<String[]>> csvFileIO = new CsvFileIO();
        List<String[]> paperList = new ArrayList<>();
        for (Paper paper : this.papers) {
            if (paper instanceof ConferencePaper conferencePaper) {
                String[] line = new String[]{"conference paper", conferencePaper.getAuthorsString(), conferencePaper.getTitle(), Integer.toString(conferencePaper.getYear()), conferencePaper.getDoi(), conferencePaper.getBookTitle(), conferencePaper.getNumOfDownloads()};
                paperList.add(line);
            }
            if (paper instanceof Article article){
                String[] line = new String[]{"article", article.getAuthorsString(), article.getTitle(), Integer.toString(article.getYear()), article.getDoi(), article.getVolume(), article.getNumber(), article.getJournal(),article.getNumOfDownloads()};
                paperList.add(line);
            }
        }
        csvFileIO.writeFileIO(paperList);
    }
}
