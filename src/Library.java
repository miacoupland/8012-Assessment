import java.io.IOException;

public class Library {
    private SortedArrayList<Book> books;
    private SortedArrayList<User> users;

    //this class just exists to hold everything and de-clutter io slightly

    public Library() throws IOException {
        this.books = new SortedArrayList<>();
        this.users = new SortedArrayList<>();
    }

    public SortedArrayList<Book> getBooks() {
        return this.books;
    }

    public SortedArrayList<User> getUsers() {
        return this.users;
    }

    //determine whether book is available to be withdrawn or not
    public Boolean availableBook(String book, String author, String user) {
        try {
            for (User u : getUsers()) { //check if user exists
                if (u.getFullName().equals(user)) {
                    if (u.getBookCount() >= 3) {//checks for too many books
                        throw new TooManyBooksException();
                    } else {
                        for (Book b : getBooks()) { //check if book is in the library
                            if (b.getAuthor().equals(author)) { //check author matches
                                if (b.getTitle().equals(book)) { //check if title matches
                                    if (b.getIsWithdrawn()) { //check if withdrawn already
                                        bookIsTaken(b); //starts write to file if already withdrawn
                                        return false;
                                    } else {
                                        u.addBook(b);//all checks done, add book
                                        b.withdraw(u);
                                        return true;
                                    }
                                }
                            }
                        }//thrown when book not in library
                        throw new BookNotFoundException();
                    }
                }
            }//thrown when user does not exist
            throw new UserNotFoundException();
        } catch (BookNotFoundException b) {
            System.out.println("\nBook not found. Please check your spelling");
            return false;
        } catch (UserNotFoundException u) {
            System.out.println("\nUser not found. Please check your spelling!");
            return false;
        } catch (TooManyBooksException t) {
            System.out.println("\nThis user has too many books. Please ask them to return one in order to withdraw another.");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //remove book from user possession and back into the library
    public Boolean returnBook(String userName, String bookTitle, String author) {
        try {
            for (User user : getUsers()) { //checks each user in the list
                if (user.getFullName().equals(userName)) { //checks if the user is the correct one
                    for (Book b : user.getBooksHeld()) { //if correct user, check for the book existing in their held books
                        if (b.getTitle().equals(bookTitle)) { //if it exists
                            for (Book b2 : getBooks()) { //look for the book in the full book list
                                if (b2.getTitle().equals(bookTitle) && b2.getAuthor().equals(author)) { //if it exists in both places, return the book
                                    b.returnBook();
                                    user.removeBook(b2.getTitle(), b2.getAuthor()); //remove book from user's possession
                                    return true;
                                }
                            }
                        }
                    }
                    throw new BookNotFoundException();
                }
            }
            throw new UserNotFoundException();
        } catch (UserNotFoundException u) {
            System.out.println("\nUser not found. Please check your spelling!");
        } catch (BookNotFoundException b) {
            System.out.println("\nBook not found. Please check your spelling!");
        }
        return false;
    }

    //the book has been withdrawn, so we make a note to the user to return the book asap
    public void bookIsTaken(Book b) throws IOException {
        for (User u : getUsers()) {//check which user has the book
            if (u.getBooksHeld().contains(b)) {//write to file asking for user to return book
                IO.writeToFile("Dear " + u.getFullName() + ", \nPlease return" +
                        " your copy of the book " + b.getTitle() + " by " +
                        b.getAuthor() + " as soon as possible, because another " +
                        "user has requested this title." +
                        "\nMany thanks,\nThe Library\n\n");
            }
        }
    }

    //exception methods
    public class BookNotFoundException extends Throwable {

    }

    public class UserNotFoundException extends Throwable {

    }

    public class TooManyBooksException extends Throwable {

    }

}
