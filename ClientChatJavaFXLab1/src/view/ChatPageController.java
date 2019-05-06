/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import commonservicejavafxlab1.Client;
import commonservicejavafxlab1.ClientInterface;
import commonservicejavafxlab1.ServerInterface;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
//import model.ServerService;

public class ChatPageController implements Initializable {

    @FXML
    private TextField messageTextField;
    @FXML
    private Label clientNameLabel;
    @FXML
    private VBox contentVBox;
//    @FXML
//    private ImageView clientImage;
    @FXML
    private Circle circleClient;

    static ChatPageController chat;

    String path;
    ClientServerImp clientServerImp;
    Registry register;
    //problem
    ServerInterface serverInterface;
    ClientInterface clientService;
    Client client;
    String clientName;

    public ChatPageController() {
        chat = this;
    }

    public void setData(String name, File file, ServerInterface serverInterface) {
        this.serverInterface = serverInterface;
        client = new Client();
        client.setName(name);
        clientName = name;
        //new = new Circle(150);
        //circle.setClip(circle);
        Image image = new Image(file.toURI().toString());
        circleClient.setFill(new ImagePattern(image));
        //clientImage.setImage(image);
        clientNameLabel.setText(client.getName());
        client.setImagePath(imageToByteArray(file));
        System.out.println(client.getName() + "\n" + client.getImagePath());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        client.setMessage(messageTextField.getText());
                        serverInterface.broadCast(client);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ChatPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    void setVBox(Client client) {
        System.out.println(client.getName() + " sent " + client.getMessage());
        System.out.println(clientName + "    " + client.getName());
        ImageView imageView = new ImageView();
        Platform.runLater(() -> {
            HBox hbox = new HBox();
            if (clientName.equals(client.getName())) {
                Label massageLabelRight = new Label(client.getMessage() + " : " + client.getName());
                Circle circle = new Circle(25, 25, 25);
                circle.setFill(new ImagePattern(byteArrayToImage(client.getImagePath())));
//                imageView.setImage(byteArrayToImage(client.getImagePath()));
//                imageView.setFitHeight(30);
//                imageView.setFitHeight(30);
//                imageView.setPickOnBounds(true);
//                imageView.setPreserveRatio(true);
                massageLabelRight.setStyle("-fx-font-size:18px;");
                massageLabelRight.setFont(new Font(24));
                massageLabelRight.setStyle("-fx-background-color: #D3D3D3;");
                hbox.setAlignment(Pos.BASELINE_RIGHT);
                hbox.getChildren().add(massageLabelRight);
                hbox.getChildren().add(circle);
                chat.contentVBox.getChildren().add(hbox);
            } else {
                Label massageLabelLeft = new Label(client.getName() + " : " + client.getMessage());
                Circle circle = new Circle(25, 25, 25);
                circle.setFill(new ImagePattern(byteArrayToImage(client.getImagePath())));
//                imageView.setImage(byteArrayToImage(client.getImagePath()));
//                imageView.setFitHeight(30);
//                imageView.setFitHeight(30);
//                imageView.setPickOnBounds(true);
//                imageView.setPreserveRatio(true);
                massageLabelLeft.setStyle("-fx-font-size:18px;");
                
                massageLabelLeft.setFont(new Font(24));
                massageLabelLeft.setStyle("-fx-background-color: #E4EAA2;");
                hbox.setAlignment(Pos.BASELINE_LEFT);
                hbox.getChildren().add(circle);
                hbox.getChildren().add(massageLabelLeft);
                chat.contentVBox.getChildren().add(hbox);
            }

        });
    }

    public byte[] imageToByteArray(File file) {
        byte[] data = null;
        try {
            BufferedImage bImage = ImageIO.read(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            data = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(ChatPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return data;
    }

    public Image byteArrayToImage(byte[] imageofBytes) {
        File file;
        Image image=null;
        
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageofBytes);
            BufferedImage bImage2 = ImageIO.read(bis);
            file=new File("image.jpg");
            ImageIO.write(bImage2, "jpg", file);
            image=new Image(file.toURI().toString());
            System.out.println("image created");
        } catch (IOException ex) {
            Logger.getLogger(ChatPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
}
