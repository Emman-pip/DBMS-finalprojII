
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/*
 * TODO:
 * for selecting all elements of a table done -- DONE
 * selecting certain records on certain tables -- DONE
 * deleting records systematically - when account is deleted, all data related
 * to that account will be too --
 * - make a chained Delete query [once an account is deleted, all user data will
 * be deleted too.]
 * - make another one for deleting/cancelling reservations
 * - remove directly from database
 * updating certain records
 * inserting records
 * method for selecting all records of a certain column in a table
 */
public class database {
    String url = "jdbc:mysql://localhost:3306/reservationSystem";
    String pass = "108996eE@emman";
    String username = "root";

    public static void queryWithID(int ID, String tableName) throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        String qr;
        if (tableName == "ClientInfo") {
            qr = "SELECT * FROM " + tableName + " WHERE clientId = " + ID + ";";

        } else if (tableName == "Packages") {
            qr = "SELECT * FROM " + tableName + " WHERE packageId = " + ID + ";";

        } else if (tableName == "Reservations") {
            qr = "SELECT * FROM " + tableName + " WHERE reservationID = " + ID + ";";

        } else if (tableName == "user_accounts") {
            qr = "SELECT * FROM " + tableName + " WHERE accountID = " + ID + ";";

        } else {
            qr = "";
        }
        ResultSet rs = st.executeQuery(qr);

        if (tableName == "ClientInfo") {
            while (rs.next()) {
                // try to find a way to shorten the code with logic (loops or recursion?)
                // TODO: put into arrays (2D) and return?

                System.out.println(rs.getInt(1) + "  ");
                System.out.print(rs.getString(2) + "  ");
                System.out.print(rs.getString(3) + "  ");
                System.out.print(rs.getInt(4) + "  ");
                System.out.print(rs.getString(5) + "  ");
                System.out.print(rs.getString(6) + "  ");
                System.out.print(rs.getString(7) + "  ");
                System.out.print(rs.getString(8) + "  ");
                System.out.print(rs.getString(9) + "  ");
                System.out.println(rs.getString(10));
            }
        } else if (tableName == "Packages") {
            if (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(rs.getString(2));
                System.out.print(rs.getString(3));
                System.out.println(rs.getInt(4));

            }
        } else if (tableName == "Reservations") {
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getInt(3));
                System.out.println(rs.getDate(4));
                System.out.println(rs.getDate(5));
                System.out.println(rs.getInt(6));
            }

        } else if (tableName == "user_accounts") {
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));

            }
        } else {
            System.out.println("ERROR");
        }
        con.close();
    }

    public static LinkedList<LinkedList<String>> queries(String tableName) throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();

        String qr = "SELECT * FROM " + tableName + ";";
        ResultSet rs = st.executeQuery(qr);

        // while statement will have different versions based on what table is chosen by
        // the function;
        LinkedList<LinkedList<String>> output = new LinkedList<LinkedList<String>>();
        LinkedList<String> credentials = new LinkedList<String>();

        if (tableName == "ClientInfo") {
            while (rs.next()) {
                // try to find a way to shorten the code with logic (loops or recursion?)
                // TODO: put into arrays (2D) and return?

                System.out.println(rs.getInt(1) + "  ");
                System.out.print(rs.getString(2) + "  ");
                System.out.print(rs.getString(3) + "  ");
                System.out.print(rs.getInt(4) + "  ");
                System.out.print(rs.getString(5) + "  ");
                System.out.print(rs.getString(6) + "  ");
                System.out.print(rs.getString(7) + "  ");
                System.out.print(rs.getString(8) + "  ");
                System.out.print(rs.getString(9) + "  ");
                System.out.println(rs.getString(10));
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
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getInt(3));
                System.out.println(rs.getDate(4));
                System.out.println(rs.getDate(5));
                System.out.println(rs.getInt(6));
            }

        } else if (tableName == "user_accounts") {
            while (rs.next()) {
                String userID = Integer.toString(rs.getInt(1));
                credentials.add(userID);

                credentials.add(rs.getString(2));
                credentials.add(rs.getString(3));

                // System.out.println(rs.getInt(1));
                // System.out.println(rs.getString(2));
                output.add((credentials));
                credentials.removeLast();
            }
        } else {
            System.out.println("ERROR");
        }
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

    // primary key
    public void insertPersonalRecords(String user, String password, String name, String gender, String age,
            String email, String cp, String landline, String address, String nationality, String reason)
            throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        String qr2 = "INSERT INTO user_accounts(username, password) VALUES ('" + user + "', '" + password + "');";
        // QUERY TO GET THE ID
        int accountID = searchAccountID(user);
        String qr = "INSERT INTO \n" + //
                "clientInfo(clientName, gender, age, email, cpNumber, landline, address, nationality, reason, accountNumber) \n"
                + //
                "VALUES ('" + name + "', '" + gender + "', " + age + ", '" + email + "', '" + cp + "', '" + landline
                + "', '" + address + "', '" + nationality + "', '" + reason + "', " + accountID + ")";
        ResultSet rs = st.executeQuery(qr2);
        ResultSet r2 = st.executeQuery(qr);

        con.close();
    }

    public static void main(String[] args) {
        try {
            System.out.println(new database().searchAccountID("emman"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
