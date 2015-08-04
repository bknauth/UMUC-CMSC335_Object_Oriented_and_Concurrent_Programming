//CoClient.java, 29 September 2014, Benjamin Knauth, Class Book for CMSC335 Project 3.
package cmsc335_project_3;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JProgressBar;

public class CoClient implements Runnable{//Creates Runnable object to be used by thread.
    private JTree tree;
    private JProgressBar coBar;
    private JButton button;
    
    public CoClient(JTree tree, JProgressBar coBar, JButton button){
        this.tree = tree;
        this.coBar = coBar;
        this.button = button;
    }
    
    @Override
    public void run(){
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
        if(temp.length() >= 13 && !temp.substring(temp.length() - 13).equals("(Checked Out)"))
            selectedNode.setUserObject(temp + " (Checked Out)");
        else if(temp.length() < 13)
            selectedNode.setUserObject(temp + " (Checked Out)");
        model.nodeChanged(selectedNode);
        coBar.setValue(0);
        button.setEnabled(true);
    }    
}