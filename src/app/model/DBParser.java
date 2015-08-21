package app.model;

import app.MainViewController;
import javafx.application.Platform;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21.08.2015.
 */
public class DBParser {
    private static boolean isDataCached = false;
    private static DBParser parser = new DBParser();
    private boolean hasNext = true;
    private List<Item> modelData = new ArrayList<>();

    private DBParser() {
    }

    public static DBParser getInstance() {
        return parser;
    }

    public List<Item> parseWeaponList(String apbdbCatalogUrl, final MainViewController mainViewController) {
        if (isDataCached)
            return modelData;
        int i = 0;
        while (hasNext)
            try {
                Document document = getDocument(apbdbCatalogUrl, ++i);
                hasNext = document.getElementsByClass("arrow").text().contains("Next");
                Element elementsByTag = document.getElementsByTag("tbody").get(0);
                Elements rows = elementsByTag.getElementsByTag("tr");
                for (Element element : rows) {
                    modelData.add(new Item(element.getElementsByTag("a").get(1).text(), element.getElementsByTag("a").get(0).attr("href")));
                }
                final double finalI = i;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        mainViewController.label1.setText(String.format("Processing page %.0f", finalI));
                        mainViewController.progressBar.setProgress(finalI / 6);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        isDataCached = true;
        return modelData;
    }

    public void parseWeaponData(Item item) {
        try {
            Document document = getDocument(item.getUrl());
            for (int i = 1; i <= 6; i++) {
                Element elementsByTag = document.getElementsByTag("tbody").get(i);
                Elements rows = elementsByTag.getElementsByTag("tr");
                for (Element element : rows) {
                    if (element.getElementsByTag("td").size() == 2)
                        item.setProrerty(element.getElementsByTag("td").get(0).text(), element.getElementsByTag("td").get(1).text());
                }
                item.setIsAlreadyParsed(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Document getDocument(String url, Integer... args) throws IOException {
        String connectionURL;
        if (args.length == 1) {
            connectionURL = String.format(url, args[0]);
        } else {
            connectionURL = url;
        }
        Connection connection = Jsoup.connect(connectionURL);
        connection.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
        connection.referrer("http://www.google.com");
        return connection.get();
    }
}
