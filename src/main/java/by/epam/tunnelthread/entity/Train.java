package by.epam.tunnelthread.entity;

import by.epam.tunnelthread.constant.TrainDirection;

public class Train {
    private String trainName;
    private int trainNumber = 1;
    private TrainDirection trainDirection;

    public Train(int trainNumber) {
        this.trainName = String.format("Train-%d", trainNumber);
        this.trainDirection = TrainDirection.getRandomDirection();
        this.trainNumber = trainNumber;
    }

    private void incrementTrainNumber() {
        this.trainNumber++;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public TrainDirection getTrainDirection() {
        return trainDirection;
    }

    public void setTrainDirection(TrainDirection trainDirection) {
        this.trainDirection = trainDirection;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainName='" + trainName + '\'' +
                ", trainDirection=" + trainDirection +
                '}';
    }
}
