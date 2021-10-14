package by.epam.tunnelthread.service;

import by.epam.tunnelthread.constant.TrainDirection;
import by.epam.tunnelthread.entity.Train;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TrainService {
    private static final int TRAIN_TRAVEL_TIME = 5;
    private Train train;
    private Callable<Train> trainCallable;
    private ArrayList<Callable<Train>> callableArrayListOfTrains;

    public TrainService(Train train) {
        this.train = train;
        callableArrayListOfTrains = new ArrayList<Callable<Train>>();
    }

    public ArrayList<Callable<Train>> getCallableArrayListOfTrains() {
        return callableArrayListOfTrains;
    }

    public void addTrainCallable(Train train) {
        this.callableArrayListOfTrains.add(createTrainCallable(train));
    }

    private Callable<Train> createTrainCallable(Train train) {
        return () -> {
            Thread.currentThread().setName(train.getTrainName());
            try {
                TimeUnit.SECONDS.sleep(TRAIN_TRAVEL_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return train;
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
