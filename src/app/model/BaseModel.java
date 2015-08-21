package app.model;

import app.MainViewController;
import app.items.Item;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.08.2015.
 */
public class BaseModel implements Model, Runnable {
    private static final String APBDB_CATALOG_URL = "http://apbdb.com/items/?cat=Weapon&p=%d";

    private static List<Item> modelData = new ArrayList<>();
    private DBParser dbParser = DBParser.getInstance();
    private MainViewController mainViewController;


    public void getModelData(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        modelData = dbParser.parseWeaponList(APBDB_CATALOG_URL, mainViewController);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mainViewController.label1.setText(String.format("All data loaded"));
                mainViewController.updateItemList(modelData);
            }
        });
    }
}
