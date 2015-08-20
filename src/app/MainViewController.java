package app;

import app.model.BaseModel;
import app.model.Item;
import app.model.Model;
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

public class MainViewController extends Application {
    private static Model model;
    private Stage mainStage;
    public List<Item> itemList = new ArrayList<>();
    public ListView<Item> leftPane;
    public ListView<Item> rightPane;
    public TextField leftFilter;
    public TextField rightFilter;
    public CompareViewController compareViewController =new CompareViewController();
    private ObservableList<Item> leftItemList = FXCollections.observableArrayList();
    private ObservableList<Item> rightItemList = FXCollections.observableArrayList();


    public static void main(String... args) {
        model = new BaseModel();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/mainWindow.fxml"));
        mainStage=primaryStage;
        primaryStage.resizableProperty().setValue(false);
        primaryStage.setTitle("ApbDB weapon compare");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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

    public void eventOnCompareClick() throws IOException {
        Item item1=leftPane.getFocusModel().getFocusedItem();
        Item item2=rightPane.getFocusModel().getFocusedItem();
        if (item1 == null || item2 == null)
            return;
        if (compareViewController.compareStage != null)
            compareViewController.compareStage.close();
        createCompareStage(item1,item2);
    }


    public void eventLoadData() {
        itemList = loadData();
        leftItemList.addAll(itemList);
        rightItemList.addAll(itemList);
        rightPane.setItems(rightItemList);
        leftPane.setItems(leftItemList);
        eventLeftFilter();
        eventRightFilter();
    }

    private List loadData() {
        return model.getData();
    }
    public void createCompareStage(Item item1, Item item2) throws IOException {
        compareViewController.start(mainStage);
        compareViewController.setItemToCompare1(item1);
        compareViewController.setItemToCompare2(item2);
    }

}

