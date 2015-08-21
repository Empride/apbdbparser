package app.controllers;

import app.items.ItemEnum;
import app.model.BaseModel;
import app.items.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController extends Application implements Initializable{

    public CompareViewController compareViewController = new CompareViewController();
    public List<Item> itemList = new ArrayList<>();
    private static BaseModel model;
    private Stage mainStage;
    private ObservableList<Item> leftItemList = FXCollections.observableArrayList();
    private ObservableList<Item> rightItemList = FXCollections.observableArrayList();
    @FXML
    public ProgressBar progressBar;
    @FXML
    public Label label1;
    @FXML
    private Button loadDataButton;
    @FXML
    private Button compareButton;
    @FXML
    private ChoiceBox<ItemEnum> choiceBox;
    @FXML
    private ListView<Item> leftPane;
    @FXML
    private ListView<Item> rightPane;
    @FXML
    private TextField leftFilter;
    @FXML
    private TextField rightFilter;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.setItems(FXCollections.observableArrayList(ItemEnum.values()));
        choiceBox.getSelectionModel().select(0);
        loadDataButton.setDefaultButton(true);
        compareButton.setDisable(true);
    }



    public static void main(String... args) {
        model=new BaseModel();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

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


    public void eventOnLoadDataClick() {
        loadDataButton.setDisable(true);
        model.getModelData(this,choiceBox.getSelectionModel().getSelectedItem());
    }

    public void updateItemList(List<Item> modelData) {
        itemList.clear();
        itemList.addAll(modelData);
        updateData();

    }

    private void updateData() {
        loadDataButton.setDisable(false);
        loadDataButton.setDefaultButton(false);
        compareButton.setDefaultButton(true);
        compareButton.setDisable(false);
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

