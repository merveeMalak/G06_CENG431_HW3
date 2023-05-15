package fileIO;

import java.util.List;
import java.util.Map;

public class JsonFileIO implements IFileIO<List<String>>{
    @Override
    public List<String[]> readFile() {
        return null;
    }

    @Override
    public void writeFile(Map<String, String> line) {}
}
