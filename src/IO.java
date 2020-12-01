import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IO extends Exception {
    private Scanner scanner;
    private Reader reader;
    private Writer libraryWriter;
    private Writer outputWriter;

    IO() throws IOException {
        scanner = new Scanner(System.in);
        reader = new Reader();
        libraryWriter = new Writer("Book Requests.txt");
        outputWriter = new Writer("Output.txt");
    }

    public void run() throws IOException {
        reader.read();
        while (true) {
            System.out.println("f - to finish running the program.\n" +
                    "b - to display on the screen the information about all the books" +
                    " in the library.\n" +
                    "u - to display on the screen the information about all the users.\n" +
                    "i - to update the stored data when a book is issued to a user.\n" +
                    "r - to update the stored data when a user returns a book to the library.");

            String input = scanner.nextLine();
            switch (input) {
                case "f":
                    System.out.println("\nProgram is ending...");
                    System.exit(0); //exit program with no error
                case "b":
                    System.out.println("\nYou have selected to display all books in the library.");
                    for (Book book: reader.getBooks()) {
                        System.out.println(book.toString());
                    }
                    break;
                case "u":
                    System.out.println("\nYou have selected to display all user information.");
                    for (User user : reader.getUsers()) {
                        System.out.println(user.toString());
                    }
                    break;
                case "i":
                    System.out.println("\nYou have selected to update stored data when a book is issued to a user.");
                    System.out.println("\nWhat is the title of the book being issued?");
                    String title = scanner.nextLine();
                    System.out.println(("\nWhat is the name of the author?"));
                    String author = scanner.nextLine();
                    System.out.println("\nWhat is the user's name?");
                    String name = scanner.nextLine();

                    if (availableBook(title, author, name)) {
                        System.out.println("\nSuccessfully withdrawn!");
                    }
                    //go to that book, see if it is withdrawn. If false, go to the user
                    //see if they have 3 books, and if not, then say they have the book
                    break;
                case "r":
                    System.out.println("\nYou have selected to update stored data when a user returns a book to the library.");
                    System.out.println("\nEnter the user's name.");
                    String userName = scanner.nextLine();
                    System.out.println("\nEnter the title of the returned book.");
                    String bookTitle = scanner.nextLine();
                    returnBook(userName, bookTitle);
                    break;
                default:
                    System.out.println("\nInvalid choice, please try again.");
            }
        }
    }

    public Boolean availableBook(String book, String author, String user) {
        try {
            for (Book b: reader.getBooks()) {
                if (b.getAuthor().equals(author)) {
                    if (b.getTitle().equals(book)) {
                        if (b.getIsWithdrawn()) {
                            bookIsTaken(b);
                            System.out.println("\nBook has already been withdrawn! A message has been sent to the user.");
                            return false;
                        } else {
                            for (User u: reader.getUsers()) {
                                if (u.getFullName().equals(user)) {
                                    if (u.getBookCount() >= 3) {
                                        return false;
                                    } else {
                                        u.addBook(b);
                                        b.withdraw();
                                        return true;
                                    }
                                }
                            }
                            throw new UserNotFoundException();
                        }
                    }
                }
            }
            throw new BookNotFoundException();
        } catch (BookNotFoundException b) {
            System.out.println("\nBook not found. Please check your spelling");
        } catch (UserNotFoundException u) {
            System.out.println("\nUser not found. Please check your spelling!");
        }
        return false;
    }

    public Boolean returnBook(String userName, String bookTitle) {
        for (User user: reader.getUsers()) { //checks each user in the list
            if (user.getFullName().equals(userName)) { //checks if the user is the correct one
                for (Book b : user.getBooksHeld()) { //if correct user, check for the book existing in their held books
                    if (b.getTitle().equals(bookTitle)) { //if it exists
                        for (Book b2: reader.getBooks()) { //look for the book in the full book list
                            if (b2.getTitle().equals(bookTitle)) { //if it exists in both places, return the book
                                b.returnBook();
                                user.removeBook(b2.getTitle()); //remove book from user's possession
                                System.out.println("\nBook returned!"); //confirm to the user the book has been returned
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void bookIsTaken(Book b) {
        for (User u : reader.getUsers()) {
            if (u.getBooksHeld().contains(b)) {
                libraryWriter.writeToFile("Dear " + u.getFullName() + ", \nPlease return" +
                        " your copy of the book " + b.getTitle() + " by " +
                        b.getAuthor() + " as soon as possible, because another " +
                        "user has requested this title.");
            }
        }
    }

    public class BookNotFoundException extends Throwable {

    }

    public class AuthorNotFoundException extends Throwable {

    }

    public class UserNotFoundException extends Throwable {

    }

}
