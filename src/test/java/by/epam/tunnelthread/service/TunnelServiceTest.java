package by.epam.tunnelthread.service;

import by.epam.tunnelthread.entity.Train;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

class TunnelServiceTest {

    @Test
    void testStartTrainsTravelingInTunnel() {
        //GIVEN
        TrainService trainService = new TrainService();
        CopyOnWriteArrayList<Train> trainArrayList = trainService.getRandomizeCreatedTrainList();

        TunnelService tunnelServiceTunnelA = new TunnelService(new ThreadGroup("tunnelA"), trainArrayList, 2);
        TunnelService tunnelServiceTunnelB = new TunnelService(new ThreadGroup("tunnelB"), trainArrayList, 2);
        //WHEN
        Thread thread1 = new Thread(tunnelServiceTunnelA::startTrainsTravelingInTunnel);
        thread1.start();
        Thread thread2 = new Thread(tunnelServiceTunnelB::startTrainsTravelingInTunnel);
        thread2.start();

        int count1 = 0;
        int count2 = 0;
        while (thread1.isAlive()) {
            for (Thread thread : Thread.getAllStackTraces().keySet()) {
                String threadName = thread.getName();
                String checkedThreadName = "";
                boolean notChecked = true;
                if (threadName.contentEquals(checkedThreadName)) {
                    notChecked = false;
                }
                if (thread.getName().contains("tunnelA") & notChecked) {
                    count1++;
                    checkedThreadName = thread.getName();
                }
            }
        }
        System.out.printf("t1->%d t2->%d", count1, count2);
        //THEN
    }

}