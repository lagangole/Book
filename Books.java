import java.util.HashMap;
import java.util.Scanner;
import ecs100.*;
/**
 * Holds a collection of books in a hashmap
 * Allows a user to add, find, print all 
 * 
 * what I haven't done : comment, test, add boundry to other cases
 *
 * @author WGT
 * @version (a version number or a date)
 */
public class Books
{
    // fields
    private HashMap<Integer, Book> library; // declaring the hashmap
    private int currBookId; // store the current id of book being added
    private Book currBook; // store the instance of the current book
    private Scanner scanner;
    /**
     * Constructor for objects of class Books
     */
    public Books()
    {
        // initialise instance variables
        library = new HashMap<Integer, Book>(); //initialise hashmap
        scanner = new Scanner(System.in); // initialise scanner
        
        //Create books
        Book b1 = new Book(1, "The Wicked King", "Holly Black", 2);
        Book b2 = new Book(2, "1984", "George Orwell", 42);
        Book b3 = new Book(3, "Song of Achilles", "Madelline Millar", 20);
        
        //add books to collection
        this.library.put(1, b1);
        this.library.put(2, b2);
        this.library.put(3, b3);
        
        this.currBookId = 3;        // Store the current book id
        
    }
    
    public HashMap<Integer, Book> getLibrary() {
        return this.library;
    }
    
    /**
     * Set bookId, increment by 1 
     */
    public void setBookId(){
        this.currBookId += 1;
    }
    
    /**
     * Add a book to the map
     * @param name, author, qty
     */
    public void addBook(String name, String author, int qty){
        this.setBookId(); // increment by 1 currentID
        this.library.put(this.currBookId, new Book(this.currBookId, name, author, qty));
    }
        
    /**
     * Removes extra space 
     * Capitalise first letter of string words
     */
    public String formatTitleCase(String input) {
        input = input.trim().replaceAll("\\s+", " "); // remove extra spaces
        String[] words = input.toLowerCase().split(" "); // Forms array of each word & lowercase
        StringBuilder formatted = new StringBuilder();
    
        for (String word : words) {
            if (!word.isEmpty()) {
                formatted.append(Character.toUpperCase(word.charAt(0))) // Capitalise first letter
                         .append(word.substring(1))
                         .append(" ");
            }
        }
        return formatted.toString().trim(); // Return array to string 
    }
        
    /**
     * Print details of all books
     * Console based interaction
     */
    public void printAll(){
        //Traverse Map
        for (int bookId : this.library.keySet()){
            System.out.println("BOOK"+bookId + " Details: ");
            System.out.println("Title: "+ this.library.get(bookId).getName() + "\nAuthor: "
                        + this.library.get(bookId).getAuthor() + "\nAvailable copies: "
                        +this.library.get(bookId).getQuantity() + "\nLikes: "
                        +this.library.get(bookId).getLike()+" \n");
        }
    }
    
    /**
     * Print all books
     * GUI 
     */
    public void printAllBooks(){
        UI.clearGraphics();  // Clears everything on the canvas
        double canvasWidth = UI.getCanvasWidth(); // get screen size
        double spacing = 20;
        int booksPerRow = 3;
        double imageWidth = (canvasWidth - spacing * (booksPerRow + 1)) / booksPerRow;
        double imageHeight = imageWidth * 1.2;
        int count = 0;
        
        //Traverse Map
        for (int bookId : this.library.keySet()){
            // Calculate grid position
            int col = count % booksPerRow;
            int row = count / booksPerRow;
    
            double locX = spacing + col * (imageWidth + spacing);
            double locY = spacing + row * (imageHeight + 60);  // extra space for text

            UI.println(bookId + " Details: ");
            UI.println("Title: "+ this.library.get(bookId).getName() + "\nAuthor: "
                        + this.library.get(bookId).getAuthor() + "\nAvailable copies: "
                        +this.library.get(bookId).getQuantity() + "\nLikes: "
                        +this.library.get(bookId).getLike()+" \n");
            this.library.get(bookId).displayBooks(locX, locY, canvasWidth);  // shift each book;
            count++;
        }
    }
    
    /**
     * Print a book
     */
    public void printABook(int bookId){
        Book book = this.library.get(bookId);
        System.out.println("Title: " + book.getName()
                + "\nAuthor: " + book.getAuthor()
                + "\nAvailable copies: " + book.getQuantity()
                + "\nLikes: " + book.getLike() + "\n");
    }
      
    /**
     * Add a book to the map and display the cover on canva
     * Override the method with different param
     * @ param name, author, qty, img
     */
    public void addBook(String name, String author, int qty, String img){
        this.setBookId();
        this.library.put(this.currBookId, new Book(this.currBookId, name, author, qty, img));
    }
    
    /**
     * Finds a book based on name
     * Sets the current book instance if found
     * @return boolean false if not found
     */
    
