//Library.java, 11 September 2014, Benjamin Knauth, Class Library for CMSC335 Project 2.
package cmsc335_project_2;
import java.util.Collections;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Comparator;

public class Library {
    
    //ArrayList<Author> Authors = new ArrayList();//Holds objects of type Author.
    LinkedHashMap<Integer, Author> Authors = new LinkedHashMap<Integer, Author>();
    
    @Override
    public String toString(){//Prints list of Authors and Books,ie Full List button.
        String concatenation = "";
        for(Author i : Authors.values()){
            concatenation = concatenation + i.toString() + "\n";            
        }
        
        return concatenation;        
    }
    
    public LinkedHashMap<Integer, Author> sortAuthorNames(LinkedHashMap<Integer, Author> authors){
        LinkedList<Author> llAuthors = new LinkedList<Author>();
        llAuthors.addAll(authors.values());
        authors.clear();
        
        Collections.sort(llAuthors, new AuthorNameComparator());
        for(Author i : llAuthors){
            authors.put(i.getIndex(), i);
        }
        return authors;
    }
    
    public LinkedHashMap<Integer, Author> sortAuthorIndexes(LinkedHashMap<Integer, Author> authors){
        LinkedList<Author> llAuthors = new LinkedList<Author>();
        llAuthors.addAll(authors.values());
        authors.clear();
        
        Collections.sort(llAuthors, new AuthorIndexComparator());
        for(Author i : llAuthors){
            authors.put(i.getIndex(), i);
        }
        return authors;
    }
}

class AuthorNameComparator implements Comparator<Author>{
    @Override
    public int compare(Author t1, Author t2) {
        return t1.getName().compareTo(t2.getName());
    }    
}

class AuthorIndexComparator implements Comparator<Author>{
    @Override
    public int compare(Author t1, Author t2) {
        int index1 = t1.getIndex();
        int index2 = t2.getIndex();
        
        if(index1 > index2)
            return 1;
        else if(index1 < index2)
            return -1;
        else
            return 0;
    }    
}