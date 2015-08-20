package app;

import app.model.BaseModel;
import app.model.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainViewController extends Application {
    private static BaseModel model;
    public CompareViewController compareViewController = new CompareViewController();

    private Stage mainStage;
    public ObservableList<Item> itemList = FXCollections.observableArrayList();
    @FXML
    private ListView<Item> leftPane;
    @FXML
    private ListView<Item> rightPane;
    @FXML
    private TextField leftFilter;
    @FXML
    private TextField rightFilter;
    private ObservableList<Item> leftItemList = FXCollections.observableArrayList();
    private ObservableList<Item> rightItemList = FXCollections.observableArrayList();


    public static void main(String... args) {
        model = BaseModel.getInstance();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        model.setMainViewController(this);
        Parent root = FXMLLoader.load(getClass().getResource("view/mainWindow.fxml"));
        mainStage = primaryStage;
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
        Item item1 = leftPane.getFocusModel().getFocusedItem();
        Item item2 = rightPane.getFocusModel().getFocusedItem();
        if (item1 == null || item2 == null)
            return;
        if (compareViewController.compareStage != null)
            compareViewController.compareStage.close();
        createCompareStage(item1, item2);
    }


    public void eventLoadData() {
        model.getData();
    }

    public void updateItemList(List<Item> modelData) {

        itemList.clear();
        itemList.addAll(modelData);
        updateData();
    }

    private void updateData() {
        leftItemList.addAll(itemList);
        rightItemList.addAll(itemList);
        rightPane.setItems(rightItemList);
        leftPane.setItems(leftItemList);
        eventLeftFilter();
        eventRightFilter();
    }

    public void createCompareStage(Item item1, Item item2) throws IOException {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/compareWindow.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        if (CompareViewController.compareStage != null)
            CompareViewController.compareStage.close();
        Stage compareStage = new Stage();
        compareStage.setTitle("Weapons compare");
        compareStage.resizableProperty().setValue(false);
        compareStage.initOwner(mainStage);
        Scene scene = new Scene(page);
        compareStage.setScene(scene);
        CompareViewController.setCompareStage(compareStage);

        CompareViewController controller = loader.getController();
        controller.setItemToCompare1(item1);
        controller.setItemToCompare2(item2);
        controller.populateCompareWindow();
        compareStage.showAndWait();


    }

}

