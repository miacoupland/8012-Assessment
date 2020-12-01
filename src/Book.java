public class Book {

    private String title;
    private String authorFirstName;
    private String authorSecondName;
    private Boolean isWithdrawn;

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

    @Override
    public String toString() {
        return this.title + ", " + this.authorFirstName + " " + this.authorSecondName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorFirstName(String firstName) {
        this.authorFirstName = firstName;
    }

    public void setAuthorSecondName(String secondName) {
        this.authorSecondName = secondName;
    }

    public Boolean getIsWithdrawn() {
        if (isWithdrawn) {
            return true;
        } else {
            return false;
        }
    }

    public void withdraw() {
        this.isWithdrawn = true;
    }

    public void returnBook() {
        this.isWithdrawn = false;
    }
}

