/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import commonservicejavafxlab1.ClientInterface;
import commonservicejavafxlab1.ServerInterface;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoginPageController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private Button imageChooser;
    @FXML
    private Button login;
    @FXML
    private ImageView userImageView;
    File file = new File("client.png");
    ClientServerImp clientServerImp;
    Registry register;
    ServerInterface serverInterface;
    ClientInterface clientService;

    ChatPageController chatPageController;
    FXMLLoader loader;
    Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setOnAction((ActionEvent event) -> {
            try {
                register = LocateRegistry.getRegistry("127.0.0.1", 1500);
                serverInterface = (ServerInterface) register.lookup("chat");
                clientService = new ClientServerImp();
                serverInterface.register(clientService);
                
                loader = new FXMLLoader(getClass().getResource("ChatFXML.fxml"));
                root = loader.load();
                chatPageController = loader.getController();
                
                chatPageController.setData(userName.getText(), file, serverInterface);
                
                Scene scene = new Scene(root, 600, 800);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                Stage currentStage = (Stage) login.getScene().getWindow();
                currentStage.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NotBoundException ex) {
                Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        imageChooser.setOnAction((ActionEvent event) -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choose Image");
            file = chooser.showOpenDialog(new Stage());
            if (file == null) {
                //String imagepath = file.getPath();
                //System.out.println("file:" + imagepath);
                file = new File("client.png");
                
            } else {
                //path = file.toURI().toString();
                Image image = new Image(file.toURI().toString());
                userImageView.setImage(image);
            }
        });
    }
}
