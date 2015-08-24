package app.model;

import app.controllers.MainApp;
import app.items.Item;
import app.items.ItemEnum;
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

    private static final DBParser INSTANCE = new DBParser();
    private List<Item> modelData = new ArrayList<>();

    private DBParser() {
    }

    public static DBParser getInstance() {
        return INSTANCE;
    }

    public List<Item> parseItemList(ItemEnum itemEnum, final MainApp mainApp) {
        modelData.clear();
        boolean hasNext = true;
        mainApp.progressBar.setProgress(0);
        int i = 0;
        while (hasNext)
            try {
                Document document = getDocument(itemEnum.getUrl(), ++i);
                //looks up for "Next" arrow
                hasNext = document.getElementsByClass("arrow").text().contains("Next");
                Element elementsByTag = document.getElementsByTag("tbody").get(0);
                Elements rows = elementsByTag.getElementsByTag("tr");
                for (Element element : rows) {
                    modelData.add(new Item(element.getElementsByTag("a").get(1).text(), element.getElementsByTag("a").get(0).attr("href"),itemEnum));
                }
                final double finalI = i;
                //updating main window progress visuals
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        mainApp.label1.setText(String.format("Processing page %.0f", finalI));
                        mainApp.progressBar.setProgress(finalI / 6);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        return modelData;
    }

    public void parseItemData(Item item) {
        try {
            Document document = getDocument(item.getUrl());
            for (int i = 1; i <  document.getElementsByTag("tbody").size(); i++) {
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
