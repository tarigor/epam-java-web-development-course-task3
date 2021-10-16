package by.epam.tunnelthread.service;

import by.epam.tunnelthread.constant.TrainDirection;
import by.epam.tunnelthread.entity.Train;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TrainService {
    private static final Logger logger = Logger.getLogger(TrainService.class);
    private static final int TRAIN_TRAVEL_TIME = 5;
    private Train train;
    private Runnable trainCallable;
    private ArrayList<Runnable> callableArrayListOfTrains;

    public TrainService() {
        callableArrayListOfTrains = new ArrayList<Runnable>();
    }

    public TrainService(Train train) {
        this.train = train;
        callableArrayListOfTrains = new ArrayList<Runnable>();
    }

    public ArrayList<Runnable> getCallableArrayListOfTrains() {
        return callableArrayListOfTrains;
    }

    public void addTrainCallable(Train train) {
        this.callableArrayListOfTrains.add(createTrain());
    }

    public Runnable createTrain() {
        return () -> {
            Train train = new Train(1);
            //Thread.currentThread().setName(train.getTrainName());
            logger.info(String.format("thread of %s started at %s", train.getTrainName(), LocalTime.now()));
            try {
                TimeUnit.SECONDS.sleep(TRAIN_TRAVEL_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    public ArrayList<Callable<Train>> getTrainsMovedByCertainDirection(ArrayList<Callable<Train>> callableArrayListOfTrains, TrainDirection trainDirection) {
        ArrayList<Callable<Train>> callableArrayListOfTrainsSortedByDirection = new ArrayList<>();
        for (Callable<Train> trainCallable : callableArrayListOfTrains) {
            try {
                if (trainCallable.call().getTrainDirection() == trainDirection) {
                    callableArrayListOfTrainsSortedByDirection.add(trainCallable);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return callableArrayListOfTrainsSortedByDirection;
    }
}
