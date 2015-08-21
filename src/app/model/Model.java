package app.model;

import app.MainViewController;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.Future;

/**
 * Created by User on 19.08.2015.
 */
public interface Model extends Runnable {
    void getModelData(MainViewController mainViewController);

}
