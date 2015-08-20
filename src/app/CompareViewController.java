package app;

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

/**
 * Created by User on 20.08.2015.
 */
public class CompareViewController extends Application {

    public ListView<String> weapon1List;
    public ListView<String> weapon2List;
    public ListView<String> diffList;
    public ListView<String> textList;
    public Stage compareStage;
    public Item itemToCompare1;
    public Item itemToCompare2;
    public Scene scene;


    public void start(Stage primaryStage) throws IOException {

        Parent rootCompare = FXMLLoader.load(getClass().getResource("view/compareWindow.fxml"));
        compareStage = new Stage();
        compareStage.resizableProperty().setValue(false);
        compareStage.setTitle("ApbDB weapon compare");
        scene=new Scene(rootCompare);
        compareStage.setScene(scene);
        compareStage.show();
        populateCompareWindow();
    }

    public void populateCompareWindow() {
        List<String> firstList = new ArrayList<>();
        firstList.add(itemToCompare1.getName());
        firstList.add(String.format("%.2f", itemToCompare1.getTimeToKill()));
        ObservableList<String> observableList = FXCollections.observableList(firstList);
        weapon1List.setItems(observableList);
    }

    public void setItemToCompare1(Item itemToCompare1) {
        this.itemToCompare1 = itemToCompare1;
    }

    public void setItemToCompare2(Item itemToCompare2) {
        this.itemToCompare2 = itemToCompare2;
    }
}
