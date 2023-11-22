import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class adminWIndow extends JFrame {
    adminWIndow() {
        new flatlaf();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel pnl_side = new JPanel();
        JPanel pnl_main = new JPanel();
        pnl_main.setLayout(new BorderLayout());
        pnl_side.add(new sidePanel(this, pnl_main));
        pnl_main.add(new JLabel("HELLO"));
        JPanel pnl_containerMain = new JPanel();

        pnl_containerMain.setLayout(new BorderLayout());
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenu menu2 = new JMenu("Menu2");
        JMenuItem menu_item1 = new JMenuItem("menu item 1");

        menu.add(menu_item1);
        mb.add(menu);
        mb.add(menu2);
        // this.add(menu);
        pnl_containerMain.add(mb, BorderLayout.NORTH);
        pnl_containerMain.add(pnl_side, BorderLayout.WEST);
        pnl_containerMain.add(pnl_main, BorderLayout.CENTER);
        this.add(pnl_containerMain);
        this.setTitle("WELCOME ADMIN!");
        this.setSize(2000, 1500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new adminWIndow();
    }
}

// design on top
// create see client info
// create edit/update/delete client info
// add records
// custom queries
class sidePanel extends JPanel {
    sidePanel(JFrame frm, JPanel pnl_content) {
        // this.setLayout(new BorderLayout());
        // see client info
        JButton btn_seeClientInfo = new JButton("SEE CLIENT INFO");
        // edit/delete/update
        JButton btn_edit_delete_update = new JButton("EDIT/DELETE/UPDATE");
        // add records
        JButton btn_addRecords = new JButton("ADD RECORDS");
        // custom queries
        JButton btn_custom = new JButton("CUSTOM QUERIES");

        JButton btn_logout = new JButton("LOGOUT");

        JPanel pnl_button = new JPanel();
        pnl_button.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton[] arr_btn = { btn_seeClientInfo, btn_edit_delete_update, btn_addRecords, btn_custom, btn_logout };
        for (int i = 0; i < arr_btn.length; i++) {
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipady = 20;
            gbc.insets = new Insets(10, 0, 0, 0);
            gbc.gridx = 0;
            pnl_button.add(arr_btn[i], gbc);
        }

        btn_seeClientInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnl_content.removeAll();
                pnl_content.add(new clientInfoDisplay());

                pnl_content.setVisible(false);
                pnl_content.setVisible(true);
            }
        });

        btn_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // code to logout admin window and come back to landing page
                frm.dispose();
                new landingWIndow();
            }
        });
        this.add(pnl_button);
    }
}

class clientInfoDisplay extends JPanel {
    clientInfoDisplay() {
        this.setLayout(new BorderLayout());
        try {
            LinkedList<LinkedList<String>> data = new database().queries("ClientInfo");
            String[][] output = new String[data.size()][data.get(0).size()];

            for (int i = 0; i < data.size(); i++) {
                String[] record = new String[data.get(0).size()];
                for (int v = 0; v < data.get(0).size(); v++) {
                    record[v] = data.get(i).get(v);
                }
                output[i] = record;

            }
            // for (String[] L : output) {
            // for (String l : L) {
            // System.out.println(l);
            // }
            // }

            String[] columns = { "clientID", "clientName", "Gender", "age", "Email", "CP", "Landline", "Address",
                    "Nantionality", "Reason", "AccountNumber" };
            JTable tbl_clientInfo = new JTable(output, columns);
            JScrollPane sp = new JScrollPane(tbl_clientInfo);
            this.add(sp, BorderLayout.CENTER);
        } catch (Exception ex) {
            System.out.println("ERROR:" + ex);
        }
    }
}
