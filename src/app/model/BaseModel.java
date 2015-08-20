package app.model;

import app.MainViewController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by User on 20.08.2015.
 */
public class BaseModel implements Model, Runnable {
    private static final String APBDB_CATALOG_URL = "http://apbdb.com/items/?cat=Weapon&p=%d";
    private static final String APBDB_ITEM_URL = "http://apbdb.com";
    private static List<Item> modelData=new ArrayList<>();
    private static MainViewController mainViewController;
    private static BaseModel instance=new BaseModel();

    private BaseModel(){};

    public static BaseModel getInstance() {
        return instance;
    }

    public void setMainViewController(MainViewController mainViewController) {
        BaseModel.mainViewController = mainViewController;
    }

    @Override
    public void getData() {
       Thread thread=new Thread(this);
        thread.start();
    }




    public void run() {
        DBParser parser=new DBParser(APBDB_CATALOG_URL,APBDB_ITEM_URL);
        modelData=parser.parse();

    }
}
