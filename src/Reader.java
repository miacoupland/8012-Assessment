import org.w3c.dom.ls.LSOutput;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    private Scanner scanner;
    private SortedArrayList<Book> books;
    private SortedArrayList<User> users;

    public Reader() {
        this.scanner = new Scanner(System.in);
        this.books = new SortedArrayList<>();
        this.users = new SortedArrayList<>();
    }

    public void read() throws FileNotFoundException {
        String inputFile = "src/example.txt";

        try {
            Scanner inFile = new Scanner(new FileReader(inputFile));
            while (inFile.hasNextLine()) {
                int bookCount = Integer.valueOf(inFile.nextLine());

                for (int i = 0; i < bookCount; i++) {
                    String title = inFile.nextLine().toLowerCase();
                    String authorName = inFile.nextLine().toLowerCase();
                    this.books.add(new Book(title, authorName));
                }

                int userCount = Integer.valueOf(inFile.nextLine());

                for (int i = 0; i < userCount; i++) {
                    String[] fullName = inFile.nextLine().toLowerCase().split(" ");
                    this.users.add(new User(fullName[0], fullName[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }
}
