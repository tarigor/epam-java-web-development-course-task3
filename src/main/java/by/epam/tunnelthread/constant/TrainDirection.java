package by.epam.tunnelthread.constant;

public enum TrainDirection {
    FORWARD, BACK;

    public static TrainDirection getRandomDirection() {
        return values()[((int) (Math.random() * values().length))];
    }
}
