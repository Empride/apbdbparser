package app.view;

import app.controller.Controller;
import app.model.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseView extends Application{
    public static Controller controller;
    public List<Item> itemList;
    public ListView<Item> leftPane;
    public ListView<Item> rightPane;
    public TextField leftFilter;
    public TextField rightFilter;
    public Stage compareStage;





    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("ApbDB weapon compare");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void setController(Controller controller) {
        BaseView.controller = controller;
    }

    public void eventLeftFilter() {
        if (itemList == null || itemList.isEmpty())
            return;
        String searchString = leftFilter.getText();
        List<Item> filteredList = filterList(searchString);
        leftPane.setItems(FXCollections.observableList(filteredList));
    }


    public void eventRightFilter() {
        if (itemList == null || itemList.isEmpty())
            return;
        String searchString = rightFilter.getText();
        List<Item> filteredList = filterList(searchString);
        rightPane.setItems(FXCollections.observableList(filteredList));
    }

    private List<Item> filterList(String searchString) {
        List<Item> filteredList = new ArrayList<Item>();
        for (Item item : itemList) {
            if (item.toString().toLowerCase().contains(searchString.toLowerCase()))
                filteredList.add(item);
        }
        return filteredList;
    }

    public void eventOnCompareClick() {
        controller.compareSelected(leftPane.getFocusModel().getFocusedItem(), rightPane.getFocusModel().getFocusedItem());
    }




    public void eventLoadData() {
        itemList = controller.loadData();
        rightPane.setItems(FXCollections.observableList(itemList));
        leftPane.setItems(FXCollections.observableList(itemList));
        eventLeftFilter();
        eventRightFilter();
    }



    public void create() {
        launch();
    }
}

