package by.epam.tunnelthread.service;

import by.epam.tunnelthread.entity.Train;
import by.epam.tunnelthread.entity.Tunnel;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TunnelService {
    private final Logger logger = Logger.getLogger(TunnelService.class);
    private static final int TRAIN_TRAVEL_TIME = 5;
    private Tunnel tunnel;
    private ArrayList<Callable<Train>> callableArrayListOfTrains;
    private ExecutorService executorService;
    private TrainService trainService;
    private DateFormat dateFormat;

    public TunnelService() {
    }

    public TunnelService(ArrayList<Callable<Train>> callableArrayListOfTrains) {
        this.callableArrayListOfTrains = callableArrayListOfTrains;
        this.trainService = new TrainService();
        this.dateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public void startTravelingViaTunnel() {


    }

    protected boolean checkIfTrainsInTunnelIsMax() {
        return false;
    }

    public synchronized void startThreads() {
        executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 20; i++) {
            Train train = new Train(i);
            try {
                LocalTime timeBefore = LocalTime.now();
                logger.info("Train came after");
                Thread.sleep(new Random().nextInt(10000));
                LocalTime timeAfter = LocalTime.now();
                logger.info(String.format("%d seconds", timeAfter.toSecondOfDay() - timeBefore.toSecondOfDay()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(() -> {
                Thread.currentThread().setName(train.getTrainName());
                logger.info(String.format("Current thread -> %s",Thread.currentThread().getName()));
                logger.info(String.format("thread of %s started at %s with direction %s", Thread.currentThread().getName(), LocalTime.now(), train.getTrainDirection().toString()));
                int timeBeforeTunnelEnter = LocalTime.now().toSecondOfDay();
                try {
                    TimeUnit.SECONDS.sleep(TRAIN_TRAVEL_TIME);
                    logger.info(String.format("Train %s has passed the tunnel in %d seconds",train.getTrainName(), (LocalTime.now().toSecondOfDay() - timeBeforeTunnelEnter)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}