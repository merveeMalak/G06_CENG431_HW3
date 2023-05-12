package fileIO;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXParser;
import org.jbibtex.ParseException;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;

public class BibFileIO implements IReadFileIO{
    @Override
    public List<String> readFile() {
        String bibFilePath = "Homework3/A_2007_Learning Test-Driven Development by Counting Lines.bib";
        FileReader reader = null;


        try {
            reader = new FileReader(bibFilePath);
        } catch (IOException e) {
            System.out.println("IOException");;
        }
        BibTeXParser parser = new BibTeXParser(){

            @Override
            public void checkStringResolution(org.jbibtex.Key key, org.jbibtex.BibTeXString string){
                System.out.println(key);
                if(string == null){
                    System.err.println("Unresolved string: \"" + key.getValue() + "\"");
                }
            }

            @Override
            public void checkCrossReferenceResolution(org.jbibtex.Key key, org.jbibtex.BibTeXEntry entry){
                System.out.println(key);
                if(entry == null){
                    System.err.println("Unresolved cross-reference: \"" + key.getValue() + "\"");
                }
            }
        };
        BibTeXDatabase database = null;
        try {

            database = parser.parse(reader);
        } catch (IOException e) {
            System.out.println("IOException");;
        } catch (ParseException e) {
            System.err.println("ParseException");;
        }
        System.out.println(database);
        return null;


    }

}
