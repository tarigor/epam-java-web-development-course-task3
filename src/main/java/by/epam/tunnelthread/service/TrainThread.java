package by.epam.tunnelthread.service;

import by.epam.tunnelthread.entity.Train;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Class provides an entity of the train thread.
 */
public class TrainThread implements Runnable {
    private static final Logger logger = Logger.getLogger(TrainThread.class);
    private static final int TRAIN_TRAVEL_TIME = 5;
    private Train train;
    private Semaphore semaphore;
    private String tunnelName;

    public TrainThread(String tunnelName, Train train, Semaphore semaphore) {
        this.tunnelName = tunnelName;
        this.train = train;
        this.semaphore = semaphore;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Thread.currentThread().setName(String.format("%s - %s in direction %s",
                tunnelName,
                train.getTrainName(),
                train.getTrainDirection().toString()));
        logger.info(String.format("%s has entered into tunnel at %s in direction %s - %s",
                train.getTrainName(),
                LocalTime.now(),
                train.getTrainDirection().toString(),
                tunnelName));
        try {
            semaphore.acquire();
            TimeUnit.SECONDS.sleep(TRAIN_TRAVEL_TIME);
            logger.info(String.format("The train %s has been passed at %s", Thread.currentThread().getName(), LocalTime.now().toString()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
}
