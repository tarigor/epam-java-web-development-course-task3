package by.epam.tunnelthread.entity;

import by.epam.tunnelthread.constant.TrainDirection;

import java.util.Objects;

/**
 * Entity of train
 *
 * @author Igor Taren
 */
public class Train {
    private String trainName;
    private int trainNumber;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return trainName.equals(train.trainName) &&
                trainDirection == train.trainDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainName, trainDirection);
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainName='" + trainName + '\'' +
                ", trainDirection=" + trainDirection +
                '}';
    }
}
