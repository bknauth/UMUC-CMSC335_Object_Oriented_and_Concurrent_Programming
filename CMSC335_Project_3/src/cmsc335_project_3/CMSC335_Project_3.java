//CMSC335_Project_3.java, 29 September 2014, Benjamin Knauth, main() method.
package cmsc335_project_3;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class CMSC335_Project_3 {

    public static void main(String[] args) throws Exception{
        Library library = new Library();
        ArrayList<String> ExtraFields = new ArrayList();
        ArrayList<Book> tempList = new ArrayList();
        ArrayList<Journal> tempList2 = new ArrayList();
        Author UnknownAuthor = new Author(99999, "Unknown", "Unknown", ExtraFields);
        library.Authors.put(UnknownAuthor.getIndex(), UnknownAuthor);//If a book object's author_index doesn't match any author index, the object is placed here.);
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileHidingEnabled(false);//Ensures "starting at dot".
        
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            java.io.File file = fileChooser.getSelectedFile();       
            Scanner input = new Scanner(file);

            while(input.hasNextLine()){
                String lineString = input.nextLine();
                while(lineString.length() == 0 && input.hasNextLine()){//Ensures program skips blank lines.
                    lineString = input.nextLine();
                }

                Scanner line = new Scanner(lineString);            
                line.useDelimiter(":");

                String token;
                token = line.next().trim();

                char token2 = token.charAt(0);
                boolean authorExists;
                int author_index;

                switch(token2){                    
                    case 'a'://Initializes authors.
                        int authorIndex = Integer.parseInt(line.next().trim());
                        String name = line.next().trim();
                        String address = line.next().trim();                    

                        ExtraFields.clear();
                        while(line.hasNext()){
                            ExtraFields.add(line.next().trim());
                        }

                        library.Authors.put(authorIndex, new Author(authorIndex, name, address, ExtraFields));                    
                        break;

                    case 'b'://Initializes books.
                        authorExists = false;
                        int bookIndex = Integer.parseInt(line.next().trim());
                        String title = line.next().trim();
                        String genre = line.next().trim();
                        double price = Double.parseDouble(line.next().trim());
                        author_index = Integer.parseInt(line.next().trim());

                        ExtraFields.clear();
                        while(line.hasNext()){
                            ExtraFields.add(line.next().trim());
                        }

                        for(Author i : library.Authors.values()){
                            if(i.getIndex() == author_index){
                                authorExists = true;
                                i.Books.put(bookIndex, new Book(bookIndex, title, genre, price, author_index, ExtraFields));
                            }
                        }

                        if(authorExists == false){
                            UnknownAuthor.Books.put(bookIndex, new Book(bookIndex, title, genre, price, author_index, ExtraFields));
                        }
                        break;
                        
                    case 'j'://Initializes journals.
                        authorExists = false;
                        String date = line.next().trim();
                        int issueNumber = Integer.parseInt(line.next().trim());
                        author_index = Integer.parseInt(line.next().trim());                   
                        
                        ExtraFields.clear();
                        while(line.hasNext()){
                            ExtraFields.add(line.next().trim());
                        }

                        for(Author i : library.Authors.values()){
                            if(i.getIndex() == author_index){
                                authorExists = true;
                                i.Journals.put(issueNumber, new Journal(date, issueNumber, author_index, ExtraFields));
                            }
                        }

                        if(authorExists == false){
                            UnknownAuthor.Journals.put(issueNumber, new Journal(date, issueNumber, author_index, ExtraFields));
                        }
                        break;

                    case '/'://Ignores lines beginning with "/".
                        break;

                    default: System.out.println("Line does not begin with \"a\", \"b\""
                            + " or \"/\"");
                }   
            }
        }
        
        for(Book i: UnknownAuthor.Books.values()){//Once file is parsed, this rechecks books under UnknownAuthor to see if they have authors.
            boolean thisExists = false;
            for(Author j : library.Authors.values()){
                if(i.getAuthorIndex() == j.getIndex()){
                    thisExists = true;
                    j.Books.put(i.getIndex(), i);                    
                }                
            }
            if(thisExists == false){//UknownAuthor.Books.remove(i) produces an error during runtime do to concurrency issues.  
                tempList.add(i);    
            }
        }
        UnknownAuthor.Books.clear();
        
        for(Book i : tempList){
            UnknownAuthor.Books.put(i.getIndex(), i);
        }
        
        for(Journal i: UnknownAuthor.Journals.values()){//Once file is parsed, this rechecks journals under UnknownAuthor to see if they have authors.
            boolean thisExists = false;
            for(Author j : library.Authors.values()){
                if(i.getAuthorIndex() == j.getIndex()){
                    thisExists = true;
                    j.Journals.put(i.getIssueNumber(), i);                    
                }                
            }
            if(thisExists == false){//UknownAuthor.Books.remove(i) produces an error during runtime do to concurrency issues.  
                tempList2.add(i);    
            }
        }
        UnknownAuthor.Journals.clear();
        
        for(Journal i : tempList2){
            UnknownAuthor.Journals.put(i.getIssueNumber(), i);
        }
        
        
        for(Author i : library.Authors.values()){//Ensures each Author object has numberBooks field up to date.
            i.setNumberBooks();
            i.setNumberJournals();
        }
        
        JFrame gui = new JTreeGUI(library);//Output.
        gui.setTitle("Project 3");
        gui.setSize(500, 400);
        gui.setLocationRelativeTo(null);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);        
    }
}