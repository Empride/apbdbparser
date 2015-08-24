package app.model;

import app.controllers.MainViewController;
import app.items.Item;
import app.items.ItemEnum;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 20.08.2015.
 */
public class BaseModel implements Model, Runnable {

    private Thread thread;
    private boolean suspendThread = true;
    private ItemEnum choosenItemType;
    private DBParser dbParser = DBParser.getInstance();
    private MainViewController mainViewController;
    private Map<String, List<Item>> dataCache = new HashMap<>();


    public void getModelData(MainViewController mainViewController, ItemEnum enumName) {
        choosenItemType =enumName;
        this.mainViewController = mainViewController;
        if (thread == null) {
            thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
        } else {
            {
                resumeThread();
            }
        }

    }

    private synchronized void resumeThread() {
        suspendThread = false;
        notify();
    }

    @Override
    public void run() {
        while (true) {
            suspendThread = true;
            if (!dataCache.containsKey(choosenItemType.toString())) {
                List<Item> dataLoaded= new ArrayList<>();
                dataLoaded.addAll(dbParser.parseItemList(choosenItemType, mainViewController));
                dataCache.put(choosenItemType.toString(), dataLoaded);
            }
            Platform.runLater(new Runnable() {
                                  @Override
                                  public void run() {
                                      mainViewController.label1.setText("All data loaded");
                                      mainViewController.updateItemList(dataCache.get(choosenItemType.toString()));
                                  }
                              }
            );
            try {
                synchronized (this) {
                    while (suspendThread)
                        wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
