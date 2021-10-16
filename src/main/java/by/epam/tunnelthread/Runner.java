package by.epam.tunnelthread;

import by.epam.tunnelthread.service.TunnelService;

public class Runner {
    public static void main(String[] args) {
        TunnelService tunnelService = new TunnelService();
        tunnelService.startThreads();
    }
}
