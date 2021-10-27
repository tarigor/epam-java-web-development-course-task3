package by.epam.tunnelthread;

import by.epam.tunnelthread.entity.Train;
import by.epam.tunnelthread.service.PropertyFileService;
import by.epam.tunnelthread.service.TrainService;
import by.epam.tunnelthread.service.TunnelService;

import java.util.concurrent.CopyOnWriteArrayList;

public class Runner {
    public static void main(String[] args) {

        int trainsAmountInTunnel = Integer.parseInt(
                PropertyFileService.getInstance()
                        .readFromPropertyFile("settings.properties")
                        .getProperty("trains.amount.in.tunnel"));
        int totalTrainsAmount = Integer.parseInt(
                PropertyFileService.getInstance()
                        .readFromPropertyFile("settings.properties")
                        .getProperty("total.trains.amount"));

        TrainService trainService = new TrainService();
        CopyOnWriteArrayList<Train> trainArrayList = trainService.getRandomizeCreatedTrainList(totalTrainsAmount);

        TunnelService tunnelServiceTunnelA = new TunnelService(new ThreadGroup("tunnelA"), trainArrayList, trainsAmountInTunnel);
        TunnelService tunnelServiceTunnelB = new TunnelService(new ThreadGroup("tunnelB"), trainArrayList, trainsAmountInTunnel);

        new Thread(tunnelServiceTunnelA::startTrainsTravelingInTunnel).start();
        new Thread(tunnelServiceTunnelB::startTrainsTravelingInTunnel).start();
    }
}
