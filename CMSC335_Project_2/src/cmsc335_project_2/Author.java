//Author.java,  11 September 2014, Benjamin Knauth, Class Author for CMSC335 Project 2.
package cmsc335_project_2;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Comparator;

public class Author{
    
    private int index, numberBooks, numberJournals;//Data fields.
    private String name, address;   
    LinkedHashMap<Integer, Book> Books = new LinkedHashMap<Integer,Book>();//Holds objects of type Book.
    LinkedHashMap<Integer, Journal> Journals = new LinkedHashMap<Integer,Journal>();
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
        String numberJournalsString = Integer.toString(numberJournals);
        String concatenation = "Index: " + indexString + "\n"
                             + "Name: " + name + "\n"
                             + "Address: " + address + "\n"
                             + "Books: \n";
        for(Book i : Books.values()){
            concatenation = concatenation + "   " + i.getTitle() + "\n";
        }
        
        concatenation = concatenation + "Journals: \n";
        
        for(Journal i : Journals.values()){
            concatenation = concatenation + "   " + i.getIssueNumber() + "\n";
        }
        concatenation = concatenation + "Number of Books: " + numberBooksString + "\n"
                        + "Number of Journals: " + numberJournalsString + "\n";
        
        return concatenation;                
    }
    
    public LinkedHashMap<Integer, Book> sortBooksTitle(LinkedHashMap<Integer, Book> books){
        LinkedList<Book> llBooks = new LinkedList<Book>();
        llBooks.addAll(books.values());
        books.clear();
        
        Collections.sort(llBooks, new BookTitleComparator());
        for(Book i : llBooks){
            books.put(i.getIndex(), i);
        }
        return books;
    }
    
    public LinkedHashMap<Integer, Book> sortBooksPrice(LinkedHashMap<Integer, Book> books){
        LinkedList<Book> llBooks = new LinkedList<Book>();
        llBooks.addAll(books.values());
        books.clear();
        
        Collections.sort(llBooks, new BookPriceComparator());
        for(Book i : llBooks){
            books.put(i.getIndex(), i);
        }
        return books;
    }
    
    public LinkedHashMap<Integer, Book> sortBooksIndex(LinkedHashMap<Integer, Book> books){
        LinkedList<Book> llBooks = new LinkedList<Book>();
        llBooks.addAll(books.values());
        books.clear();
        
        Collections.sort(llBooks, new BookIndexComparator());
        for(Book i : llBooks){
            books.put(i.getIndex(), i);
        }
        return books;
    }
    
    public LinkedHashMap<Integer, Journal> sortJournalDate(LinkedHashMap<Integer, Journal> journals){
        LinkedList<Journal> llJournal = new LinkedList<Journal>();
        llJournal.addAll(journals.values());
        journals.clear();
        
        Collections.sort(llJournal, new JournalDateComparator());
        for(Journal i : llJournal){
            journals.put(i.getIssueNumber(), i);
        }
        return journals;
    }
    
    public LinkedHashMap<Integer, Journal> sortJournalIssue(LinkedHashMap<Integer, Journal> journals){
        LinkedList<Journal> llJournal = new LinkedList<Journal>();
        llJournal.addAll(journals.values());
        journals.clear();
        
        Collections.sort(llJournal, new JournalIssueComparator());
        for(Journal i : llJournal){
            journals.put(i.getIssueNumber(), i);
        }
        return journals;
    }  
    
    public int getIndex(){//Getters and setters.
        return index;
    }
    
    public int getNumberBooks(){
        return numberBooks;
    }
    
    public int getNumberJournals(){
        return numberJournals;
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
    
    public void setNumberJournals(){
        this.numberJournals = Journals.size();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
}

class BookTitleComparator implements Comparator<Book>{
    @Override
    public int compare(Book t1, Book t2) {
        return t1.getTitle().compareTo(t2.getTitle());
    }    
}

class BookIndexComparator implements Comparator<Book>{
    @Override
    public int compare(Book t1, Book t2) {
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

class BookPriceComparator implements Comparator<Book>{
    @Override
    public int compare(Book t1, Book t2) {
        double index1 = t1.getPrice();
        double index2 = t2.getPrice();
        
        if(index1 > index2)
            return 1;
        else if(index1 < index2)
            return -1;
        else
            return 0;
    }
}

class JournalDateComparator implements Comparator<Journal>{
    @Override
    public int compare(Journal t1, Journal t2) {
        return t1.getDate().compareTo(t2.getDate());
    }    
}

class JournalIssueComparator implements Comparator<Journal>{
    @Override
    public int compare(Journal t1, Journal t2) {
        int index1 = t1.getIssueNumber();
        int index2 = t2.getIssueNumber();
        
        if(index1 > index2)
            return 1;
        else if(index1 < index2)
            return -1;
        else
            return 0;
    }
}