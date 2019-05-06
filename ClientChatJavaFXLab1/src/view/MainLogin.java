package view;

import commonservicejavafxlab1.Client;
import commonservicejavafxlab1.ServerInterface;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainLogin extends Application {

    public MainLogin() throws RemoteException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws RemoteException {
        launch(args);
        new MainLogin();
    }

    public static void setVBox(Client client) {
        try {
            //Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainLogin.class.getResource("ChatFXML.fxml"));
            Parent root = loader.load();
            ChatPageController controller = loader.getController();
            controller.setVBox(client);

        } catch (IOException ex) {
            Logger.getLogger(MainLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
