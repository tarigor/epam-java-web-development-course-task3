package by.epam.tunnelthread;

import by.epam.tunnelthread.entity.Train;
import by.epam.tunnelthread.entity.Tunnel;
import by.epam.tunnelthread.service.TrainService;
import by.epam.tunnelthread.service.TunnelService;

import java.util.concurrent.CopyOnWriteArrayList;

public class Runner {
    public static void main(String[] args) {

        Tunnel tunnelB = new Tunnel("tunnelB");
        Tunnel tunnelA = new Tunnel("tunnelA");

        TrainService trainService = new TrainService();
        CopyOnWriteArrayList<Train> trainArrayList = trainService.getRandomizeCreatedTrainList();

        TunnelService tunnelServiceTunnelA = new TunnelService(new ThreadGroup("tunnelA"), trainArrayList, 2);
        TunnelService tunnelServiceTunnelB = new TunnelService(new ThreadGroup("tunnelB"), trainArrayList, 2);

        new Thread(tunnelServiceTunnelA::startTrainsTravelingInTunnel).start();

        new Thread(tunnelServiceTunnelB::startTrainsTravelingInTunnel).start();

    }
}
