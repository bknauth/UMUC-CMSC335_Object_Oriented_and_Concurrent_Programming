//Journal.java, 29 September 2014, Benjamin Knauth, Class Book for CMSC335 Project 3.
package cmsc335_project_4;
import java.util.ArrayList;

public class Journal {    
    
    private String date;
    private int issueNumber, author_index, copies;
    ArrayList<String> ExtraFields = new ArrayList();
    
    Journal(String date, int issueNumber, int author_index, ArrayList<String> ExtraFields){
        this.date = date;
        this.issueNumber = issueNumber;
        this.author_index = author_index;
        this.ExtraFields = ExtraFields;
        copies = 5;
    }
    
    @Override
    public String toString(){//Prints book information.
        String issueNumberString = Integer.toString(this.issueNumber);
        
        String concatenation = "Issue Number: " + issueNumberString + "\n"
                             + "Date: " + this.date + "\n";
        return concatenation;                
    }
    
    public String getDate(){
        return date;
    }
    
    public int getIssueNumber(){
        return issueNumber;
    }
    
    public int getAuthorIndex(){
        return author_index;
    }
    
    public int getCopies(){
        return copies;
    }
    
    public void setDate(String newDate){
        this.date = newDate;
    }
    
    public void setIssueNumber(int newIssueNumber){
        this.issueNumber = newIssueNumber;
    }
   
    public void setAuthorIndex(int author_index){
        this.author_index = author_index;
    }
    
    public void setCopies(int newCopies){
        this.copies = newCopies;
    }
}