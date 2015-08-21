package app.items;

import app.model.DBParser;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by User on 20.08.2015.
 */
public class ItemsMerger extends ArrayList<ItemsMerger.MergedData> {
    Item item1;
    Item item2;

    DBParser dbParser = DBParser.getInstance();

    public ItemsMerger(Item item1, Item item2) {
        if (!item1.isAlreadyParsed())
        dbParser.parseWeaponData(item1);
        if (!item2.isAlreadyParsed())
        dbParser.parseWeaponData(item2);
        this.item1 = item1;
        this.item2 = item2;
        merge();
    }

    private void merge() {


        for (Map.Entry<String,Double> item1stat : item1.getStatMap().entrySet())
        {
            if (item2.getStatMap().containsKey(item1stat.getKey())) {
                double item2Value=item2.getStatMap().get(item1stat.getKey());
                add(new MergedData(item1stat.getKey(),item1stat.getValue(),item2Value,item1stat.getValue()-item2Value));
            }
        }


    }

    private String format(double d) {
        return String.format("%.2f", d);
    }

    public class MergedData {
        final String statNameColumn;
        final String firstItemColumn;
        final String secondItemColumn;
        final String differenceColumn;



        public MergedData(String statNameColumn, Double firstItemColumn, Double secondItemColumn, Double differenceColumn) {
            this.statNameColumn = statNameColumn;
            this.firstItemColumn = format(firstItemColumn);
            this.secondItemColumn = format(secondItemColumn);
            this.differenceColumn = format(differenceColumn);
        }

        public String getStatNameColumn() {
            return statNameColumn;
        }

        public String getFirstItemColumn() {
            return firstItemColumn;
        }

        public String getSecondItemColumn() {
            return secondItemColumn;
        }

        public String getDifferenceColumn() {
            return differenceColumn;
        }
    }


}
