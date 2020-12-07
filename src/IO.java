import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//all user input and output
public class IO extends Exception {
    private final Scanner scanner;
    private static FileWriter writer; //static so library can access writer method
    private Library library;


    IO() throws IOException {
        scanner = new Scanner(System.in);
        this.library = new Library();
    }

    public void read() throws FileNotFoundException {
        String inputFile = "src/example.txt";

        try {
            Scanner inFile = new Scanner(new FileReader(inputFile));
            while (inFile.hasNextLine()) {
                //while the file has another line, get the first line which is the amount of books
                int bookCount = Integer.valueOf(inFile.nextLine());
                //for every two lines and for the number specified above, look for title and author
                for (int i = 0; i < bookCount; i++) {
                    String title = inFile.nextLine().toLowerCase();
                    String authorName = inFile.nextLine().toLowerCase();
                    library.getBooks().sortAdd(new Book(title, authorName));
                }

                int userCount = Integer.valueOf(inFile.nextLine());
                //after first loop, look for user count, then find their full name
                for (int i = 0; i < userCount; i++) {
                    String[] fullName = inFile.nextLine().toLowerCase().split(" ");
                    library.getUsers().sortAdd(new User(fullName[0], fullName[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //menu functionality
    public void run() throws IOException {
        read();//import list of books and users before running menu
        while (true) {
            System.out.println("f - to finish running the program.\n" +
                    "b - to display on the screen the information about all the books" +
                    " in the library.\n" +
                    "u - to display on the screen the information about all the users.\n" +
                    "i - to update the stored data when a book is issued to a user.\n" +
                    "r - to update the stored data when a user returns a book to the library.");
            String input = scanner.nextLine();
            switch (input) {
                case "f": //finish
                    System.out.println("\nProgram is ending...");
                    //writer.close();
                    System.exit(0); //exit program with no error
                case "b": //see books. quite small functionality so did not seem worth adding another method?
                    System.out.println("\nYou have selected to display all books in the library.");
                    for (Book book: library.getBooks()) {//sort functionality inside reader.getBooks()
                        System.out.println(book.toString());
                    }
                    break;
                case "u": //see users. Same case as see books
                    System.out.println("\nYou have selected to display all user information.");
                    for (User user : library.getUsers()) {//sort functionality inside reader.getUsers()
                        System.out.println(user.toString());
                    }
                    break;
                case "i": //issue book to a user
                    issueBook();
                    break;
                case "r": //return
                    returnBook();
                    break;
                default:
                    System.out.println("\nInvalid choice, please try again.");
            }
        }
    }

    public void issueBook() {
        System.out.println("\nYou have selected to update stored data when a book is issued to a user.");
        System.out.println("\nWhat is the title of the book being issued?");
        String title = scanner.nextLine();
        System.out.println(("\nWhat is the name of the author?"));
        String author = scanner.nextLine();
        System.out.println("\nWhat is the user's name?");
        String name = scanner.nextLine();
        if (library.availableBook(title, author, name)) {
            System.out.println("\nSuccessfully withdrawn!");
        }
    }

    public void returnBook() {
        System.out.println("\nYou have selected to update stored data when a user returns a book to the library.");
        System.out.println("\nEnter the user's name.");
        String userName = scanner.nextLine();
        System.out.println("\nEnter the title of the returned book.");
        String bookTitle = scanner.nextLine();
        System.out.println("\nEnter the name of the author");
        String authorName = scanner.nextLine();
        if (library.returnBook(userName, bookTitle, authorName)) {
            System.out.println("\nBook returned!"); //confirm to the user the book has been returned
        }
    }

    //writing to the file, text from library. static so it's accessible
    public static void writeToFile(String text) throws IOException {
        try {
            writer = new FileWriter("Book Requests.txt", true);//write to this file for return book requests
            writer.write(text);
            writer.close();
            System.out.println("\nUser reminder added to file.");
        } catch (IOException e) {
            System.out.println("\nAn error occurred writing to the file");
            e.printStackTrace();
        }
    }
}
