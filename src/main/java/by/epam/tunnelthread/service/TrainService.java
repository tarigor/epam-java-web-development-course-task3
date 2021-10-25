package by.epam.tunnelthread.service;

import by.epam.tunnelthread.entity.Train;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class provides service method for handling with train entity
 *
 * @author Igor Taren
 */
public class TrainService {

    private CopyOnWriteArrayList<Train> trainArrayList;

    public CopyOnWriteArrayList<Train> getTrainArrayList() {
        return trainArrayList;
    }

    public TrainService() {
    }

    /**
     * Method provides a generation of train list.
     *
     * @return ArrayList contains list of trains.
     */
    public CopyOnWriteArrayList<Train> getRandomizeCreatedTrainList(int totalTrainsAmount) {
        trainArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < totalTrainsAmount; i++) {
            trainArrayList.add(new Train(i));
        }
        return trainArrayList;
    }
}
