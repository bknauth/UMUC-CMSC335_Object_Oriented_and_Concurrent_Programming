//Library.java, 31 August 2014, Benjamin Knauth, Class Library for CMSC335 Project 1.
package cmsc335_project_1;
import java.util.ArrayList;

public class Library {
    
    ArrayList<Author> Authors = new ArrayList();//Holds objects of type Author.
    
    @Override
    public String toString(){//Prints list of Authors and Books,ie Full List button.
        String concatenation = "";
        for(Author i : Authors){
            concatenation = concatenation + i.toString() + "\n";            
        }
        
        return concatenation;        
    }
}
