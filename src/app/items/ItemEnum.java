package app.items;

/**
 * Created by User on 21.08.2015.
 */
public enum ItemEnum {
    WEAPON("Weapon","http://apbdb.com/items/?cat=Weapon&p=%d"),
    VEHICLE("Vehicle","http://apbdb.com/items/?cat=Vehicle&p=%d");

    private String label;
    private String url;

    ItemEnum(String label, String url) {
        this.label = label;
        this.url=url;
    }

    public String toString() {
        return label;
    }

    public String getUrl() {
        return url;
    }
}
