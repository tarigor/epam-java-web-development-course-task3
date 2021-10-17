package by.epam.tunnelthread.service;

import by.epam.tunnelthread.entity.Train;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

/**
 * Class provides service method for handling with tunnel entity
 *
 * @author Igor Taren
 */
public class TunnelService {
    private final Logger logger = Logger.getLogger(TunnelService.class);
    private Semaphore semaphore;
    private CopyOnWriteArrayList<Train> trainArrayList;
    private ThreadGroup tunnel;

    public TunnelService(ThreadGroup tunnel, CopyOnWriteArrayList<Train> trainArrayList, int amountOfTrainsInTunnelSimultaneously) {
        this.tunnel = tunnel;
        this.trainArrayList = trainArrayList;
        this.semaphore = new Semaphore(amountOfTrainsInTunnelSimultaneously);
    }

    /**
     * Method starts the threads simulating the trains traveling.
     */
    public void startTrainsTravelingInTunnel() {

        //the delay makes for having time to open visualvm application
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean firstThread = false;
        while (trainArrayList.size() != 0) {
            int randomTrain = new Random().nextInt(trainArrayList.size());
            logger.info(String.format("The %s has been get randomly from array of trains with a direction -> %s",
                    trainArrayList.get(randomTrain).getTrainName(),
                    trainArrayList.get(randomTrain).getTrainDirection().toString()));
            try {
                //check for direction of the current thread in order to make priority for thread with the same direction
                if (checkTrainDirectionFromTrainGroup(randomTrain, trainArrayList) || !firstThread) {
                    //is required for first thread creation
                    firstThread = true;
                    //simulate a different time of trains arriving into tunnel
                    generateRandomTimeDelay();
                    try {
                        Thread thread = new Thread(tunnel, new TrainThread(tunnel.getName(), trainArrayList.get(randomTrain), semaphore));
                        logger.info(String.format("Thread created for the %s", trainArrayList.get(randomTrain).getTrainName()));
                        thread.start();
                        trainArrayList.remove(randomTrain);
                    } catch (Exception e) {
                        e.toString();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method provides checking of the direction of the current thread in order to make priority of selection for thread with the same direction
     *
     * @param randomTrain    entity for train supposing to be next for entrance into tunnel
     * @param trainArrayList list of the Trains
     * @return result of the train direction for train currently traveling in the tunnel and train supposing to be next for traveling
     */
    private boolean checkTrainDirectionFromTrainGroup(int randomTrain, CopyOnWriteArrayList<Train> trainArrayList) {
        boolean ifDirectionDoNotExistInArray;
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread thread : threadSet) {
            if (thread.getThreadGroup().getName().contains(tunnel.getName())) {
                //check when all threads belong to certain direction will be passed out and remained only threads with other direction type
                ifDirectionDoNotExistInArray = checkIfDirectionDoNotExistInArray(thread, trainArrayList);
                if (thread.getName().contains(trainArrayList.get(randomTrain).getTrainDirection().toString()) || ifDirectionDoNotExistInArray) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method provides check when all threads belong to certain direction will be passed out and remained only threads with other direction type.
     *
     * @param thread         current thread
     * @param trainArrayList list of the remained Trains
     * @return result of check. If true - all trains with same direction have been passed and remained only trains with another type of direction
     */
    private boolean checkIfDirectionDoNotExistInArray(Thread thread, CopyOnWriteArrayList<Train> trainArrayList) {
        int amount = 0;
        for (Train train : trainArrayList) {
            //get direction type from the thread name
            String[] threadNameSeparated = thread.getName().split(" ");
            if (threadNameSeparated.length > 4) {
                if (train.getTrainDirection().toString().contains(threadNameSeparated[5])) {
                    amount++;
                }
            } else {
                amount++;
            }
        }
        if (amount == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method provides the time delay thereby simulates the different time of the trains arriving at the tunnel.
     */
    private void generateRandomTimeDelay() {
        try {
            LocalTime timeBefore = LocalTime.now();
            Thread.sleep(new Random().nextInt(3000));
            LocalTime timeAfter = LocalTime.now();
            logger.info(String.format("The next train has came in %d seconds at the entrance of the tunnel", timeAfter.toSecondOfDay() - timeBefore.toSecondOfDay()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}