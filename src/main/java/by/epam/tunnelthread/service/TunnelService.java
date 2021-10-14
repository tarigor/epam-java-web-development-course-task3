package by.epam.tunnelthread.service;

import by.epam.tunnelthread.entity.Train;
import by.epam.tunnelthread.entity.Tunnel;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TunnelService {
    private Tunnel tunnel;
    private ArrayList<Callable<Train>> callableArrayListOfTrains;

    public TunnelService(ArrayList<Callable<Train>> callableArrayListOfTrains) {
        this.callableArrayListOfTrains = callableArrayListOfTrains;
    }

    public void startTravelingViaTunnel(){


    }

    protected boolean checkIfTrainsInTunnelIsMax(){
        return false;
    }

}