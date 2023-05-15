package fileIO;

import org.jbibtex.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BibFileIO implements IReadFileIO {
    private final String PATH = "Homework3/";

    public BibFileIO() {
    }

    public List<String[]> readFile() {
        File folder = new File(PATH);
        File[] listOfFiles = folder.listFiles();
        List<String[]> stringList = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".bib")) {
                FileReader reader = null;
                try {
                    reader = new FileReader(String.format("%s%s", PATH, file.getName()));
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
        }
        return stringList;
//        List<String[]> stringList = new ArrayList<>();
//        for (String bibFilePath : filePaths) {
//            FileReader reader = null;
//            try {
//                reader = new FileReader("Homework3/" + bibFilePath);
//            } catch (IOException e) {
//                System.out.println("IOException");
//            }
//            BibTeXParser parser = null;
//            try {
//                parser = new BibTeXParser();
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            BibTeXDatabase database = null;
//            try {
//
//                database = parser.parse(reader);
//                Collection<BibTeXEntry> entries = database.getEntries().values();
//                for (org.jbibtex.BibTeXEntry entry : entries) {
//                    stringList.add(entryToPaper(entry));
//                }
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return stringList;
    }

    private String[] entryToPaper(BibTeXEntry entry) {
        if (entry.getType().equals(BibTeXEntry.TYPE_ARTICLE)) {
            Key[] articleKeys = new Key[]{BibTeXEntry.KEY_AUTHOR, BibTeXEntry.KEY_TITLE, BibTeXEntry.KEY_YEAR, BibTeXEntry.KEY_DOI, BibTeXEntry.KEY_VOLUME, BibTeXEntry.KEY_NUMBER, BibTeXEntry.KEY_JOURNAL};
            String[] articleValues = new String[7];
            for (int i = 0; i < articleKeys.length; i++) {
                articleValues[i] = keyString(entry, articleKeys[i]);
            }

            return articleValues;
        } else if (entry.getType().equals(BibTeXEntry.TYPE_INPROCEEDINGS)) {
            Key[] conferenceKeys = new Key[]{BibTeXEntry.KEY_AUTHOR, BibTeXEntry.KEY_TITLE, BibTeXEntry.KEY_YEAR, BibTeXEntry.KEY_DOI, BibTeXEntry.KEY_BOOKTITLE};
            String[] conferenceValues = new String[5];
            for (int i = 0; i < conferenceKeys.length; i++) {
                conferenceValues[i] = keyString(entry, conferenceKeys[i]);
            }
            return conferenceValues;
        }
        return null;
    }

    private String keyString(BibTeXEntry entry, Key key) {
        org.jbibtex.Value value = entry.getField(key);
        if (value == null) {
            return null;
        }
        String string = value.toUserString();
        if (string.indexOf('\\') > -1 || string.indexOf('{') > -1) {
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
