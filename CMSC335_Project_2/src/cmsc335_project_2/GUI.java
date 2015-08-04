//GUI.java, 11 September 2014, Benjamin Knauth, Class GUI for CMSC335 Project 1.
package cmsc335_project_2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{    
    
    public GUI(final Library library){//Creates GUI to display data.
        final JTextArea textArea = new JTextArea(50, 50);
        //textArea.setText(library);        
        JScrollPane textScroller = new JScrollPane(textArea);
        
        final JTextArea searchBar = new JTextArea(1,10);
        searchBar.setToolTipText("Enter index, title, or genre, then click Search");
        JButton search = new JButton("Search");
        JButton fullList = new JButton("Full List");
        
        JPanel topPanel = new JPanel();//Top Panel.
        topPanel.add(searchBar);
        topPanel.add(search);
        topPanel.add(fullList);        
        
        JPanel leftPanel = new JPanel();//Left Panel with all sorting buttons.
        leftPanel.setLayout(new GridLayout(7,1,10,10));
        JButton allBooksTitle = new JButton("All Books - Title");
        JButton allBooksPrice = new JButton("All Books - Price");
        JButton allBooksIndex = new JButton("All Books - Index");
        JButton allJournDate = new JButton("All Journals - Date");
        JButton allJournIssue = new JButton("All Journals - Issue");
        JButton allAuthName = new JButton("All Authors - Name");
        JButton allAuthIndex = new JButton("All Authors - Index");
        leftPanel.add(allBooksTitle);
        leftPanel.add(allBooksPrice);
        leftPanel.add(allBooksIndex);
        leftPanel.add(allJournDate);
        leftPanel.add(allJournIssue);
        leftPanel.add(allAuthName);
        leftPanel.add(allAuthIndex);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(textScroller, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        
        this.setContentPane(mainPanel);
        this.setTitle("Library Management System (LIMS)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        
        fullList.addActionListener(new ActionListener() {//Full List button.
            @Override
            public void actionPerformed(ActionEvent e){
                library.sortAuthorIndexes(library.Authors);
                textArea.setText("");
                textArea.setText(library.toString());
            }            
        });
        
        allBooksTitle.addActionListener(new ActionListener() {//All Books - Title button.
            @Override
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                String concatenation = "";
                library.sortAuthorNames(library.Authors);//Creates consistancy whenever All Books - Title button pressed.                
                for(Author i : library.Authors.values()){
                    concatenation += i.getName() + ":\n";
                    i.sortBooksTitle(i.Books);
                    for(Book j : i.Books.values()){
                        concatenation += "    " + j.getTitle() + "\n";
                    }
                    concatenation += "\n";
                }
                textArea.setText(concatenation);                
            }            
        });
        
        allBooksPrice.addActionListener(new ActionListener() {//All Books - Price button.
            @Override
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                String concatenation = "";
                library.sortAuthorNames(library.Authors);                
                for(Author i : library.Authors.values()){
                    concatenation += i.getName() + ":\n";
                    i.sortBooksPrice(i.Books);
                    for(Book j : i.Books.values()){
                        concatenation += "    " + j.getTitle() + "  " + j.getPrice() + "\n";
                    }
                    concatenation += "\n";
                }
                textArea.setText(concatenation);                
            }            
        });
        
        allBooksIndex.addActionListener(new ActionListener() {//All Books - Index button.
            @Override
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                String concatenation = "";
                library.sortAuthorNames(library.Authors);               
                for(Author i : library.Authors.values()){
                    concatenation += i.getName() + ":\n";
                    i.sortBooksIndex(i.Books);
                    for(Book j : i.Books.values()){
                        concatenation += "    " + j.getTitle() + "  " + j.getIndex() + "\n";
                    }
                    concatenation += "\n";
                }
                textArea.setText(concatenation);                
            }            
        });
        
        allJournDate.addActionListener(new ActionListener() {//All Journal - Date button.
            @Override
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                String concatenation = "";
                library.sortAuthorNames(library.Authors);               
                for(Author i : library.Authors.values()){
                    concatenation += i.getName() + ":\n";
                    i.sortJournalDate(i.Journals);
                    for(Journal j : i.Journals.values()){
                        concatenation += "    Issue Number:  " + j.getIssueNumber() + "    Date:  " + j.getDate() + "\n";
                    }
                    concatenation += "\n";
                }
                textArea.setText(concatenation);                
            }            
        });
        
        allJournIssue.addActionListener(new ActionListener() {//All Journal - Issue button.
            @Override
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                String concatenation = "";
                library.sortAuthorNames(library.Authors);             
                for(Author i : library.Authors.values()){
                    concatenation += i.getName() + ":\n";
                    i.sortJournalIssue(i.Journals);
                    for(Journal j : i.Journals.values()){
                        concatenation += "    Issue Number:  " + j.getIssueNumber() + "    Date:  " + j.getDate() + "\n";
                    }
                    concatenation += "\n";
                }
                textArea.setText(concatenation);                
            }            
        });
        
        allAuthName.addActionListener(new ActionListener() {//All Author - Name button.
            @Override
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                String concatenation = "";
                library.sortAuthorNames(library.Authors);              
                for(Author i : library.Authors.values()){
                    concatenation += i.getName() + "\n";                    
                }
                textArea.setText(concatenation);                
            }            
        });
        
        allAuthIndex.addActionListener(new ActionListener() {//All Author - Index button.
            @Override
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                String concatenation = "";
                library.sortAuthorIndexes(library.Authors);            
                for(Author i : library.Authors.values()){
                    concatenation += i.getName() + "    " + i.getIndex() + "\n";                    
                }
                textArea.setText(concatenation);                
            }            
        });
        
        search.addActionListener(new ActionListener() {//Search button.
            @Override
            public void actionPerformed(ActionEvent e){
                String searchString = searchBar.getText();
                searchBar.setText("");
                textArea.setText("");
                
                if(isInteger(searchString) == true){//Search field is index.
                    int searchInt = Integer.parseInt(searchString);
                    
                    for(Author i : library.Authors.values()){
                        for(Book j : i.Books.values()){
                            if(j.getIndex() == searchInt){
                                textArea.setText(j.toString());
                            }
                        }
                    }
                }
                else{//Search field is genre or title.
                    String text = "";
                    
                    for(Author i : library.Authors.values()){
                        for(Book j : i.Books.values()){
                            if(j.getTitle().equals(searchString) 
                            || j.getGenre().equals(searchString)){
                                text = text + j.toString() + "\n\n";
                                textArea.setText(text);
                            }
                        }
                    }
                }
            }            
        });
    }
    
    private boolean isInteger(String searchString){//Used to determine if Search field is index or genre/title.
        try
        {
            Integer.parseInt(searchString);
            return true;
        } catch (NumberFormatException ex)
          {
              return false;
          }
    }
}