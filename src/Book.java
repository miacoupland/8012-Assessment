public class Book implements Comparable<Book> {
    private final String title;
    private String authorFirstName;
    private String authorSecondName;
    private Boolean isWithdrawn;
    private User borrower;

    Book(String title, String authorName) {
        this.title = title;
        getName(authorName);
        this.isWithdrawn = false;
    }

    private void getName(String name) {
        String[] names = name.split(" ");
        this.authorSecondName = names[names.length - 1];
        for (int i = 0; i < (names.length -1); i++) {
            if (i == 0) {
                this.authorFirstName = names[i];
            } else {
                this.authorFirstName += " " + names[i];
            }
        }
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorSecondName() {
        return authorSecondName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return authorFirstName + " " + authorSecondName;
    }

    public User getBorrower() {return borrower;}

    public Boolean getIsWithdrawn() {
        if (isWithdrawn) {
            return true;
        } else {
            return false;
        }
    }

    public void withdraw(User u) {
        this.isWithdrawn = true;
        this.borrower = u;
    }

    public void returnBook() {
        this.isWithdrawn = false;
    }

    @Override
    public int compareTo(Book o) {
        if (this.getAuthorSecondName().compareTo(o.getAuthorSecondName()) > 0) {
            return 1;
        } else if (this.getAuthorSecondName().equals(o.getAuthorSecondName())){
            if (this.getAuthorFirstName().compareTo(o.getAuthorFirstName()) > 0) {
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return this.title + ", " + this.authorFirstName + " " + this.authorSecondName;
    }

    public static void main(String[] args) {
        User u = new User("Sample User", "Sample Surname");
        Book b1 = new Book("Sample Title", "Sample Author");
        System.out.println(b1.getTitle() + " by " + b1.getAuthor());
        System.out.println(b1.getAuthorFirstName() + " " + b1.getAuthorSecondName());
        System.out.println(b1.getIsWithdrawn());
        System.out.println(b1.toString());
        Book b2 = new Book("!Fnsjffe%*", "A.G Sur-Name");
        System.out.println(b2.getAuthorFirstName() + " - " + b2.getAuthorSecondName() + " " + b2.getAuthor());
        System.out.println(b1.compareTo(b2));
        System.out.println(b2.getBorrower() + " " + b1.getBorrower());
        u.addBook(b1);
        u.addBook(b1);
        u.addBook(b1);
        System.out.println(u.getBookCount());
        //book has been added thrice, shouldn't matter for this coursework but
        //problematic in a real life scenario
    }
}

