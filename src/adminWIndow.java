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
        JButton btn_custom = new JButton("CUSTOM SELECT QUERIES");

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

        btn_custom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnl_content.removeAll();
                // pnl_content.add(new Label("hello world"));
                // pnl_content.add(pnl_enter);
                // enter new class here
                pnl_content.add(new customQuery());

                // currently working on getting id of client and getting ang username and
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
        JPanel pnl_top = new JPanel();
        String[] tablenameList = { "ClientInfo", "Packages", "user_accounts", "Reservations" };
        JComboBox combo_tablename = new JComboBox(tablenameList);
        pnl_top.add(combo_tablename);
        JPanel pnl_centerJPanel = new JPanel();
        this.add(pnl_top, BorderLayout.NORTH);
        pnl_centerJPanel.setLayout(new BorderLayout());
        try {

            pnl_centerJPanel.add(new customQueries("SELECT * FROM ClientInfo"), BorderLayout.CENTER);
        } catch (Exception ex) {
            System.out.println("ERROR:" + ex);
        }
        this.add(pnl_centerJPanel, BorderLayout.CENTER);

        combo_tablename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    pnl_centerJPanel.removeAll();
                    pnl_centerJPanel.add(new customQueries("SELECT * FROM " + combo_tablename.getSelectedItem()),
                            BorderLayout.CENTER);
                    pnl_centerJPanel.setVisible(false);
                    pnl_centerJPanel.setVisible(true);

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
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

class customQuery extends JPanel {
    customQuery() {
        this.setLayout(new BorderLayout());
        JPanel pnl_enter = new JPanel();
        JPanel pnl_table = new JPanel();
        pnl_enter.setLayout(new GridBagLayout());
        pnl_table.setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel lbl_clientID = new JLabel("TYPE YOUR QUERY HERE: ");
        JTextArea txt_clientID = new JTextArea();

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnl_enter.add(lbl_clientID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnl_enter.add(txt_clientID, gbc);

        JButton btn_query = new JButton("Execute query");
        gbc.gridx = 0;
        gbc.gridy = 2;

        pnl_enter.add(btn_query, gbc);

        btn_query.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnl_table.removeAll();
                try {
                    customQueries cq = new customQueries(txt_clientID.getText());
                    // cq.setResizable(true);
                    pnl_table.add(cq, BorderLayout.CENTER);
                } catch (Exception ex) {
                    System.out.println("IN");
                    JLabel label = new JLabel("INVALID QUERY");
                    pnl_table.add(label, BorderLayout.CENTER);
                }
                pnl_table.setVisible(false);
                pnl_table.setVisible(true);
            }
        });

        this.add(pnl_enter, BorderLayout.NORTH);
        this.add(pnl_table, BorderLayout.CENTER);

    }
}