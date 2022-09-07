import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HtmlParser {
    private static final String URL = "https://skillbox-java.github.io";

    public static void metroHTMLParser() {
        try {
            Document metro = Jsoup.connect(URL).get();
            Elements lines = metro.select("span.js-metro-line");
            lines.forEach(element -> {
                DataClass.metroLines.add(new Line(element.text(), element.attr("data-line")));
            });
            Elements stations = metro.select("div.js-metro-stations[data-line]");
            stations.forEach(element -> {
                String lineNumber = element.attr("data-line");
                Elements stationNameElements = element.select("p.single-station > span.name");
                ArrayList<Station> lineStations = new ArrayList<>();
                stationNameElements.forEach(st -> {
                    Station station = new Station(st.text());
                    station.setLineNumber(lineNumber);
                    lineStations.add(station);
                });
                ArrayList<String> stationsForSecondMap = new ArrayList<>();
                stationNameElements.forEach(st -> {
                    stationsForSecondMap.add(st.text());
                });
                DataClass.mapLineNumberToStations.put(lineNumber, lineStations);
                DataClass.mapJson.put(lineNumber, stationsForSecondMap);
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
