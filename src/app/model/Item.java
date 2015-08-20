package app.model;

/**
 * Created by User on 19.08.2015.
 */
public class Item {
    String name;
    String url;
    double timeToKill;
    double damage;
    double rateOfFile;

    public Item(String name, String url) {
        this.name = name;
        this.url = url;
        timeToKill=Math.random();
        damage=Math.random()*100;
        rateOfFile=Math.random();
    }

    @Override
    public String toString() {
        return name;
    }

}
