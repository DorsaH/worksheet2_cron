package edu.curtin.cron;
import java.io.*;
import java.time.LocalTime;

/**
 * Represents a job (command) to be run, and performs the execution. (You need to fill in the 
 * details!)
 */
public class Job implements Runnable
{
    private String command;
    private  int delay;
    private  Logger logger;
    
    // tmp

    public Job(String cmd, int delay, Logger logger) {
        this.command = cmd;
        this.delay = delay;
        this.logger = logger;
    }
    @Override    
    public void run() {
        // Assume 'command' is a string containing the command the
        // execute. Then we initially run it as follows:
        try {
            Process proc = Runtime.getRuntime().exec(command);


        //////////////////////////////////////////////////////////////////// 
            // to pirnt the time 
             LocalTime currentTime = LocalTime.now();
        
        // Extract hours, minutes, and seconds
        int hours = currentTime.getHour();
        int minutes = currentTime.getMinute();
        int seconds = currentTime.getSecond();
        
        // Display the time in HH:MM:SS format
        String timeFormat = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        System.out.println("Current Time: " + timeFormat);

        /////////////////////////////////////////////////////////////


            // Arrange to capture the command's output, line by line.
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
            new InputStreamReader(proc.getInputStream()));
            String line = reader.readLine();
            while(line != null)
            {
                //handles thread interuppts
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Job was interrupted");
                    return;
                }
                output.append(line);
                output.append('\n');
                line = reader.readLine();
            }
            // We've now reached the end of the command's output, which
            // generally means the command has finished.
            System.out.println(command + ": " + output.toString());
        }
        catch (IOException e) {
            System.out.println("IO exception occured!");
        }
    }

    public int getDelay(){
        return delay;
    }
}
