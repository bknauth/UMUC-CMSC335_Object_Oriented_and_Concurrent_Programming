//Journal.java, 11 September 2014, Benjamin Knauth, Class Book for CMSC335 Project 2.
package cmsc335_project_2;
import java.util.ArrayList;

public class Journal {    
    
    private String date;
    private int issueNumber, author_index;
    ArrayList<String> ExtraFields = new ArrayList();
    
    Journal(String date, int issueNumber, int author_index, ArrayList<String> ExtraFields){
        this.date = date;
        this.issueNumber = issueNumber;
        this.author_index = author_index;
        this.ExtraFields = ExtraFields;
    }
    
    @Override
    public String toString(){//Prints book information.
        String issueNumberString = Integer.toString(this.issueNumber);
        
        String concatenation = "Issue Number: " + issueNumberString + "\n"
                             + "Date: " + this.date + "\n";
        return concatenation;                
    }
    
    public String getDate(){
        return this.date;
    }
    
    public int getIssueNumber(){
        return this.issueNumber;
    }
    
    public int getAuthorIndex(){
        return this.author_index;
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
}