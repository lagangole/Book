import ecs100.*;
/**
 * GUI class for books manager 
 * Allows add, print, find books on GUI
 *
 * @author WGT
 * @version 8 April
 */
public class GUI
{
    // field
    private Books books;     //declare books instance
    private Book book;      //declare book instance
    
    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        books = new Books();           //instantiate the books
        
        // Set up GUI
        UI.initialise();
        UI.addButton("Print All", books::printAllBooks);
        UI.addButton("Add", this::addBook);
        UI.addButton("Find", this::findBook);
        UI.addButton("Delete", this::deleteBook);
        UI.addButton("Remove", this::removeBook);
        UI.addButton("Like", this::likeBook);
        UI.addButton("Quit", UI::quit);

    }

    /**
     *Add a book to collection
     */
    public void addBook()
    {
        // force a range of quantity
        final int MAX_QUANTITY = 99; // max quantity it can add to the hashmap
        int quantity;

        //Ask the user for details
        String name = UI.askString("Title: ");
        String author = UI.askString("Author: ");

        //Check boundaries for the number of books added
        do {
            quantity = UI.askInt("Quantity: ");
            if ((quantity > 0) && (quantity <= MAX_QUANTITY)){
                UI.println("Added");
            }else if (quantity > MAX_QUANTITY) {
                UI.println("Must be less than 100");
            }else if (quantity < 1){
                UI.println("Must be greater than 0");
            }else{
                UI.println("Must be a number!");
            }
        }while (0 > quantity || quantity > MAX_QUANTITY);
        // add a book image for display
        String imgFileName = UIFileChooser.open("Choose Image File: ");
        
        // add books with images
        this.books.addBook(name, author, quantity, imgFileName);
    }

    /**
     * Finds book based on name
     * Prints out the author and qty if found
     */
    public void findBook(){
        String bookName = UI.askString("Name of Book: ");
        String bookAuth = UI.askString("Author of Book: ");

        if(this.books.findBook(bookName.toLowerCase().trim(),bookAuth.toLowerCase().trim()) != null){ // Refer back to Books instance books method findBook to return true or false

            this.book = books.getBook(); 
            UI.println("Found Book!");
            
            this.book.displayBook();
            UI.println("Author: " + this.book.getAuthor());
            UI.println("Quantity: " + this.book.getQuantity());

        }
        else{
            UI.println("That book does not exist!");
        }
    }
    
    /**
     * Find book and delete
     * Decrease quantity
     */
    public void deleteBook(){
        String bookName = UI.askString("Name of Book: ");
        String bookAuth = UI.askString("Author of Book: ");
        
        Book book = books.findBook(bookName.trim().toLowerCase(),bookAuth.trim().toLowerCase());
    
        if (book != null) {
            // Display the book details before asking for confirmation
            UI.println("Book found!");
    
            String confirmation = UI.askString("Do you want to delete a book of the collection? (yes/no): ");
            if (confirmation.equalsIgnoreCase("yes")) {
                this.books.deleteBook(book);
            } 
            else {
                UI.println("Book deletion cancelled.");
            }
        } else {
            UI.println("That book does not exist!");
        }
    }
    
    /**
     * Remove bookId
     */
    public void removeBook(){
        String bookName = UI.askString("Name of Book: ");
        String bookAuth = UI.askString("Author of Book: ");
        Book book = books.findBook(bookName.trim().toLowerCase(),bookAuth.trim().toLowerCase());
        if (book != null) {
            // Display the book details before asking for confirmation
            UI.println("Book found!");
            book.displayBook();
            UI.println("Author: " + book.getAuthor());
            UI.println("Quantity: " + book.getQuantity());
    
            String confirmation = UI.askString("Do you want to remove this book?(yes/no): ");
            if (confirmation.equalsIgnoreCase("yes")) {
                this.books.removeBook(bookName, bookAuth);
                UI.println("Remove success");
            } else if (confirmation.equalsIgnoreCase("no")) {
                UI.println("Book deletion cancelled.");
            }
            else {
                UI.println("Try again");
            }
        } else {
            UI.println("That book does not exist!");
        }
        
    }
    
    /**
     * Like a book
     */
    public void likeBook(){
        String bookName = UI.askString("Name of Book: ");
        String bookAuth = UI.askString("Author of Book: ");
        Book book = books.findBook(bookName, bookAuth);  // directly get the book
    
        if (book != null) {
            book.displayBook();
            UI.println("Author: " + book.getAuthor());
    
            String confirmation = UI.askString("Proceed to like this book? (yes/no): ");
            if (confirmation.equalsIgnoreCase("yes")) {
                book.increaseLike();
                UI.println("Book liked successfully! Total likes: " + book.getLike());
            } else {
                UI.println("Book like cancelled.");
            }
        } else {
            UI.println("That book does not exist!");
        }   
    }
    
    /**
     * main routine
     */
    public static void main(String[] args) {
        new GUI();
    }
    
}