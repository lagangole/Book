import ecs100.*;
/**
 * Support class for Books
 * A Book contains an id, name, author, quantity, image
 * 
 * ? add likes?
 * 
 * @author WGT
 * @version (a version number or a date)
 */
public class Book
{
    // fields
    private int id;
    private String name;
    private String author;
    private int quantity;
    private String image;
    private int likes = 0;
    static final String DEFAULT_IMAGE = "book.jpg"; // Set a default image

    /**
     * Constructor for objects of class book, this constructor overloaded so set default image to obj
     * @param key, nm, auth, qty, img
     */
    public Book(int key, String nm, String auth, int qty, String img)
    {
        id = key;
        name = nm; 
        author = auth;
        quantity = qty;
        if( img == null) {
            this.image = DEFAULT_IMAGE; // add default img if user clicks cancel
        }else{
            this.image = img;
        }
    }
    
    /**
     * Constructor for objects of class book
     * @param key, nm, auth, qty
     */
    public Book(int key, String nm, String auth, int qty)
    {
        this(key, nm, auth, qty, DEFAULT_IMAGE);
    }
    
    /**
     * Display image on GUI
     */
    public void displayBook() {
        int locX = 100; // image x start position
        int locY = 100; // image y start position
        
        final double WIDTH = 250;
        final double HEIGHT = 300;
        
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
    }

    /**
     * Getter for id
     * @return integer id number of obj book
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Getter for name
     * @return String book name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Getter for author
     * @return String author's name
     */
    public String getAuthor(){
        return this.author;
    }
    
    /**
     * Getter for quantity
     * @return integer with quantity of the obj book
     */
    public int getQuantity(){
        return this.quantity;
    }
    
    /**
     * Getter for image 
     * @return String image 
     */
    public String getImage(){
        return this.image;
    }
    
    /**
     * Getter for likes
     */
    public int getLike(){
        return this.likes;
    }
    
    /**
     * Increase like by 1
     */
    public int increaseLike(){
        this.likes++;
        return this.likes;
    }
    
    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }
    
}