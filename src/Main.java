import fileIO.BibFileIO;
import fileIO.IReadFileIO;

public class Main {
    public static void main(String[] args) {
        IReadFileIO bibFileIO = new BibFileIO();
        bibFileIO.readFile();
    }
}