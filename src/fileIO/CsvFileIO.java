package fileIO;


import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CsvFileIO implements IFileIO<List<String[]>> {
    @Override
    public void writeFileIO(List<String[]> lines) {
        FileWriter writer = null;
        File file = new File("papers.csv");
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
        return null;
    }


}
