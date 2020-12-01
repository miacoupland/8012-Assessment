import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    /*Book b = new Book("Hello", "author has name");
	    Reader reader = new Reader();

	    reader.read();
	    for (Book book : reader.getBooks()) {
            System.out.println(book);
            if (book.getTitle().equals("java gently")) {
                System.out.println("Found");
            }
            if (book.getAuthor().equals("judith bishop")) {
                System.out.println(("Found judith"));
            }
        }
	    for (User u: reader.getUsers()) {
            System.out.println(u);

        }
        */
        IO io = new IO();
	    io.run();
    }
}


/*
TO DO:
 - Implement sorted array list functionality using comparable
    compare each element in the array and sort into alphabetical order
 - one text file to output from the command line
 - one text file to record librarian's interactions with the program displayed in
   the terminal window
 - make sure to use a sorted array list, not just arraylist
 - check if user is valid and book is in library. if not, throw. then see if
   book is on loan or not, a note to the user holding the book should be printed
   to a file informing them another user requested it and it should be returned asap
   if available, returned stored info
 * when returned, it must be checked if the user is valid, if it is on the list
   of books, and if it has been borrowed by the user. if not, throw
   if valid, update accordingly
 - don't forget to test
 */