import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private FileWriter newFile;

    public Writer(String fileName) throws IOException {
        newFile = new FileWriter(fileName);
    }

    public void writeToFile(String text) {
        try {
            FileWriter newFile = new FileWriter("Book Requests.txt");
            newFile.append(text);
            newFile.close();
            System.out.println("\nUser reminder added to file.");
        } catch (IOException e) {
            System.out.println("\nAn error occurred");
            e.printStackTrace();
        }

    }
}
