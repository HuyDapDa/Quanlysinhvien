package com.homework.quanlysinhvienfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    StackPane pane = new StackPane();
//    Scene scene = new Scene(pane,100,100)
            
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"),379,306);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        //379, 306
    }

    public static void setRoot(String fxml) throws IOException {
        
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    

}