import ecs100.*;
import java.awt.Color;
/**
 * Support class for Books
 * A Book contains an id, name, author, quantity, image
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
    private double imageX, imageY, imageWidth, imageHeight;
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
    public void displayBooks(double locX, double locY, double canvasWidth) {
        final int booksPerRow = 3;
        double spacing = 18;
        double width = (canvasWidth - spacing * (booksPerRow + 1)) / booksPerRow;
        double height = width * 1.2; // maintain aspect ratio
        
        this.imageX = locX;
        this.imageY = locY;
        this.imageWidth = width;
        this.imageHeight = height;

        // Clear the previous text area
        UI.setColor(Color.white); // or whatever your background color is
        UI.fillRect(locX, locY + height + 5, width, 20); // adjust width/height as needed
        
        // Draw the updated text
        UI.setColor(Color.black); // reset text color

        UI.drawImage(this.image, locX, locY, width, height);
        UI.drawString("Likes: " + getLike(), locX, locY+ height + 30);
        UI.drawString(getName()+ " by " + getAuthor(), locX, locY+ height + 14);
    }
    
    public void displayBook(){
        UI.clearGraphics();  // Clears everything on the canvas
        
        int locX = 100; // image x start position
        int locY = 100; // image y start position
        final double WIDTH = 250;
        final double HEIGHT = 300;
        
        this.imageX = locX;
        this.imageY = locY;
        this.imageWidth = WIDTH;
        this.imageHeight = HEIGHT;
        
        // Clear the previous text area
        UI.setColor(Color.white); // or whatever your background color is
        UI.fillRect(locX, locY + HEIGHT + 5, 100, 20); // adjust width/height as needed
        
        // Draw the updated text
        UI.setColor(Color.black); // reset text color

        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
        UI.drawString("Likes: " + getLike(), locX, locY+ HEIGHT + 30);
        UI.drawString(getName()+ " by " + getAuthor(), locX, locY+ HEIGHT + 14);
    }
    
    public boolean wasClicked(double mouseX, double mouseY) {
        if ((mouseX >= imageX) && (mouseX <= imageX + imageWidth) &&
               (mouseY >= imageY) && (mouseY <= imageY + imageHeight)){
                   return true;
        }
        else{
            return false;
        }
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