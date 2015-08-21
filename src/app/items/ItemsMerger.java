package app.items;

import app.model.DBParser;

import java.text.DecimalFormat;
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
            dbParser.parseItemData(item1);
        if (!item2.isAlreadyParsed())
            dbParser.parseItemData(item2);
        this.item1 = item1;
        this.item2 = item2;
        merge();
    }

    private void merge() {


        for (Map.Entry<String, Double> item1stat : item1.getStatMap().entrySet()) {
            if (item2.getStatMap().containsKey(item1stat.getKey())) {
                double item2Value = item2.getStatMap().get(item1stat.getKey());
                double item1Value = item1stat.getValue();
                if (item1stat.getKey().equals("Drive Type"))
                    add(new MergedData("Drive Type",
                            ((item1Value == 0.20) ? "FWD" : (item1Value == 0.22) ? "AWD" : "RWD"),
                            ((item2Value == 0.20) ? "FWD" : (item2Value == 0.22) ? "AWD" : "RWD"),
                            ""));
                else
                    add(new MergedData(item1stat.getKey(), item1Value, item2Value, item1Value - item2Value));
            }
        }
    }

    private String format(double d) {
        DecimalFormat df = new DecimalFormat("####.##");
        return df.format(d);
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

        public MergedData(String statNameColumn, String firstItemColumn, String secondItemColumn, String differenceColumn) {
            this.statNameColumn = statNameColumn;
            this.firstItemColumn = firstItemColumn;
            this.secondItemColumn = secondItemColumn;
            this.differenceColumn = differenceColumn;
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
