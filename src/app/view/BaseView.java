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
    public List<Item> itemList=new ArrayList<>();
    private ObservableList<Item> leftItemList=FXCollections.observableArrayList();;
    private ObservableList<Item> rightItemList=FXCollections.observableArrayList();;
    public ListView<Item> leftPane;
    public ListView<Item> rightPane;
    public TextField leftFilter;
    public TextField rightFilter;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("ApbDB weapon compare");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void setController(Controller controller) {
        BaseView.controller = controller;
    }

    public void eventLeftFilter() {

        String searchString = leftFilter.getText();
        leftItemList.clear();
        leftItemList.addAll(filterList(searchString));


    }


    public void eventRightFilter() {

        String searchString = rightFilter.getText();
        rightItemList.clear();
        rightItemList.addAll(filterList(searchString));
    }

    private List<Item> filterList(String searchString) {
        List<Item> filteredList = new ArrayList<>();
        if (searchString.isEmpty())
            return itemList;
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
        leftItemList.addAll(itemList);
        rightItemList.addAll(itemList);
        rightPane.setItems(rightItemList);
        leftPane.setItems(leftItemList);
        eventLeftFilter();
        eventRightFilter();
    }



    public void create() {
        launch();
    }
}

