package app.model;

import app.controllers.MainApp;
import app.items.ItemEnum;

/**
 * Created by User on 19.08.2015.
 */
public interface Model extends Runnable {
    void requestDataUpdate(MainApp mainApp, ItemEnum enumName);

}
