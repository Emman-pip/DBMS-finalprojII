
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
            }
        } else {
            System.out.println("ERROR");
        }
        return output;

    }

    public static void main(String[] args) {
        // try {
        // new database().queries("user_accounts");

        // } catch (Exception e) {
        // System.out.println("ERROR: " + e);
        // }
    }
}
