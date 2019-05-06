package commonservicejavafxlab1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    void register(ClientInterface clientInterface) throws RemoteException;

    void unregister(ClientInterface clientInterface) throws RemoteException;

    void broadCast(Client client) throws RemoteException;
    
    //void brodCastChatbot(String message) throws RemoteException;
}
