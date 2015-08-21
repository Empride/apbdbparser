package app.model;

import app.controllers.MainViewController;
import app.items.ItemEnum;

/**
 * Created by User on 19.08.2015.
 */
public interface Model extends Runnable {
    void getModelData(MainViewController mainViewController, ItemEnum enumName);

}
