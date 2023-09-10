package edu.curtin.cron;
import java.io.*;

import javax.management.monitor.Monitor;

/**
 * The logger is in charge of writing output to 'cron.log'. It does this in its own thread, but 
 * assumes that other threads will call the setMessage() in order to provide messages to log. (You 
 * need to fill in the details!)
 */
public class Logger
{
    private String nextMessage;
    private Thread loggerThread;
    private Monitor loggerMonitor;
    
    public void setMessage(String newMessage) throws InterruptedException
    {   
        // TODO: ...
    }
    
    public void start()
    {
        // TODO: ...
    }
    
    public void stop()
    {
        // TODO: ...
    }
}
