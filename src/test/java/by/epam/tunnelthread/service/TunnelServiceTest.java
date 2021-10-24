package by.epam.tunnelthread.service;

import by.epam.tunnelthread.entity.Train;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

class TunnelServiceTest {

    @Test
    void testStartTrainsTravelingInTunnel() {
        //GIVEN
        int amountOfTrainsInTunnelASimultaneously = 2;
        int amountOfTrainsInTunnelBSimultaneously = 4;

        TrainService trainService = new TrainService();
        CopyOnWriteArrayList<Train> trainArrayList = trainService.getRandomizeCreatedTrainList();

        TunnelService tunnelServiceTunnelA = new TunnelService(new ThreadGroup("tunnelA"), trainArrayList, amountOfTrainsInTunnelASimultaneously);
        TunnelService tunnelServiceTunnelB = new TunnelService(new ThreadGroup("tunnelB"), trainArrayList, amountOfTrainsInTunnelBSimultaneously);
        //WHEN
        Thread thread1 = new Thread(tunnelServiceTunnelA::startTrainsTravelingInTunnel);
        thread1.start();
        Thread thread2 = new Thread(tunnelServiceTunnelB::startTrainsTravelingInTunnel);
        thread2.start();

        boolean amountMoreThanTwo = false;
        while (thread1.isAlive() && thread2.isAlive()) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int amountOfTrainsInTunnelAAtOnce = Thread.getAllStackTraces().keySet().stream()
                    .filter(n -> n.getName().contains("tunnelA"))
                    .filter(t -> t.getState().toString().contains("TIMED_WAITING"))
                    .collect(Collectors.toSet()).size();
            int amountOfTrainsInTunnelBAtOnce = Thread.getAllStackTraces().keySet().stream()
                    .filter(n -> n.getName().contains("tunnelB"))
                    .filter(t -> t.getState().toString().contains("TIMED_WAITING"))
                    .collect(Collectors.toSet()).size();

            if (amountOfTrainsInTunnelAAtOnce > amountOfTrainsInTunnelASimultaneously || amountOfTrainsInTunnelBAtOnce > amountOfTrainsInTunnelBSimultaneously) {
                amountMoreThanTwo = true;
            }

        }
        Assert.assertFalse("The amount of trains simultaneously in a single tunnel is exceeded an allowed amount", amountMoreThanTwo);
    }

}