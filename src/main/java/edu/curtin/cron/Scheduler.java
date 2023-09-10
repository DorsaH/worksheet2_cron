package edu.curtin.cron;
import java.util.*;

/**
 * The scheduler keeps track of all the jobs, and runs each one at the appropriate time. (You need
 * to fill in the details!)
 */
public class Scheduler
{
    // TODO: ...
    private ArrayList<Job> jobs = new ArrayList<Job>();
    private Thread scheculThread;
    private Object mutex = new Object();
    private Thread schedulerThread;
    private boolean running = false;
    private int seconCounter = 0;

    public void addJob(Job newJob)
    {
        synchronized (mutex) {
            jobs.add(newJob);
        }
    }
    
    public void start()
    {
        running = true;
        schedulerThread = new Thread(() -> {
            
            try {
                while (running) {
                    synchronized (mutex) {
                        for (Job job : jobs) {

                            if (seconCounter % job.getDelay() == 0) {
                                new Thread(job::run, "schedulerThread!!!").start();
                            }
                        }
                    }
                    Thread.sleep(1000L);
                    seconCounter++;
                }
            } catch (InterruptedException e) {
                System.out.println("Job was interrupted");
            }
        });
        schedulerThread.start();
    }

    public void stop()
    {
        running = false;
        if (schedulerThread != null) {
            schedulerThread.interrupt();
            scheculThread = null;
        }else{
            // need to add try catch in the ui class when it call this thread stop
            throw new IllegalStateException("unexpected interupt in scheduler thread");
        }
    }

}