    public Book findBook(String name, String author){
        //Search for book through hashmap library
        for (int bookId : this.library.keySet()){
            Book book = this.library.get(bookId);
            if(book.getName().toLowerCase().trim().equals(name.toLowerCase().trim()) &&
            book.getAuthor().toLowerCase().trim().equals(author.toLowerCase().trim())){
                this.currBook = book;
                return currBook;
            }
        }
        return null; //Not found
    }
    
    /**
     * delete a quantity
     * @param name, author
     */
    public int deleteBook(Book book){
        // Decrease the quantity
        if (book.getQuantity() > 1) {
            book.decreaseQuantity();  // Decreases the quantity by 1
            return book.getQuantity();
        } else {
            // If quantity is 1 or less, remove the book from the library
            removeBook(book.getName(), book.getAuthor());
            return 0;
        }
    }
    
    /**
     * Remove a book of the map
     * @param name, author, qty
     */
    public void removeBook(String name, String auth){
        for (int bookId : this.library.keySet()){
            Book b = this.library.get(bookId);
            if (b.getName().equalsIgnoreCase(name) && b.getAuthor().equalsIgnoreCase(auth)){
                this.library.remove(bookId);
                System.out.println("Remove success");
                return;
            }
        }
        System.out.println("Book not found");
    }
    
    /**
     * Book getter
     */
    public Book getBook(){
        return currBook;
    }
    
    /**
     * Menu to print and call appropriate methods
     * console-based menu
     */
    public void menu(){
        //Print menu and force choice
        String choice;
        do{
            System.out.println("\nPlease choose from the following:");
            System.out.println("- (P)rint all");
            System.out.println("- (A)dd a book");
            System.out.println("- (F)ind a book");
            System.out.println("- (L)ike");
            System.out.println("- (D)elete a book");
            System.out.println("- (R)emove entire book collection");
            System.out.println("- (Q)uit");
            
            choice = scanner.nextLine().trim().toUpperCase(); // avoid case-senstivity and whitespace before and after the string
            
            switch(choice){
                case "A": // only allows char "A" - add
                    System.out.print("\nEnter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    
                    title = formatTitleCase(title); // coverts string to cap and remove space
                    author = formatTitleCase(author);
                    
                    // Check for existing book
                    if (this.findBook(title, author) != null) {  // check if findBook book
                      System.out.println("Error: A book with that title already exists!");
                        break;
                    }
                    
                    boolean valid = false;
                    int qty = 0;
                    // try and except - probably create a sperate method for this
                    while (!valid) {
                        System.out.print("Enter quantity: ");
                        String input = scanner.nextLine();
                        try {
                            qty = Integer.parseInt(input); // converts string to int
                            if (qty > 0) {
                                valid = true;
                            }
                            else if (qty < 100){
                                System.out.println("That's too many, try again: ");
                            }
                            else {
                                System.out.println("Please enter a positive number.");
                            }    
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                    }
                
                    // call addBook method with 3 param: title, author, qty
                    this.addBook(title, author, qty);
                    System.out.println("Book added successfully! ID: " + this.currBookId);
                    break;
                    
                case "F":
                    System.out.print("\nEnter book title to find: ");
                    String searchTitle = scanner.nextLine();
                    System.out.print("\nEnter book author: ");
                    String searchAuthor = scanner.nextLine();
                        
                    if (this.findBook(searchTitle, searchAuthor) != null){
                        System.out.println("\nBook found! ");
                        printABook(this.currBook.getId()); // print current book   
                    }else{
                        System.out.println("Book not found!");
                    }
                    break;
                        
                case "P":
                    printAll();
                    break;
                        
                case "L":
                    System.out.print("\nEnter book title: ");
                    String likeTitle = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String likeAuth = scanner.nextLine();
                    // Check for existing book
                    if (this.findBook(likeTitle, likeAuth ) != null) {  // check if findBook return true
                        this.currBook.increaseLike();
                        System.out.println("\nYou liked \"" + this.currBook.getName() + "\". Total likes: " 
                        + this.currBook.getLike());
                    } 
                    else {
                        System.out.println("Book not found.");
                    }
                    break;
                case "R":
                    System.out.print("\nEnter book title to remove: ");
                    String Rtitle = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String Rauth = scanner.nextLine();
                    removeBook(Rtitle, Rauth);
                    break;
                case "D":
                    System.out.print("\nEnter book title: ");
                    String Dtitle = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String Dauth = scanner.nextLine();
                    Book bookToDelete = findBook(Dtitle, Dauth);
                    if (bookToDelete != null) {
                        System.out.println("Quantity decreased by 1. New quantity: " 
                        + deleteBook(bookToDelete));
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case "Q":
                    System.out.println("Goodbye");
                    break;
                        
                default:
                    System.out.println("Invalid choice! Try again: ");
                    } 
        }while (!choice.equals("Q"));   //loop until choice is 'Q'
        scanner.close();
    }
    
    /**
     * main routine
     */
    public static void main(String[] args) {
        new Books().menu();
    }
}