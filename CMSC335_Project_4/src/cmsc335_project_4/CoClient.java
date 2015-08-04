//CoClient.java, 29 September 2014, Benjamin Knauth, Class Book for CMSC335 Project 3.
package cmsc335_project_4;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JProgressBar;

public class CoClient implements Runnable{//Creates Runnable object to be used by thread.
    private JTree tree;
    private JProgressBar coBar;
    private JButton button;
    private Library library;
    private BlockingQueue queue = null;
    
    public CoClient(){
    }
    
    public CoClient(JTree tree, JProgressBar coBar, JButton button, Library library, BlockingQueue queue){
        this.tree = tree;
        this.coBar = coBar;
        this.button = button;
        this.library = library;
        this.queue = queue;
    }
    
    @Override
    public void run(){
        try {//Places this thread in queue.
            queue.put(this);
        } catch (InterruptedException ex) {
            Logger.getLogger(CoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        button.setEnabled(false);
        for(int i = 1; i <= 100; i++){
            coBar.setValue(i);
            try{
                Thread.sleep(50);
            }catch(InterruptedException ex){            
            }
        }
        
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        String temp = selectedNode.toString();        
        int copies = Integer.parseInt(temp.substring(temp.length() - 1));
        temp = temp.substring(0, temp.length() - 2);
        if(copies > 0){//Modifies tree and library.
            copies = copies - 1;
            for(Author i : library.Authors.values()){
                for(Book j : i.Books.values()){
                    String libBook = j.getTitle();
                    if(libBook.equals(temp)){
                        j.setCopies(copies);
                    }
                }
                for(Journal j : i.Journals.values()){
                    String libJournal = "" + j.getIssueNumber();
                    if(libJournal.equals(temp)){
                        j.setCopies(copies);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Item Checked Out successfully!.");
        }
        else if(copies <= 0)
            JOptionPane.showMessageDialog(null, "All copies checked out, Check Out cancelled.");
        selectedNode.setUserObject(temp + " " + copies);
        model.nodeChanged(selectedNode);
        coBar.setValue(0);
        button.setEnabled(true);
        try {//Removes this thread from queue.
            queue.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(CoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Library getLibrary(){
        return library;
    }
}