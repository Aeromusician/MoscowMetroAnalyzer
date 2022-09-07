import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        HtmlParser.metroHTMLParser();
        FileFinder.startSearch("C:\\skillbox\\java_basics\\FilesAndNetwork\\DataCollector\\data");
        System.out.println(DataClass.mapLineNumberToStations);
        JsonUtils.CreateJsonFile();
    }
}
