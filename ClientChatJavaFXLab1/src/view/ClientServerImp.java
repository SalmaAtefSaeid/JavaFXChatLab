/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import commonservicejavafxlab1.Client;
import commonservicejavafxlab1.ClientInterface;
import commonservicejavafxlab1.ServerInterface;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class ClientServerImp extends UnicastRemoteObject implements ClientInterface {

    //ChatPageController chatPageController = new ChatPageController();
//    ChatPageController chatPageController;
//    FXMLLoader loader;
//    Parent root;
    public ClientServerImp() throws RemoteException {
        //serverInterface.register(this);

    }

    @Override
    public void receive(Client client) throws RemoteException {

//            loader = new FXMLLoader(getClass().getResource("ChatFXML.fxml"));
//            root = loader.load();
//            //System.out.println(root);
//            chatPageController = loader.getController();
        ChatPageController.chat.setVBox(client);

    }
    //        try {
    ////            //System.out.println(client.getName() + " sent " + client.getMessage());
    ////
    ////            loader = new FXMLLoader(getClass().getResource("ChatFXML.fxml"));
    //            root = loader.load();
    //            chatPageController = loader.getController();
    //            chatPageController.setVBox(client);
    //        } catch (IOException ex) {
    //            Logger.getLogger(ClientServerImp.class.getName()).log(Level.SEVERE, null, ex);
    //        }
}
