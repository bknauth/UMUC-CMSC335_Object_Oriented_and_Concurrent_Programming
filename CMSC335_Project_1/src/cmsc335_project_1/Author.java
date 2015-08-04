//Author.java, 31 August 2014, Benjamin Knauth, Class Author for CMSC335 Project 1.
package cmsc335_project_1;
import java.util.ArrayList;

public class Author {
    private int index, numberBooks;//Data fields.
    private String name, address;   
    ArrayList<Book> Books = new ArrayList();//Holds objects of type Book.
    ArrayList<String> ExtraFields = new ArrayList();
    
    public Author(int index, String name, String address, ArrayList<String> ExtraFields){//Constructor.
        this.index = index;
        this.name = name;
        this.address = address;
        this.ExtraFields = ExtraFields;
    }
    
    @Override
    public String toString(){//Prints author information.
        String indexString = Integer.toString(this.index);
        String numberBooksString = Integer.toString(numberBooks);
        String concatenation = "Index: " + indexString + "\n"
                             + "Name: " + name + "\n"
                             + "Address: " + address + "\n"
                             + "Books: \n";
        for(Book i : Books){
            concatenation = concatenation + "   " + i.getTitle() + "\n";
        }                     
        concatenation = concatenation + "Number of Books: " + numberBooksString + "\n";
        return concatenation;                
    }
    
    public int getIndex(){//Getters and setters.
        return index;
    }
    
    public int getNumberBooks(){
        return numberBooks;
    }
    
    public String getName(){
        return name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setIndex(int index){
        this.index = index;
    }
    
    public void setNumberBooks(){
        this.numberBooks = Books.size();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
}
