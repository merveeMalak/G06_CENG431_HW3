package fileIO;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import model.Paper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvFileIO implements IFileIO<List<String[]>, List<String[]>> {
    final private String PATH = "papers.csv";

    @Override
    public void writeFileIO(List<String[]> lines) {
        FileWriter writer = null;
        File file = new File(PATH);
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CSVWriter csvWriter = new CSVWriter(writer);
        csvWriter.writeAll(lines);
        try {
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<String[]> readFile() {
        CSVReader reader = null;
        List<String[]> readList = new ArrayList<>();
        try {
            //parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader(PATH));
            String[] nextLine;
            //reads one line at a time
            while ((nextLine = reader.readNext()) != null) {
                readList.add(nextLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readList;
    }
}
