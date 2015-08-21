package app.items;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 19.08.2015.
 */
public class Item {
    private static final String APBDB_ITEM_URL = "http://apbdb.com";
    private boolean isAlreadyParsed = false;
    private Map<String, Double> statMap = new LinkedHashMap<>();

    private String name;
    private String url;
    private ItemEnum itemType;

    public ItemEnum getItemType() {
        return itemType;
    }


    public Item(String name, String url,ItemEnum itemType) {
        this.name = name;
        this.itemType=itemType;
        this.url = APBDB_ITEM_URL + url;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setProrerty(String stat, String value) {
        Pair<String, Double> pair = moveMeasurementFromValueToKey(stat, value);
        statMap.put(pair.getKey(), pair.getValue());
    }


    public Pair<String, Double> moveMeasurementFromValueToKey(String key, String value) {
        value = value.replaceAll(",", "");
        if (value.contains("("))
            value = value.substring(value.indexOf("(") + 1, value.length() - 1);
        if (key.equalsIgnoreCase("Drive Type"))
        {
            return new Pair<String, Double>("Drive Type", (double) (value.equalsIgnoreCase("FWD")?0.20:value.equalsIgnoreCase("AWD")?0.22:0.02));
        }
        String measurementCut = value.replaceAll("[ 0-9\\.]*", "");
        String valueCut = value.replaceAll("[a-zA-Z/%]", "");
        double valueFormatted = 0;
        if (measurementCut.trim().length() > 0 && !measurementCut.equals("%"))
            key = key + ", " + measurementCut;
        valueFormatted = Double.parseDouble(valueCut);
        return new Pair<String, Double>(key, valueFormatted);
    }

    public boolean isAlreadyParsed() {
        return isAlreadyParsed;
    }

    public void setIsAlreadyParsed(boolean isAlreadyParsed) {
        this.isAlreadyParsed = isAlreadyParsed;
    }

    public Map<String, Double> getStatMap() {
        return statMap;
    }
}
