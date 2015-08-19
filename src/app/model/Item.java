package app.model;

/**
 * Created by User on 19.08.2015.
 */
public class Item {
    String name;
    String url;
    double timeToKill;

    public Item(String name, String url) {
        this.name = name;
        this.url = url;
        timeToKill=Math.random();
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public double getTimeToKill() {
        return timeToKill;
    }
}
