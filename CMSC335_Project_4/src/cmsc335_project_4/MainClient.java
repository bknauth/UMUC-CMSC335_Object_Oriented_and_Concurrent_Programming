package cmsc335_project_4;

import java.util.concurrent.BlockingQueue;

public class MainClient {
    private CoClient coClient = new CoClient();
    private ReClient reClient = new ReClient();
    private Runnable checkOut = coClient;
    private Runnable checkIn = checkOut;
    private Thread coThread;
    private Thread reThread;
    
    public MainClient(CoClient setCoClient, ReClient setReClient){
        this.checkOut = setCoClient;
        this.coThread = new Thread(checkOut);
        this.checkIn = setReClient;
        this.reThread = new Thread(checkIn);
    }
    
    public CoClient getCoClient(){
        return coClient;
    }
    
    public ReClient getReClient(){
        return reClient;
    }
    
    public Runnable getCheckOut(){
        return checkOut;
    }
    
    public Runnable getCheckIn(){
        return checkIn;
    }
    
    public Thread getcoThread(){
        return coThread;
    }
    
    public Thread getreThread(){
        return reThread;
    }
    
    public void setCoClient(CoClient newCoClient){
        this.coClient = newCoClient;
    }
    
    public void setReClient(ReClient newReClient){
        this.reClient = newReClient;
    }
    
    public void setCheckOut(CoClient newCheckOut){
        this.checkOut = newCheckOut;
    }
    
    public void setCheckIn(ReClient newCheckIn){
        this.checkIn = newCheckIn;
    }    
    //Setters for Threads are unnecessary.
}
