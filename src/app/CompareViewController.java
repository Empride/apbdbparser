package app;

import app.items.Item;
import app.items.ItemsMerger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by User on 20.08.2015.
 */
public class CompareViewController {

    public static Stage compareStage;
    private Item itemToCompare1;
    private Item itemToCompare2;
    @FXML
    private TableColumn<ItemsMerger.MergedData, String> columnText;
    @FXML
    private TableColumn<ItemsMerger.MergedData, String> columnItem1;
    @FXML
    private TableColumn<ItemsMerger.MergedData, String> columnItem2;
    @FXML
    private TableColumn<ItemsMerger.MergedData, String> columnDiff;
    @FXML
    private TableView<ItemsMerger.MergedData> compareTable;

    public static void setCompareStage(Stage stage) {
        compareStage = stage;
    }

    public void populateCompareWindow() {
        List<ItemsMerger.MergedData> mergedItemsList= new ItemsMerger(itemToCompare1,itemToCompare2);
        ObservableList<ItemsMerger.MergedData> observableList = FXCollections.observableList(mergedItemsList);

        columnItem1.setText(itemToCompare1.toString());
        columnItem2.setText(itemToCompare2.toString());
        columnText.setCellValueFactory(new PropertyValueFactory<ItemsMerger.MergedData,String>("statNameColumn"));
        columnItem1.setCellValueFactory(new PropertyValueFactory<ItemsMerger.MergedData,String>("firstItemColumn"));
        columnItem2.setCellValueFactory(new PropertyValueFactory<ItemsMerger.MergedData, String>("secondItemColumn"));
        columnDiff.setCellValueFactory(new PropertyValueFactory<ItemsMerger.MergedData, String>("differenceColumn"));
        compareTable.setItems(observableList);
    }

    public void setItemToCompare1(Item itemToCompare1) {
        this.itemToCompare1 = itemToCompare1;
    }

    public void setItemToCompare2(Item itemToCompare2) {
        this.itemToCompare2 = itemToCompare2;
    }
}
