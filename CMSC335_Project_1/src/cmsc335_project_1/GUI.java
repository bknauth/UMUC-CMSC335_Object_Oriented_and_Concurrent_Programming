//GUI.java, 31 August 2014, Benjamin Knauth, Class GUI for CMSC335 Project 1.
package cmsc335_project_1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{    
    
    public GUI(final String libraryString, final Library library){//Creates GUI to display data.
        final JTextArea textArea = new JTextArea(50, 50);
        //textArea.setText(library);        
        JScrollPane textScroller = new JScrollPane(textArea);
        
        final JTextArea searchBar = new JTextArea(1,10);
        searchBar.setToolTipText("Enter index, title, or genre, then click Search");
        JButton search = new JButton("Search");
        JButton fullList = new JButton("Full List");
        
        JPanel topPanel = new JPanel();
        topPanel.add(searchBar);
        topPanel.add(search);
        topPanel.add(fullList);        
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(textScroller, BorderLayout.SOUTH);
        
        this.setContentPane(mainPanel);
        this.setTitle("Library Management System (LIMS)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        
        fullList.addActionListener(new ActionListener() {//Full List button.
            @Override
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                textArea.setText(libraryString);
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
                    
                    for(Author i : library.Authors){
                        for(Book j : i.Books){
                            if(j.getIndex() == searchInt){
                                textArea.setText(j.toString());
                            }
                        }
                    }
                }
                else{//Search field is genre or title.
                    String text = "";
                    
                    for(Author i : library.Authors){
                        for(Book j : i.Books){
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
