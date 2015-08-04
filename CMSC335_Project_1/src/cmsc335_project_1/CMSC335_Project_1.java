//CMSC335_Project_1.java, 31 August 2014, Benjamin Knauth, main() method.
package cmsc335_project_1;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class CMSC335_Project_1 {

    public static void main(String[] args) throws Exception{
        Library library = new Library();
        ArrayList<String> ExtraFields = new ArrayList();
        ArrayList<Book> tempList = new ArrayList();
        Author UnknownAuthor = new Author(99999, "Unknown", "Unknown", ExtraFields);
        library.Authors.add(UnknownAuthor);//If a book object's author_index doesn't match any author index, the object is placed here.);
        
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

                switch(token2){
                    case 'a'://Initializes authors.
                        int authorIndex = Integer.parseInt(line.next().trim());
                        String name = line.next().trim();
                        String address = line.next().trim();                    

                        ExtraFields.clear();
                        while(line.hasNext()){
                            ExtraFields.add(line.next().trim());
                        }

                        library.Authors.add(new Author(authorIndex, name, address, ExtraFields));                    
                        break;

                    case 'b'://Initializes books.
                        boolean authorExists = false;
                        int bookIndex = Integer.parseInt(line.next().trim());
                        String title = line.next().trim();
                        String genre = line.next().trim();
                        Double price = Double.parseDouble(line.next().trim());
                        int author_index = Integer.parseInt(line.next().trim());

                        ExtraFields.clear();
                        while(line.hasNext()){
                            ExtraFields.add(line.next().trim());
                        }

                        for(Author i:library.Authors){
                            if(i.getIndex() == author_index){
                                authorExists = true;
                                i.Books.add(new Book(bookIndex, title, genre, price, author_index, ExtraFields));
                            }
                        }

                        if(authorExists == false){
                            UnknownAuthor.Books.add(new Book(bookIndex, title, genre, price, author_index, ExtraFields));
                        }
                        break;

                    case '/'://Ignores lines beginning with "/".
                        break;

                    default: System.out.println("Line does not begin with \"a\", \"b\""
                            + " or \"/\"");
                }   
            }
        }
        
        for(Book i: UnknownAuthor.Books){//Once file is parsed, this rechecks books under UnknownAuthor to see if they have authors.
            boolean thisExists = false;
            for(Author j: library.Authors){
                if(i.getAuthorIndex() == j.getIndex()){
                    thisExists = true;
                    j.Books.add(i);                    
                }                
            }
            if(thisExists == false){//UknownAuthor.Books.remove(i) produces an error during runtime do to concurrency issues.  
                tempList.add(i);    
            }
        }
        UnknownAuthor.Books.clear();
        UnknownAuthor.Books.addAll(tempList); 
        
        for(Author i : library.Authors){//Ensures each Author object has numberBooks field up to date.
            i.setNumberBooks();
        }        
        JFrame gui = new GUI(library.toString(), library);//Output.
        gui.setVisible(true);
    }
}