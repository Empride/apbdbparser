package app.model;

import app.controllers.MainApp;
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
    private MainApp mainApp;
    private Map<String, List<Item>> dataCache = new HashMap<>();

    /**
     * Starting new thread, or resumed old to upload data from DB
     * @param mainApp FX controller instance, whose method will be called with uploaded data param
     * @param enumName items enum
     */
    public void requestDataUpdate(MainApp mainApp, ItemEnum enumName) {
        choosenItemType =enumName;
        this.mainApp = mainApp;
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
                dataLoaded.addAll(dbParser.parseItemList(choosenItemType, mainApp));
                dataCache.put(choosenItemType.toString(), dataLoaded);
            }
            Platform.runLater(new Runnable() {
                                  @Override
                                  public void run() {
                                      mainApp.label1.setText("All data loaded");
                                      mainApp.updateItemList(dataCache.get(choosenItemType.toString()));
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
