
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class database {
    String url = "jdbc:mysql://localhost:3306/reservationSystem";
    String pass = "108996eE@emman";
    String username = "root";

    public LinkedList<ArrayList<Object>> customQueries(String qr) throws Exception {
        new flatlaf();
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        ArrayList<Object> data = new ArrayList<Object>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(qr);
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();

        while (rs.next()) {
            ArrayList<Object> record = new ArrayList<Object>();
            for (int i = 1; i < columnCount + 1; i++) {
                record.add(rs.getObject(i));
            }
            data.add(record);
        }
        LinkedList<ArrayList<Object>> dataList = new LinkedList<ArrayList<Object>>();

        for (int i = 0; i < data.size(); i++) {
            ArrayList subArr = (ArrayList) data.get(i);
            ArrayList<Object> recordVector = new ArrayList<Object>();
            for (int v = 0; v < subArr.size(); v++) {
                recordVector.add(subArr.get(v));
            }
            dataList.add(recordVector);
        }
        con.close();
        return dataList;
    }

    public int queryWithUsername(String username2) throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        String qr = "SELECT * FROM user_accounts WHERE username = '" + username2 + "';";
        ResultSet rs = st.executeQuery(qr);
        int output = 0;
        try {
            if (rs.next()) {
                output = rs.getInt(1);
            }
        } catch (Exception err) {
            System.out.println("ERROR");
        }
        con.close();
        return output;
    }

    public LinkedList<LinkedList<String>> queries(String tableName) throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();

        String qr = "SELECT * FROM " + tableName + ";";
        ResultSet rs = st.executeQuery(qr);

        LinkedList<LinkedList<String>> output = new LinkedList<LinkedList<String>>();

        if (tableName == "ClientInfo") {
            while (rs.next()) {
                LinkedList<String> record = new LinkedList<>();

                record.add(String.valueOf(rs.getInt(1)));
                record.add(String.valueOf(rs.getString(2)));
                record.add(String.valueOf(rs.getString(3)));
                record.add(String.valueOf(rs.getInt(4)));
                record.add(String.valueOf(rs.getString(5)));
                record.add(String.valueOf(rs.getString(6)));
                record.add(String.valueOf(rs.getString(7)));
                record.add(String.valueOf(rs.getString(8)));
                record.add(String.valueOf(rs.getString(9)));
                record.add(String.valueOf(rs.getString(10)));
                record.add(String.valueOf(rs.getString(11)));
                output.add(record);
            }
        } else if (tableName == "Packages") {
            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(rs.getString(2));
                System.out.print(rs.getString(3));
                System.out.println(rs.getInt(4));
            }
        } else if (tableName == "Reservations") {
            while (rs.next()) {
                LinkedList<String> reservations = new LinkedList<String>();

                reservations.add(String.valueOf(rs.getInt(1)));
                reservations.add(String.valueOf(rs.getString(2)));
                reservations.add(String.valueOf(rs.getInt(3)));
                reservations.add(String.valueOf(rs.getDate(4)));
                reservations.add(String.valueOf(rs.getDate(5)));
                reservations.add(String.valueOf(rs.getInt(6)));

                output.add(reservations);
            }

        } else if (tableName == "user_accounts") {
            while (rs.next()) {
                LinkedList<String> credentials = new LinkedList<String>();

                String userID = Integer.toString(rs.getInt(1));
                credentials.add(userID);

                credentials.add(rs.getString(2));
                credentials.add(rs.getString(3));
                output.add((credentials));
                ;
            }
        } else {
            System.out.println("ERROR");
        }
        // System.out.println(output);
        con.close();
        return output;

    }

    // create a class that searches the user_account based on attributes and not the
    public int searchAccountID(String user) throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();

        String qr = "SELECT accountID FROM user_accounts WHERE username='" + user + "'";

        ResultSet rs = st.executeQuery(qr);
        int ID = 0;
        while (rs.next()) {
            ID = rs.getInt(1);
        }
        con.close();
        return ID;
    }

    public void insertUserAccount(String user, String password) throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        String qr2 = "INSERT INTO user_accounts(username, password) VALUES ('" + user + "', '" + password + "');";
        // QUERY TO GET THE ID
        st.executeUpdate(qr2);
        con.close();
    }

    // primary key
    public void insertPersonalRecords(
            String user,
            String name,
            String gender,
            String age,
            String email,
            String cp,
            String landline,
            String address,
            String nationality,
            String reason)
            throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        int accountID = searchAccountID(user);
        System.out.println(accountID);
        String qr = "INSERT INTO \n" + //
                "ClientInfo(clientName, gender, age, email, cpNumber, landline, address, nationality, reason, accountNumber) \n"
                + //
                "VALUES ('" + name + "', '" + gender + "', " + age + ", '" + email + "', '" + cp + "', '" + landline
                + "', '" + address + "', '" + nationality + "', '" + reason + "', " + accountID + ")";
        st.executeUpdate(qr);

        con.close();
    }

    public void insertReservation(String type, String packageID, String checkin, String depart, String clientID)
            throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        String packageToID;
        if (packageID.equals("Grange Pool Villa")) {
            packageToID = "1";
        } else if (packageID.equals("Petrus Pool Villa")) {
            packageToID = "2";
        } else if (packageID.equals("Shiraz Suite Room")) {
            packageToID = "3";
        } else if (packageID.equals("Chardonnay Suite Room")) {
            packageToID = "4";
        } else if (packageID.equals("Sauvignon Grand Villa")) {
            packageToID = "5";
        } else {
            packageToID = "6";
        }
        String id = "";
        try {
            id = new database().queryWithID(Integer.parseInt(clientID), "ClientInfo", "accountNumber").getFirst();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(id);
        String qr = "INSERT INTO \n"
                + "Reservations(typeOfReservation, packageID, checkinDate, departureDate, clientID) \n"
                + //
                "VALUES ('" + type + "', " + packageToID + ", '" + checkin + "', '" + depart
                + "', " + id + ")";
        st.executeUpdate(qr);
        con.close();
    }

    public void insertReservation2(String type, String packageID, String checkin, String depart, String clientID)
            throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();

        String qr = "INSERT INTO \n"
                + "Reservations(typeOfReservation, packageID, checkinDate, departureDate, clientID) \n"
                + //
                "VALUES ('" + type + "', " + packageID + ", '" + checkin + "', '" + depart
                + "', " + clientID + ")";
        st.executeUpdate(qr);
        con.close();
    }

    public void updateReservation(String clientID, String type, String packageID, String checkin, String departure)
            throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        String qr = "UPDATE Reservations SET typeOfReservation = '" + type + "', packageID = " + packageID
                + ", checkinDate = '" + checkin + "', departureDate = '" + departure + "' WHERE clientID = " + clientID;
        st.executeUpdate(qr);
        con.close();
    }

    public void deleteReservation(String reservationID) throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        String qr = "DELETE FROM Reservations WHERE reservationID = " + reservationID;
        st.executeUpdate(qr);
        con.close();
    }

    public void customActionQuery(String query) throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        con.close();
    }
}
