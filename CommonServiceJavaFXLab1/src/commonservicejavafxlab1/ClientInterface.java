package commonservicejavafxlab1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    void receive(Client client) throws RemoteException;
}