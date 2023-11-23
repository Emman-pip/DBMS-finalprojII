import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;

public class customQueries extends JInternalFrame {
    public customQueries(String qr) throws Exception {
        new flatlaf();
        database db = new database();
        String url = db.url;
        String username = db.username;
        String pass = db.pass;

        ArrayList data = new ArrayList();
        ArrayList columnNames = new ArrayList();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, pass);
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(qr);
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        for (int i = 1; i < columnCount + 1; i++) {
            columnNames.add(md.getColumnName(i));
        }

        while (rs.next()) {
            ArrayList record = new ArrayList();
            for (int i = 1; i < columnCount + 1; i++) {
                record.add(rs.getObject(i));
            }
            data.add(record);
        }
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < columnNames.size(); i++) {
            columnNamesVector.add(columnNames.get(i));
        }

        for (int i = 0; i < data.size(); i++) {
            ArrayList subArr = (ArrayList) data.get(i);
            Vector recordVector = new Vector();
            for (int v = 0; v < subArr.size(); v++) {
                recordVector.add(subArr.get(v));
            }
            dataVector.add(recordVector);
        }
        con.close();

        JTable tbl = new JTable(dataVector, columnNamesVector);
        JScrollPane sp = new JScrollPane(tbl);

        this.add(sp);

        // this.setSize(500, 500);
        this.setVisible(true);
    }

    // public static void main(String[] args) throws Exception {
    // new customQueries();
    // }
}
