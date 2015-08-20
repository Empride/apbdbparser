package app.model;

import java.util.ArrayList;

/**
 * Created by User on 20.08.2015.
 */
public class ItemsMerger extends ArrayList<ItemsMerger.MergedData> {
    Item item1;
    Item item2;

    public ItemsMerger(Item item1, Item item2) {
        this.item1 = item1;
        this.item2 = item2;
        merge();
    }

    private void merge() {
        add(new MergedData("Damage", "100", "150", "50"));
        add(new MergedData("Fire Interval", "0.1", "0.15", "0.05"));
        add(new MergedData("Time to kill", format(item1.timeToKill), format(item2.timeToKill), format(item1.timeToKill - item2.timeToKill)));
    }

    private String format(double d)
    {
        return String.format("%.2f",d);
    }

    public class MergedData
    {
    final String firstColumn;
    final String secondColumn;
    final String thirdColumn;
    final String forthColumn;

        public MergedData(String firstColumn, String secondColumn, String thirdColumn, String forthColumn) {
            this.firstColumn = firstColumn;
            this.secondColumn = secondColumn;
            this.thirdColumn = thirdColumn;
            this.forthColumn = forthColumn;
        }

        public String getFirstColumn() {
            return firstColumn;
        }

        public String getSecondColumn() {
            return secondColumn;
        }

        public String getThirdColumn() {
            return thirdColumn;
        }

        public String getForthColumn() {
            return forthColumn;
        }
    }




}
