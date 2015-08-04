//Book.java, 31 August 2014, Benjamin Knauth, Class Book for CMSC335 Project 1.
package cmsc335_project_1;
import java.util.ArrayList;

public class Book {
    private int index, author_index;//Data fields.
    private double price;
    private String title, genre, author;
    ArrayList<String> ExtraFields = new ArrayList();
    
    public Book(int index, String title, String genre, double price, 
                int author_index, ArrayList<String> ExtraFields){//Constructor.
        this.index = index;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.author_index = author_index;
        this.ExtraFields = ExtraFields;
    }
    
    @Override
    public String toString(){//Prints book information.
        String indexString = Integer.toString(this.index);
        String author_indexString = Integer.toString(this.author_index);
        String priceString = Double.toString(this.price);
        String concatenation = "Index: " + indexString + "\n"
                             + "Title: " + this.title + "\n"
                             + "Genre: " + this.genre + "\n"
                             + "Price: " + priceString + "\n"
                             + "Author Index: " + author_indexString;
        return concatenation;                
    }
    
    public int getIndex(){//Getters and setters
        return index;
    }
    
    public int getAuthorIndex(){
        return author_index;
    }
    
    public double getPrice(){
        return price;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public void setIndex(int index){
        this.index = index;
    }
    
    public void setAuthorIndex(int author_index){
        this.author_index = author_index;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }
    
    public void setGenre(String genre){
        this.genre = genre;
    }
}
