package app.controller;

import app.model.Item;
import app.model.Model;
import app.view.BaseView;
import app.view.CompareView;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by User on 19.08.2015.
 */
public class Controller {
    BaseView view;
    Model model;
    CompareView compareView;

    public void setCompareView(CompareView compareView) {
        this.compareView = compareView;
    }

    public void setView(BaseView view) {
        this.view = view;
        view.create();
    }

    public void compareSelected(Item item1, Item item2) {
        if (item1 == null || item2 == null)
            return;
        if (compareView.stage != null)
            compareView.stage.close();
            compareView.start(new Stage());
            compareView.populateCompareWindow(item1,item2);

    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List loadData() {
        return model.getData();
    }
}
