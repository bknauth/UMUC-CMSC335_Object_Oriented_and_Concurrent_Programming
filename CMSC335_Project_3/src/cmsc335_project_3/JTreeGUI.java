//JTreeGUI.java, 29 September 2014, Benjamin Knauth, Class Book for CMSC335 Project 3.
package cmsc335_project_3;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class JTreeGUI extends JFrame{//Creates GUI.
    private JTree tree;
    private JProgressBar coBar;
    private JProgressBar reBar;    
    
    public JTreeGUI(final Library LIBRARY){//Creates JTree usin Library object, and outputs in GUI.
        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(2,1));
        
        DefaultMutableTreeNode libraryNode = new DefaultMutableTreeNode("Library");
        DefaultMutableTreeNode authorsNode = new DefaultMutableTreeNode("Authors");        
        
        Vector<DefaultMutableTreeNode> vAuthorsNode = new Vector<DefaultMutableTreeNode>();
        for(Author i : LIBRARY.Authors.values()){
            vAuthorsNode.add(new DefaultMutableTreeNode(i.getName()));
            
            DefaultMutableTreeNode books = new DefaultMutableTreeNode("Books");            
            for(Book j : i.Books.values()){
                books.add(new DefaultMutableTreeNode(j.getTitle()));
            }
            vAuthorsNode.lastElement().add(books);
            
            DefaultMutableTreeNode journals = new DefaultMutableTreeNode("Journals");            
            for(Journal j : i.Journals.values()){
                journals.add(new DefaultMutableTreeNode(j.getIssueNumber()));
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
        
        coButton.addActionListener(new ActionListener(){//Listener for the Check Out button.
            
            @Override
            public void actionPerformed(ActionEvent e){
                
                final Runnable coRun = new CoClient(tree, coBar, coButton);
                final Thread coThread = new Thread(coRun);
                coThread.start();                
                
                pauseCO.addActionListener(new ActionListener(){//Pauses Check Out.
                    boolean suspended;
                    @Override
                    public void actionPerformed(ActionEvent e){                        
                        if(suspended == false){
                            coThread.suspend();
                            suspended = true;
                        }
                        else{
                            coThread.resume();
                            suspended = false;
                        }
                    }
                });
                cancelCO.addActionListener(new ActionListener(){//Cancels Check Out.
                    @Override
                    public void actionPerformed(ActionEvent e){                        
                            coThread.stop();
                            coBar.setValue(0);
                            coButton.setEnabled(true);
                    }
                });
                
            }
        });
        reButton.addActionListener(new ActionListener(){//Listener for Return Button.
            @Override
            public void actionPerformed(ActionEvent e){
                Runnable reRun = new ReClient(tree, reBar, reButton);
                final Thread reThread = new Thread(reRun);                
                reThread.start();
                
                pauseRE.addActionListener(new ActionListener(){//Pauses Return.
                    boolean suspended;
                    @Override
                    public void actionPerformed(ActionEvent e){                        
                        if(suspended == false){
                            reThread.suspend();
                            suspended = true;
                        }
                        else{
                            reThread.resume();
                            suspended = false;
                        }
                    }
                });
                cancelRE.addActionListener(new ActionListener(){//Cancels return.             
                    @Override
                    public void actionPerformed(ActionEvent e){                        
                            reThread.stop();
                            reBar.setValue(0);
                            reButton.setEnabled(true);
                    }
                });
            }
        });        
    }
}