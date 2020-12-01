import java.util.ArrayList;

public class User {

    private String firstName;
    private String surname;
    private int bookCount;
    private ArrayList<Book> booksHeld;

    User(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
        this.bookCount = 0;
        this.booksHeld = new ArrayList<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getFullName() {
        return this.firstName + " " + this.surname;
    }

    public int getBookCount() {
        return this.bookCount;
    }

    public ArrayList<Book> getBooksHeld() {
        return this.booksHeld;
    }

    @Override
    public String toString() {
        String withdrawn = "";
        for (Book book: getBooksHeld()) {
            if (getBooksHeld().size() > 0) {
                withdrawn += ":";
            }
            withdrawn += "\n\t" + book;
        }
        return this.firstName + " " + this.surname + " currently has " + bookCount + " book[s]"
                + withdrawn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean removeBook(String bookTitle) {
        if (bookCount > 0) {
            for (Book book: booksHeld) {
                if (book.getTitle().equals(bookTitle)) {
                    this.booksHeld.remove(book);
                    bookCount--;
                    return true;
                }
            }
        }
        return false;
    }

    public void addBook(Book book) {
        this.booksHeld.add(book);
        bookCount++;
    }
}
//ADD ERROR IN WHERE A USER HAS MORE THAN THREE BOOKS AND THEY MUST RETURN IT
//MAYBE SAY WHO NEEDS TO RETURN