package by.epam.tunnelthread.entity;

public class Tunnel {
    private String tunnelName;
    private int amountOfAllowedTrainsInTunnel;
    private int amountOfAllowedTrainsInTunnelInOneDirection;

    public Tunnel(String tunnelName, int amountOfAllowedTrainsInTunnel, int amountOfAllowedTrainsInTunnelInOneDirection) {
        this.tunnelName = tunnelName;
        this.amountOfAllowedTrainsInTunnel = amountOfAllowedTrainsInTunnel;
        this.amountOfAllowedTrainsInTunnelInOneDirection = amountOfAllowedTrainsInTunnelInOneDirection;
    }

    public String getTunnelName() {
        return tunnelName;
    }

    public void setTunnelName(String tunnelName) {
        this.tunnelName = tunnelName;
    }

    public int getAmountOfAllowedTrainsInTunnel() {
        return amountOfAllowedTrainsInTunnel;
    }

    public void setAmountOfAllowedTrainsInTunnel(int amountOfAllowedTrainsInTunnel) {
        this.amountOfAllowedTrainsInTunnel = amountOfAllowedTrainsInTunnel;
    }

    public int getAmountOfAllowedTrainsInTunnelInOneDirection() {
        return amountOfAllowedTrainsInTunnelInOneDirection;
    }

    public void setAmountOfAllowedTrainsInTunnelInOneDirection(int amountOfAllowedTrainsInTunnelInOneDirection) {
        this.amountOfAllowedTrainsInTunnelInOneDirection = amountOfAllowedTrainsInTunnelInOneDirection;
    }

    @Override
    public String toString() {
        return "Tunnel{" +
                "tunnelName='" + tunnelName + '\'' +
                ", amountOfAllowedTrainsInTunnel=" + amountOfAllowedTrainsInTunnel +
                ", amountOfAllowedTrainsInTunnelInOneDirection=" + amountOfAllowedTrainsInTunnelInOneDirection +
                '}';
    }
}
