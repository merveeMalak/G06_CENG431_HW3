package fileManagement;

import fileIO.CsvFileIO;
import fileIO.IFileIO;
import model.Article;
import model.ConferencePaper;
import model.Paper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFileManagement {
    private List<Paper> papers;

    public CsvFileManagement(List<Paper> createdPapers) {
        this.papers = createdPapers;
    }

    public void writePapers() {
        IFileIO<List<String[]>, List<String[]>> csvFileIO = new CsvFileIO();
        List<String[]> paperList = new ArrayList<>();
        for (Paper paper : this.papers) {
            if (paper instanceof ConferencePaper conferencePaper) {
                String[] line = new String[]{"conference paper", conferencePaper.getAuthorsString(), conferencePaper.getTitle(), Integer.toString(conferencePaper.getYear()), conferencePaper.getDoi(), conferencePaper.getBookTitle(), conferencePaper.getNumOfDownloads()};
                paperList.add(line);
            }
            if (paper instanceof Article article) {
                String[] line = new String[]{"article", article.getAuthorsString(), article.getTitle(), Integer.toString(article.getYear()), article.getDoi(), article.getVolume(), article.getNumber(), article.getJournal(), article.getNumOfDownloads()};
                paperList.add(line);
            }
        }
        csvFileIO.writeFileIO(paperList);
    }

    public void readPapers() {
        IFileIO<List<String[]>, List<String[]>> csvFileIO = new CsvFileIO();
        List<String[]> readList = csvFileIO.readFile();
        for (String[] line : readList) {
            switch (line[0]) {
                case "article": {
                    Article article = new Article(Arrays.asList(line[1].split(",")), line[2], Integer.parseInt(line[3]), line[4], Integer.parseInt(line[5]), line[6], line[7], Integer.parseInt(line[8]));
                    this.papers.add(article);
                }
                case "conference paper": {
                    ConferencePaper conferencePaper = new ConferencePaper(Arrays.asList(line[1].split(",")), line[2], Integer.parseInt(line[3]), line[4], line[5], Integer.parseInt(line[6]));
                    this.papers.add(conferencePaper);
                }
            }

        }
    }
}
