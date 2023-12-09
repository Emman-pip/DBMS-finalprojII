import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dates {
    public LinkedList<String> fillDates(String[][] data) {
        LinkedList<String> datesReserved = new LinkedList<String>();
        for (String[] i : data) {

            datesReserved.add(i[0]);
            datesReserved.add(i[1]);
            // for YYYY/MM/DD
            LinkedList<String> checkinData = new dates().regEx(i[0], "[0-9]+");
            LinkedList<String> departureData = new dates().regEx(i[1], "[0-9]+");
            // look if same month
            if (checkinData.get(2).equals(departureData.get(2))) {
                datesReserved.add(checkinData.get(2));
                continue;
            }
            for (int v = Integer.parseInt(checkinData.get(2)) + 1; v < Integer.parseInt(departureData.get(2)); v++) {
                if (v < 10) {
                    datesReserved.add(checkinData.get(0) + "-" + checkinData.get(1) + "-0" + v);
                    continue;
                }
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
            int start = matcher.start();
            int end = matcher.end();
            items.add(data.substring(start, end));
        }
        return items;
    }

    public static void main(String[][] args) {
        System.out.println("lol here");
        String[][] lol = { { "2023-12-20" } };
        System.out.println(new dates().fillDates(lol));
    }
}
