package app.model;

import java.util.ArrayList;

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

        add(new MergedData("Health Damage", item1.getHealthDamage(),item2.getHealthDamage(), item1.getHealthDamage()-item2.getHealthDamage()));
        add(new MergedData("Fire Interval", item1.getFireInterval(),item2.getFireInterval(), item1.getFireInterval()-item2.getFireInterval()));
        add(new MergedData("Time to kill", item1.timeToKill, item2.timeToKill, item1.timeToKill - item2.timeToKill));
    }

    private String format(double d) {
        return String.format("%.2f", d);
    }

    public class MergedData {
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

        public MergedData(String firstColumn, Double secondColumn, Double thirdColumn, Double forthColumn) {
            this.firstColumn = firstColumn;
            this.secondColumn = format(secondColumn);
            this.thirdColumn = format(thirdColumn);
            this.forthColumn = format(forthColumn);
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
