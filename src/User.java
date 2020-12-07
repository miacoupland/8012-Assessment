public class User implements Comparable<User> {
    private final String firstName;
    private final String surname;
    private int bookCount;
    private SortedArrayList<Book> booksHeld;

    User(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
        this.bookCount = 0;
        this.booksHeld = new SortedArrayList<>();
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

    public SortedArrayList<Book> getBooksHeld() {
        return booksHeld;
    }

    public Boolean removeBook(String bookTitle, String author) {
        if (bookCount > 0) {
            for (Book book: booksHeld) {
                if (book.getTitle().equals(bookTitle) && book.getAuthor().equals(author)) {
                    this.booksHeld.remove(book);
                    bookCount--;
                    return true;
                }
            }
        }
        return false;
    }

    public void addBook(Book book) {
        if (this.bookCount < 3) {
            this.booksHeld.sortAdd(book);
            bookCount++;
        }
    }

    @Override
    public int compareTo(User o) {
        if (this.getSurname().compareTo(o.getSurname()) > 0) {
            return 1;
        } else if (this.getSurname().equals(o.getSurname())){
            if (this.getFirstName().compareTo(o.getFirstName()) > 0) {
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
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

    public static void main(String[] args) {
        //main here for testing purposes
        User u1 = new User("Sergio", "Aguero");
        Book b1 = new Book("Born to Rise", "Sergio Aguero");
        System.out.println("This user has " + u1.getBookCount() + " book[s]");
        u1.addBook(b1);
        System.out.println("This user has " + u1.getBookCount() + " book[s]: " + u1.getBooksHeld());
        System.out.println(u1.getFirstName() + " " + u1.getSurname() + " has " + u1.getBookCount() + " book[s]");
        System.out.println(u1.toString());
        u1.removeBook("Born to Rise", "Sergio Aguero");
        System.out.println(u1.toString());
        u1.removeBook("invalid title", "Sergio Aguero");//shouldn't work
    }
}
//ADD ERROR IN WHERE A USER HAS MORE THAN THREE BOOKS AND THEY MUST RETURN IT
//MAYBE SAY WHO NEEDS TO RETURN