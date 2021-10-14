package by.epam.tunnelthread.constant;

public enum TrainDirection {
    FROWARD, BACK;

    public static TrainDirection getRandomDirection() {
        return values()[((int) (Math.random() * values().length))];
    }
}
