//JTreeGUI.java, 29 September 2014, Benjamin Knauth, Class Book for CMSC335 Project 3.
package cmsc335_project_4;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class JTreeGUI extends JFrame{//Creates GUI.
    private Library library;
    private JTree tree;
    private JProgressBar coBar;
    private JProgressBar reBar;
    private LinkedList<MainClient> clientList = new LinkedList<MainClient>();
    private BlockingQueue queue = new LinkedBlockingQueue();
    
    public JTreeGUI(Library newLibrary){//Creates JTree usin Library object, and outputs in GUI.
        this.library = newLibrary;
        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(2,1));
        
        DefaultMutableTreeNode libraryNode = new DefaultMutableTreeNode("Library");
        DefaultMutableTreeNode authorsNode = new DefaultMutableTreeNode("Authors");        
        
        Vector<DefaultMutableTreeNode> vAuthorsNode = new Vector<DefaultMutableTreeNode>();
        for(Author i : library.Authors.values()){
            vAuthorsNode.add(new DefaultMutableTreeNode(i.getName()));
            
            DefaultMutableTreeNode books = new DefaultMutableTreeNode("Books");            
            for(Book j : i.Books.values()){
                books.add(new DefaultMutableTreeNode(j.getTitle() + " " + j.getCopies()));
            }
            vAuthorsNode.lastElement().add(books);
            
            DefaultMutableTreeNode journals = new DefaultMutableTreeNode("Journals");            
            for(Journal j : i.Journals.values()){
                journals.add(new DefaultMutableTreeNode(j.getIssueNumber() + " " + j.getCopies()));
            }
            vAuthorsNode.lastElement().add(journals);
        }
        
        for(DefaultMutableTreeNode i : vAuthorsNode){
            authorsNode.add(i);
        }
        libraryNode.add(authorsNode);
        tree = new JTree(libraryNode);
        
        JScrollPane treePane = new JScrollPane(tree);
        treePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(treePane);
        
        Panel buttons = new Panel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
        final JButton coButton = new JButton("Check Out");        
        final JButton reButton = new JButton("Return");
        coBar = new JProgressBar(0,100);
        coBar.setValue(0);
        coBar.setStringPainted(true);
        reBar = new JProgressBar(0,100);
        reBar.setValue(0);
        reBar.setStringPainted(true);
        buttons.add(coButton);
        buttons.add(coBar);        
        buttons.add(reButton);
        buttons.add(reBar);
        final JButton pauseCO = new JButton("PauseCO");
        final JButton cancelCO = new JButton("CancelCO");
        final JButton pauseRE = new JButton("PauseRE");
        final JButton cancelRE = new JButton("CancelCO");
        buttons.add(pauseCO);
        buttons.add(cancelCO);
        buttons.add(pauseRE);
        buttons.add(cancelRE);
        mainPanel.add(buttons);
        add(mainPanel);
        
        for(int i = 0; i < 100; i++){//Creates and holds 100 client objects so 100 users can use the server.
            clientList.add(new MainClient(new CoClient(tree, coBar, coButton, library, queue), new ReClient(tree, reBar, reButton, library, queue)));
        }
        
        int count = 0;
        MainClient tempClient = clientList.getFirst();
        
        while(tempClient.getcoThread().isAlive() || tempClient.getreThread().isAlive()){//Search for inactive MainClient object.
            count++;
            tempClient = clientList.get(count);
        }
        
        final MainClient finalClient = tempClient;       
        
        coButton.addActionListener(new ActionListener(){//Listener for the Check Out button.            
            @Override
            public void actionPerformed(ActionEvent e){
                final Thread coStart = new Thread(finalClient.getcoThread());//Threads are nonreusable.  
                coStart.start();                
                    //THE FOLLOWING COMMENT CODE SHOWS THAT THE LIBRARY GETS UPDATED.  UNFORTUNATELY, IT ALSO STOPS THE PROGRESS BAR FOR
                    //UNKNOWN REASONS, SO I KEPT IT AS OPTIONAL CODE ONLY TO SHOW THAT THE LIBRARY IS INDEED BEING UPDATED, NOT JUST
                    //THE JTREE.
//                try {
//                    coStart.join();
//                } catch (InterruptedException ex) {
//                }
//                
//                for(Author i : library.Authors.values()){
//                    for(Book j : i.Books.values()){
//                        System.out.println("Author: " + i.getName() + "  Book: " + j.getTitle() + "  copies: " + j.getCopies());
//                    }
//                }
                
                pauseCO.addActionListener(new ActionListener(){//Pauses Check Out.
                    boolean suspended;
                    @Override
                    public void actionPerformed(ActionEvent e){                        
                        if(suspended == false){
                            coStart.suspend();
                            suspended = true;
                        }
                        else{
                            coStart.resume();
                            suspended = false;
                        }
                    }
                });
                cancelCO.addActionListener(new ActionListener(){//Cancels Check Out.
                    @Override
                    public void actionPerformed(ActionEvent e){                        
                            coStart.stop();
                            coBar.setValue(0);
                            coButton.setEnabled(true);
                    }
                });                
            }
        });
        reButton.addActionListener(new ActionListener(){//Listener for Return Button.
            @Override
            public void actionPerformed(ActionEvent e){                
                final Thread reStart = new Thread(finalClient.getreThread());
                reStart.start();
                //THE FOLLOWING COMMENT CODE SHOWS THAT THE LIBRARY GETS UPDATED.  UNFORTUNATELY, IT ALSO STOPS THE PROGRESS BAR FOR
                //UNKNOWN REASONS, SO I KEPT IT AS OPTIONAL CODE ONLY TO SHOW THAT THE LIBRARY IS INDEED BEING UPDATED, NOT JUST
                //THE JTREE.
//                try {
//                    reStart.join();
//                } catch (InterruptedException ex) {
//                }
//                
//                for(Author i : library.Authors.values()){
//                    for(Book j : i.Books.values()){
//                        System.out.println("Author: " + i.getName() + "  Book: " + j.getTitle() + "  copies: " + j.getCopies());
//                    }
//                }               
                
                pauseRE.addActionListener(new ActionListener(){//Pauses Return.
                    boolean suspended;
                    @Override
                    public void actionPerformed(ActionEvent e){                        
                        if(suspended == false){
                            reStart.suspend();
                            suspended = true;
                        }
                        else{
                            reStart.resume();
                            suspended = false;
                        }
                    }
                });
                cancelRE.addActionListener(new ActionListener(){//Cancels return.             
                    @Override
                    public void actionPerformed(ActionEvent e){                        
                            reStart.stop();
                            reBar.setValue(0);
                            reButton.setEnabled(true);
                    }
                });
            }
        });    
    }
}