package app.view;/**
 * Created by User on 20.08.2015.
 */

import app.controller.Controller;
import app.model.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompareView extends Application {

    public static Controller controller;
    public ListView<String> weapon1List;
    public ListView<String> weapon2List;
    public ListView<String> diffList;
    public ListView<String> textList;
    public Stage stage;

    public static void setController(Controller controller) {
        CompareView.controller = controller;
    }


    public void populateCompareWindow(Item item1, Item item2) {
        List<String> firstList = new ArrayList<>();
        firstList.add(item1.getName());
        firstList.add(String.format("%.2f", item1.getTimeToKill()));
        ObservableList<String> observableList = FXCollections.observableList(firstList);
        weapon1List.setItems(observableList);
    }

    @Override
    public void start(Stage primaryStage){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("compareWindow.fxml"));
        Parent rootCompare = null;
        try {
            rootCompare = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage = new Stage();
        stage=primaryStage;
        primaryStage.setTitle("ApbDB weapon compare");
        primaryStage.setScene(new Scene(rootCompare));
        primaryStage.show();
    }
}
