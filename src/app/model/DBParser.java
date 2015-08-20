package app.model;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;

/**
 * Created by User on 20.08.2015.
 */
public class DBParser{

    private String urlToDB;
    private String itemURL;
    boolean hasNext=true;

    public DBParser(String urlToDB, String itemURL) {

        this.urlToDB = urlToDB;
        this.itemURL = itemURL;
    }

    public List<Item> parse() {
        List<Item> modelData=new ArrayList<>();
        int i=1;
        while (hasNext)
            try {
                Document document= getDocument(urlToDB, i++);
                hasNext = document.getElementsByClass("arrow").text().contains("Next");
                Element elementsByTag = document.getElementsByTag("tbody").get(0);
                Elements rows = elementsByTag.getElementsByTag("tr");
                for (Element element : rows)
                {
                    modelData.add(new Item(element.getElementsByTag("a").get(1).text(), itemURL + element.getElementsByTag("a").get(0).attr("href")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return modelData;
    }



    private Document getDocument(String url, int page) throws IOException {
        String connectionURL = String.format(url,page);

        Connection connection = Jsoup.connect(connectionURL);
        connection.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
        connection.referrer("http://www.google.com");
        return connection.get();
    }
}
