
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class database {
    String url = "jdbc:mysql://localhost:3306/reservationSystem";
    String pass = "137666eE";
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
