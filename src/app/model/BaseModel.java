package app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19.08.2015.
 */
public class BaseModel implements Model {
    private ArrayList<Item> items;

    public List getData()
    {
        items=new ArrayList<>();
        items.add(new Item("OCA",null));
        items.add(new Item("NTEC",null));
        return items;
    }

}
