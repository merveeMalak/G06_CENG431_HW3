package fileIO;
import model.Article;
import model.ConferencePaper;
import model.Paper;
import org.jbibtex.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BibFileIO implements IReadFileIO{
    private final String[] filePaths = new String[]{"A_2004_Test-Driven Modeling for Model-Driven Development.bib",
            "A_2005_Introducing Test Automation and Test-Driven Development.bib","A_2005_On the Effectiveness of the Test-First Approach to Programming.bib",
            "A_2005_Test-Driven Development Concepts, Taxonomy, and Future Direction.bib","A_2007_Learning Test-Driven Development by Counting Lines.bib",
    "A_2009_A Formally Verified Compiler Back-end.bib","A_2011_Impact of test-driven development on productivity, code and tests- A controlled experiment.bib",
    "A_2011_The effectiveness of test-driven development- an industrial case study.bib","A_2013_A successful application of a Test-Driven Development strategy in the industrial environment.bib",
    "IP_2003_An initial investigation of test driven development in industry.bib","IP_2007_Generating User Acceptance Test Plans from Test Cases.bib",
    "IP_2012_Test driven development the state of the practice.bib","IP_2014_An experimental evaluation of test driven development vs. test-last development with industry professionals.bib",
    "IP_2018_A systematic literature review to support the selection of user acceptance testing techniques.bib","IP_2018_On the effectiveness of unit tests in test-driven development.bib"};

    public BibFileIO(){
    }

    public List<String[]> readFile() {
        List<String[]> stringList = new ArrayList<>();
        for (String bibFilePath : filePaths) {
            FileReader reader = null;
            try {
                reader = new FileReader("Homework3/" +bibFilePath);
            } catch (IOException e) {
                System.out.println("IOException");
            }
            BibTeXParser parser = null;
            try {
                parser = new BibTeXParser();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            BibTeXDatabase database = null;
            try {

                database = parser.parse(reader);
                Collection<BibTeXEntry> entries = database.getEntries().values();
                for (org.jbibtex.BibTeXEntry entry : entries) {
                    stringList.add(entryToPaper(entry));
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return stringList;
    }
    private String[] entryToPaper(BibTeXEntry entry){
        if (entry.getType().equals(BibTeXEntry.TYPE_ARTICLE)){
            Key[] articleKeys = new Key[]{BibTeXEntry.KEY_AUTHOR ,BibTeXEntry.KEY_TITLE,BibTeXEntry.KEY_YEAR,
                    BibTeXEntry.KEY_DOI,BibTeXEntry.KEY_VOLUME,BibTeXEntry.KEY_NUMBER,BibTeXEntry.KEY_JOURNAL};
            String[] articleValues  = new String[7];
            for (int i = 0; i < articleKeys.length; i++){
                articleValues[i] = keyString(entry, articleKeys[i]);
            }

            return articleValues;
        }else if(entry.getType().equals(BibTeXEntry.TYPE_INPROCEEDINGS)){
            Key[] conferenceKeys = new Key[]{BibTeXEntry.KEY_AUTHOR ,BibTeXEntry.KEY_TITLE,BibTeXEntry.KEY_YEAR,
                    BibTeXEntry.KEY_DOI,BibTeXEntry.KEY_BOOKTITLE};
            String[] conferenceValues  = new String[5];
            for (int i = 0; i < conferenceKeys.length; i++){
                conferenceValues[i] = keyString(entry, conferenceKeys[i]);
            }
            return conferenceValues;
        }
        return null;
    }

    private String keyString(BibTeXEntry entry, Key key){
        org.jbibtex.Value value = entry.getField(key);
        if (value == null) {
            return null;
        }
        String string = value.toUserString();
        if(string.indexOf('\\') > -1 || string.indexOf('{') > -1){
            // LaTeX string that needs to be translated to plain text string
            LaTeXParser latexParser = null;
            try {
                latexParser = new LaTeXParser();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            List<LaTeXObject> latexObjects = null;
            try {
                latexObjects = latexParser.parse(string);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            org.jbibtex.LaTeXPrinter latexPrinter = new org.jbibtex.LaTeXPrinter();
            String plainTextString = latexPrinter.print(latexObjects);
            return plainTextString;
        } else {
            return string;
        }
    }
}
