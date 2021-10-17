package by.epam.tunnelthread.entity;

/**
 * Entity of tunnel
 *
 * @author Igor Taren
 */
public class Tunnel {
    private String tunnelName;
    private ThreadGroup threadGroup;

    public Tunnel(String tunnelName) {
        this.tunnelName = tunnelName;
    }

    public String getTunnelName() {
        return tunnelName;
    }

    public ThreadGroup getThreadGroup() {
        this.threadGroup = new ThreadGroup(tunnelName);
        return threadGroup;
    }

    @Override
    public String toString() {
        return "Tunnel{" +
                "tunnelName='" + tunnelName + '\'' +
                '}';
    }
}
