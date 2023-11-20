import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dates {
    public LinkedList<String> fillDates(String[][] data) {
        // String[][] data = { { "2023-12-10", "2023-12-13" } };
        LinkedList<String> datesReserved = new LinkedList<String>();
        for (String[] i : data) {

            // for YYYY/MM/DD
            LinkedList<String> checkinData = new dates().regEx(i[0], "[0-9]+");
            LinkedList<String> departureData = new dates().regEx(i[1], "[0-9]+");
            // look if same month
            if (checkinData.get(2).equals(departureData.get(2))) {
                datesReserved.add(checkinData.get(2));
                continue;
            }
            datesReserved.add(i[0]);
            datesReserved.add(i[1]);
            for (int v = Integer.parseInt(checkinData.get(2)) + 1; v < Integer.parseInt(departureData.get(2)); v++) {
                datesReserved.add(checkinData.get(0) + "-" + checkinData.get(1) + "-" + v);
            }
        }
        return datesReserved;
    }

    public LinkedList<String> regEx(String data, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        LinkedList<String> items = new LinkedList<String>();
        while (matcher.find()) {
            // int start = matcher.start() + 2;
            int start = matcher.start();

            // int end = matcher.end() - 1;
            int end = matcher.end();
            items.add(data.substring(start, end));
            // System.out.println(start + " " + end);
        }
        return items;
    }
}
