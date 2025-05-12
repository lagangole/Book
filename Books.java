import java.util.HashMap;
import java.util.Scanner;
import ecs100.*;
/**
 * Holds a collection of books in a hashmap
 * Allows a user to add, find, print all 
 * 
 * ??? delete
 * ??? prevent user from adding a duplicate
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
        //Traverse Map
        for (int bookId : this.library.keySet()){
            UI.println(bookId + " Details: ");
            UI.println(this.library.get(bookId).getName() + " "
                        +this.library.get(bookId).getAuthor() + " "
                        +this.library.get(bookId).getQuantity() + " "
                        +this.library.get(bookId).getLike()+ " ");
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
     * @param name, author, qty
     */
    public void deleteBook(String name, String author) {
        name = name.trim().toLowerCase();
        author = author.trim().toLowerCase();
    
        // Loop through the map to find the book
        for (int bookId : this.library.keySet()) {
            Book book = this.library.get(bookId);
    
            // Check if the book matches name and author
            if (book.getName().toLowerCase().equals(name) && book.getAuthor().toLowerCase().equals(author)) {
                // Decrease the quantity
                if (book.getQuantity() > 1) {
                    book.decreaseQuantity();  // Decreases the quantity by 1
                    UI.println("Quantity decreased by 1. New quantity: " + book.getQuantity());
                } else {
                    // If quantity is 1 or less, remove the book from the library
                    this.library.remove(bookId);
                    UI.println("Book removed from the library.");
                }
                return;  // Exit after processing the book
            }
            else{
                UI.println("That book does not exist!");
            }
        }

    }
    
    /**
     * Remove a book of the map
     * @param name, author, qty
     */
    public void removeBook(String name){
        for (int bookId : this.library.keySet()){
            Book b = this.library.get(bookId);
            if (b.getName().equals(name)){
                this.library.remove(bookId);
                UI.println("Remove success");
                break;
            }
            else{
                UI.println("Book not found");
                break;
            }
        }
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
                    // try and except
                    while (!valid) {
                        System.out.print("Enter quantity: ");
                        String input = scanner.nextLine();
                        try {
                            qty = Integer.parseInt(input); // converts string to int
                            if (qty > 0) {
                                valid = true;
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
                    System.out.print("\nEnter book title: ");
                    String Rtitle = scanner.nextLine();
                    removeBook(Rtitle);
                    break;
                case "D":
                    System.out.print("\nEnter book title: ");
                    String Dtitle = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String Dauthor = scanner.nextLine();
                    deleteBook(Dtitle, Dauthor);
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