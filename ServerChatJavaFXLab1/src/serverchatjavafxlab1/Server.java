
package serverchatjavafxlab1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
   
    public static void main(String[] args) {
        try {
            ServerImp serverImp=new ServerImp();
            Registry registry = LocateRegistry.createRegistry(1500);
            registry.rebind("chat", serverImp);
            System.out.println("Server  Started");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
       
    }
    
}
