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

        btn_edit_delete_update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnl_content.removeAll();
                // pnl_content.add(new Label("hello world"));
                pnl_content.add(new editDelete());
                pnl_content.setVisible(false);
                pnl_content.setVisible(true);
            }
        });

        // btn_addRecords.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // pnl_content.removeAll();
        // pnl_content.add(new Label("hello world"));
        // JPanel pnl_enter = new JPanel();
        // JLabel lbl_clientID = new JLabel("Client ID: ");
        // JTextField txt_clientID = new JTextField();
        // pnl_enter.add(lbl_clientID);
        // pnl_enter.add(txt_clientID);
        // pnl_content.add(pnl_enter);

        // // currently working on getting id of client and getting ang username and

        // try {
        // String accountNumber = new database()
        // .queryWithID(Integer.parseInt(txt_clientID.getText()), "ClientInfo",
        // "clientId").getLast();
        // LinkedList<String> accountData = new
        // database().queryWithID(Integer.parseInt(accountNumber),
        // "user_accounts", "accountID");
        // String username = accountData.get(1);
        // String password = accountData.get(2);
        // JTextField txt_username = new JTextField();
        // txt_username.setText(username);
        // JPasswordField txt_password = new JPasswordField();
        // txt_password.setText(password);
        // JPanel pnl_container = new JPanel();
        // pnl_container.add(new personalInfoForm(txt_username, txt_password, frm));
        // pnl_content.add(pnl_container, BorderLayout.CENTER);

        // } catch (Exception ex) {
        // System.out.println(ex);
        // }
        // pnl_content.setVisible(false);
        // pnl_content.setVisible(true);
        // }
        // });

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

class editDelete extends JPanel {
    editDelete() {
        this.setLayout(new BorderLayout());
        JLabel lbl_id = new JLabel("CLIENT ID: ");
        JTextField txt_id = new JTextField();
        txt_id.setColumns(20);
        JPanel pnl_components = new JPanel();
        pnl_components.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.ipady = 20;
        gbc.gridy = 0;
        pnl_components.add(lbl_id, gbc);

        gbc.gridx = 1;
        gbc.ipady = 20;
        gbc.gridy = 0;
        pnl_components.add(txt_id, gbc);

        JButton btn = new JButton("Search");
        gbc.gridx = 0;

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        pnl_components.add(btn, gbc);

        this.add(pnl_components, BorderLayout.CENTER);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editDeleteReservation ed = new editDeleteReservation(Integer.parseInt(txt_id.getText()));
                ed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ;
                ed.btn_back.setVisible(false);

            }
        });
    }
}