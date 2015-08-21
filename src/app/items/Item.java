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

    public Item(String name, String url, ItemEnum itemType) {
        this.name = name;
        this.itemType = itemType;
        this.url = APBDB_ITEM_URL + url;
    }

    public ItemEnum getItemType() {
        return itemType;
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

    //moving measurements from value to key
    public Pair<String, Double> moveMeasurementFromValueToKey(String key, String value) {
        value = value.replaceAll(",", "");
        //Explosives max damage to usual damage
        switch (key) {
            case "Max Health Damage":
                key = "Health Damage";
                break;
            case "Max Stamina Damage":
                key = "Stamina Damage";
                break;
            case "Max Hard Damage":
                key = "Hard Damage";
                break;
        }
        if (value.contains("("))
            value = value.substring(value.indexOf("(") + 1, value.length() - 1);
        //Car Drive type to double
        if (key.equalsIgnoreCase("Drive Type")) {
            return new Pair<String, Double>("Drive Type", (double) (value.equalsIgnoreCase("FWD") ? 0.20 : value.equalsIgnoreCase("AWD") ? 0.22 : 0.02));
        }

        String measurementCut = value.replaceAll("[x 0-9\\.]*", "");
        String valueCut = value.replaceAll("[^x 0-9\\.]*", "");

        double valueFormatted;
        if (measurementCut.trim().length() > 0 && !measurementCut.equals("%"))
            key = key + ", " + measurementCut;
        //Shotgun damage=pellets*damagePerOnePellet
        if (valueCut.contains("x")) {
            valueFormatted = Double.parseDouble(valueCut.split("x")[0]) * Double.parseDouble(valueCut.split("x")[1]);
        } else
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
