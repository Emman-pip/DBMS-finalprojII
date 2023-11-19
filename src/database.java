
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
 * to that account will be too
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

    public static LinkedList<String> queryWithID(int ID, String tableName, String columnName) throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        String qr;
        qr = "SELECT * FROM " + tableName + " WHERE " + columnName + " = " + ID + ";";
        ResultSet rs = st.executeQuery(qr);
        LinkedList<String> output = new LinkedList<String>();
        if (tableName == "ClientInfo") {
            while (rs.next()) {
                // try to find a way to shorten the code with logic (loops or recursion?)
                // TODO: put into arrays (2D) and return?
                output.add(String.valueOf(rs.getInt(1)));
                // System.out.println(rs.getInt(1) + " ");
                output.add(String.valueOf(rs.getString(2)));
                output.add(String.valueOf(rs.getString(3)));
                output.add(String.valueOf(rs.getInt(4)));
                output.add(String.valueOf(rs.getString(5)));
                output.add(String.valueOf(rs.getString(6)));
                output.add(String.valueOf(rs.getString(7)));
                output.add(String.valueOf(rs.getString(8)));
                output.add(String.valueOf(rs.getString(9)));
                output.add(String.valueOf(rs.getString(10)));

                // return output;
                // System.out.print(rs.getString(2) + " ");
                // System.out.print(rs.getString(3) + " ");
                // System.out.print(rs.getInt(4) + " ");
                // System.out.print(rs.getString(5) + " ");
                // System.out.print(rs.getString(6) + " ");
                // System.out.print(rs.getString(7) + " ");
                // System.out.print(rs.getString(8) + " ");
                // System.out.print(rs.getString(9) + " ");
                // System.out.println(rs.getString(10));
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
                output.add(String.valueOf(rs.getInt(1)));
                output.add(rs.getString(2));
                output.add(String.valueOf(rs.getInt(3)));
                output.add(String.valueOf(rs.getDate(4)));
                output.add(String.valueOf(rs.getDate(5)));
                output.add(String.valueOf(rs.getInt(6)));

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
        return output;
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
                LinkedList<String> credentials = new LinkedList<String>();

                String userID = Integer.toString(rs.getInt(1));
                credentials.add(userID);

                credentials.add(rs.getString(2));
                credentials.add(rs.getString(3));

                // System.out.println(rs.getInt(1));
                // System.out.println(rs.getString(2));
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
        int accountID = searchAccountID(user);
        int rs = st.executeUpdate(qr2);
    }

    // primary key
    public void insertPersonalRecords(String user, String name, String gender, String age,
            String email, String cp, String landline, String address, String nationality, String reason)
            throws Exception {
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();
        // String qr2 = "INSERT INTO user_accounts(username, password) VALUES ('" + user
        // + "', '" + password + "');";
        // QUERY TO GET THE ID
        int accountID = searchAccountID(user);
        // int rs = st.executeUpdate(qr2);
        // try {
        // } catch (Exception e) {
        // System.out.println(e);
        // return;
        // }
        // FIX THE ERROR HERE: ERROR IS WHEN DATA IS INSERTED, EVERYTHING GETS MESSED
        // UP, STUFF HAVE TO BE CLICKED TWICE TO WORK
        System.out.println(accountID);
        String qr = "INSERT INTO \n" + //
                "ClientInfo(clientName, gender, age, email, cpNumber, landline, address, nationality, reason, accountNumber) \n"
                + //
                "VALUES ('" + name + "', '" + gender + "', " + age + ", '" + email + "', '" + cp + "', '" + landline
                + "', '" + address + "', '" + nationality + "', '" + reason + "', " + accountID + ")";
        int r2 = st.executeUpdate(qr);

        con.close();
    }

    public static void main(String[] args) {
        try {
            System.out.println(new database().queryWithID(1, "Reservations", "clientID").size());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
