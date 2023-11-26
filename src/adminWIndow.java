import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminWIndow extends JFrame {
    adminWIndow() {
        new flatlaf();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel pnl_side = new JPanel();
        JPanel pnl_main = new JPanel();
        pnl_main.setLayout(new BorderLayout());
        pnl_side.add(new sidePanel(this, pnl_main));
        try {
            JLabel lbl_icon = new JLabel();
            lbl_icon.setHorizontalAlignment(JLabel.CENTER);
            images imgs = new images();
            imgs.images();
            Image imgmain = imgs.getScaledImage(imgs.outdoor, 1300, 800);
            lbl_icon.setIcon(new ImageIcon(imgmain));

            pnl_main.add(lbl_icon, BorderLayout.CENTER);

        } catch (Exception e) {
            System.out.println(e);
        }
        JPanel pnl_containerMain = new JPanel();

        pnl_containerMain.setLayout(new BorderLayout());

        // JMenuBar mb = new JMenuBar();
        // JMenu menu = new JMenu("Packages Info");
        // try {
        // images imgs = new images();
        // imgs.images();
        // menu.setIcon(new ImageIcon(imgs.getScaledImage(imgs.packageicon, 50, 50)));
        // } catch (Exception e) {
        // System.out.println("FAILED TO LOAD ICONS: " + e);
        // }
        // JMenu menu2 = new JMenu("Help");

        // JMenu menu_item1 = new JMenu("Grange Pool Villa (1)");
        // menu_item1.add(new JMenuItem("try"));

        // JMenu menu_item2 = new JMenu("Petrus Pool Villa (2)");
        // JMenu menu_item3 = new JMenu("Shiraz Suite Room (3)");
        // JMenu menu_item4 = new JMenu("Chardonnay Suite Room (4)");
        // JMenu menu_item5 = new JMenu("Sauvignon Grand Villa (5)");
        // JMenu menu_item6 = new JMenu("Bordeaux Grand Villa (6)");

        // menu.add(menu_item1);
        // menu.add(menu_item2);
        // menu.add(menu_item3);
        // menu.add(menu_item4);
        // menu.add(menu_item5);
        // menu.add(menu_item6);

        // mb.add(menu);
        // mb.add(menu2);

        // this.add(menu);
        pnl_containerMain.add(new menuBar().menuB(), BorderLayout.NORTH);
        pnl_containerMain.add(pnl_side, BorderLayout.WEST);
        pnl_containerMain.add(pnl_main, BorderLayout.CENTER);
        this.add(pnl_containerMain);
        this.setTitle("WELCOME ADMIN!");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(1000, 800));
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
        JButton btn_seeClientInfo = new JButton("SEE DATABASE TABLES");
        // edit/delete/update
        JButton btn_edit_delete_update = new JButton("EDIT/DELETE/UPDATE");
        // add records
        JButton btn_addRecords = new JButton("ADD RECORDS");
        // custom queries
        JButton btn_custom = new JButton("CUSTOM SELECT QUERIES");

        JButton btn_customAction = new JButton("CUSTOM ACTION QUERIES");

        JButton btn_logout = new JButton("LOGOUT");

        JPanel pnl_button = new JPanel();
        pnl_button.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton[] arr_btn = { btn_seeClientInfo, btn_edit_delete_update, btn_addRecords, btn_custom, btn_customAction,
                btn_logout };
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

        btn_addRecords.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnl_content.removeAll();
                pnl_content.add(new addRecordsForm());
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

        btn_customAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnl_content.removeAll();
                pnl_content.add(new customActionQuery());
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

class addRecordsForm extends JPanel {
    JPanel master = this;

    addRecordsForm() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(80, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        // pnl_one user account
        JPanel pnl_account = new JPanel();
        pnl_account.setLayout(new GridBagLayout());
        JTextField txt_username = new JTextField();
        JPasswordField txt_password = new JPasswordField();
        JButton btn_createAccount = new JButton("Create account");

        // pnl_two personal info
        JPanel pnl_personalInfo = new JPanel();
        pnl_personalInfo.setLayout(new GridBagLayout());
        JTextField txt_name = new JTextField();
        JTextField txt_gender = new JTextField();
        JTextField txt_age = new JTextField();
        JTextField txt_email = new JTextField();
        JTextField txt_cp = new JTextField();
        JTextField txt_landline = new JTextField();
        JTextField txt_address = new JTextField();
        JTextField txt_nationality = new JTextField();
        JTextArea txt_reason = new JTextArea();
        JTextField txt_accNum = new JTextField();
        JButton btn_setPersonalInfo = new JButton("Set Personal Info");
        btn_setPersonalInfo.setEnabled(false);

        // pnl_three reseration info
        JPanel pnl_reservation = new JPanel();
        pnl_reservation.setLayout(new GridBagLayout());
        JTextField txt_type = new JTextField();
        JTextField txt_package = new JTextField();
        JTextField txt_checkIn = new JTextField();
        JTextField txt_depart = new JTextField();
        JTextField txt_clientID = new JTextField();
        JButton btn_reserve = new JButton("Reserve");
        btn_reserve.setEnabled(false);

        JTextField[] txt_list = {
                txt_username, txt_password, txt_name, txt_gender, txt_age, txt_email, txt_cp, txt_landline, txt_address,
                txt_nationality, txt_accNum, txt_type, txt_package, txt_checkIn, txt_depart, txt_clientID
        };

        JTextField[] txt_accounts = {
                txt_username, txt_password
        };

        JTextField[] txt_personalInfo = {
                txt_name, txt_gender, txt_age, txt_email, txt_cp, txt_landline, txt_address,
                txt_nationality, txt_accNum
        };

        JTextField[] txt_reservation = {
                txt_type, txt_package, txt_checkIn, txt_depart, txt_clientID
        };

        btn_createAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if ((txt_username.getText().equals(""))
                            || (String.valueOf(txt_password.getPassword()).equals(""))) {
                        throw new Exception();
                    }
                    new database().insertUserAccount(txt_username.getText(),
                            String.valueOf(txt_password.getPassword()));

                    for (JTextField txt : txt_personalInfo) {
                        txt.setEnabled(true);
                        txt_reason.setEnabled(true);
                    }
                    btn_setPersonalInfo.setEnabled(true);

                    for (JTextField txt : txt_accounts) {
                        txt.setEnabled(false);
                        btn_createAccount.setEnabled(false);
                    }
                    txt_accNum.setText(String.valueOf(new database().searchAccountID(txt_username.getText())));
                    txt_accNum.setEnabled(false);

                    JOptionPane.showMessageDialog(new JFrame(), "DATABASE INSERTION SUCCESS");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "DATABASE INSERTION FAILED: " + ex);
                }
            }
        });

        btn_setPersonalInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    for (JTextField txt : txt_personalInfo) {
                        if (txt.getText().isBlank() || txt_reason.getText().isEmpty()) {
                            throw new Exception("Blank fields");
                        }

                    }
                    new database().insertPersonalRecords(
                            txt_username.getText(),
                            txt_name.getText(),
                            txt_gender.getText(),
                            txt_age.getText(),
                            txt_email.getText(),
                            txt_cp.getText(),
                            txt_landline.getText(),
                            txt_address.getText(),
                            txt_nationality.getText(),
                            txt_reason.getText());

                    if ((txt_username.getText().equals(""))
                            || (String.valueOf(txt_password.getPassword()).equals(""))) {
                        throw new Exception();
                    }

                    for (JTextField txt : txt_personalInfo) {
                        txt.setEnabled(false);
                        txt_reason.setEnabled(false);
                    }
                    btn_setPersonalInfo.setEnabled(false);
                    // btn_createAccount.setEnabled(false);

                    for (JTextField txt : txt_reservation) {
                        txt.setEnabled(true);
                        btn_reserve.setEnabled(true);
                    }

                    txt_clientID.setEnabled(false);

                    // HERE set text to client ID
                    String ID = new database()
                            .queryWithID(Integer.parseInt(txt_accNum.getText()), "ClientInfo", "accountNumber").get(0);
                    // System.out.println("ID: " + ID);
                    txt_clientID.setText(ID);

                    JOptionPane.showMessageDialog(new JFrame(), "DATABASE INSERTION SUCCESS");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "DATABASE INSERTION FAILED: " + ex);
                }
                // TODO: FIX BUG SA ACTION QUERIES
            }
        });

        // btn_reserve
        btn_reserve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // System.out.println(txt_type.getText() +
                    // txt_package.getText() +
                    // txt_checkIn.getText() +
                    // txt_depart.getText() +
                    // txt_clientID.getText());
                    new database().insertReservation2(
                            txt_type.getText(),
                            txt_package.getText(),
                            txt_checkIn.getText(),
                            txt_depart.getText(),
                            txt_clientID.getText());
                    for (JTextField txt : txt_reservation) {
                        txt.setEnabled(false);
                        btn_reserve.setEnabled(false);
                    }
                    JOptionPane.showMessageDialog(new JFrame(),
                            "RESERVATION SUCCESS. RECLICK THE ADD RECORDS BUTTON IN SIDE PANEL TO RESET THIS PAGE.");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "RESERVATION FAIL: " + ex);
                }
            }
        });
        for (JTextField txt : txt_personalInfo) {
            txt.setEnabled(false);
            txt_reason.setEnabled(false);
        }

        for (JTextField txt : txt_reservation) {
            txt.setEnabled(false);
        }

        for (JTextField txt : txt_list) {
            txt.setColumns(20);
        }

        // reservation components

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnl_reservation.add(new JLabel("Package type: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnl_reservation.add(new JLabel("Package ID: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnl_reservation.add(new JLabel("Check In Date: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pnl_reservation.add(new JLabel("Departure Date: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnl_reservation.add(new JLabel("Client ID: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pnl_reservation.add(txt_type, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnl_reservation.add(txt_package, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pnl_reservation.add(txt_checkIn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pnl_reservation.add(txt_depart, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pnl_reservation.add(txt_clientID, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        // gbc.gridwidth = 2;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        pnl_reservation.add(btn_reserve, gbc);

        // ipasok ang mga gui
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnl_personalInfo.add(new JLabel("Name: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnl_personalInfo.add(new JLabel("Gender: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnl_personalInfo.add(new JLabel("Age: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pnl_personalInfo.add(new JLabel("Email: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnl_personalInfo.add(new JLabel("Mobile number: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        pnl_personalInfo.add(new JLabel("Landline: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        pnl_personalInfo.add(new JLabel("Address: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        pnl_personalInfo.add(new JLabel("Nationality: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        pnl_personalInfo.add(new JLabel("Reason: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        pnl_personalInfo.add(new JLabel("Account Number: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pnl_personalInfo.add(txt_name, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnl_personalInfo.add(txt_gender, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pnl_personalInfo.add(txt_age, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pnl_personalInfo.add(txt_email, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pnl_personalInfo.add(txt_cp, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        pnl_personalInfo.add(txt_landline, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        pnl_personalInfo.add(txt_address, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        pnl_personalInfo.add(txt_nationality, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 8;
        txt_reason.setWrapStyleWord(true);
        pnl_personalInfo.add(txt_reason, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        pnl_personalInfo.add(txt_accNum, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        pnl_personalInfo.add(btn_setPersonalInfo, gbc);
        //

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnl_account.add(new JLabel("username: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pnl_account.add(txt_username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnl_account.add(new JLabel("password: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnl_account.add(txt_password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        pnl_account.add(btn_createAccount, gbc);

        // gbc.gridx = 0;
        // gbc.gridy = 0;
        JPanel pnl_pnls = new JPanel();
        // pnl_pnls.setPreferredSize(pnl_personalInfo.getSize());

        pnl_pnls.add(pnl_account);

        // gbc.gridx = 1;
        // gbc.gridy = 0;
        pnl_pnls.add(pnl_personalInfo);

        // gbc.gridx = 2;
        // gbc.gridy = 0;
        pnl_pnls.add(pnl_reservation);

        this.add(pnl_pnls, BorderLayout.CENTER);

    }
}

class clientInfoDisplay extends JPanel {
    clientInfoDisplay() {
        this.setLayout(new BorderLayout());
        JPanel pnl_top = new JPanel();
        String[] tablenameList = { "ClientInfo", "Packages", "user_accounts", "Reservations" };
        JComboBox combo_tablename = new JComboBox(tablenameList);
        combo_tablename.setFont(new Font(null, Font.BOLD, 30));
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

        JButton btn_delete = new JButton("DELETE ALL RECORDS OF USER");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        pnl_components.add(btn_delete, gbc);

        btn_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new deleteUserData().deleteAll(txt_id.getText());
                    JOptionPane.showMessageDialog(new JFrame(), "All data related to the user ID was deleted.");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "FAILED TO DELETE RECORDS:" + ex);
                }
            }
        });

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

class deleteUserData {
    public void deleteAll(String ID) throws Exception {
        new database().customActionQuery("DELETE FROM Reservations WHERE clientID = " + ID);
        new database()
                .customActionQuery("DELETE FROM Reservations WHERE clientID = " + new database().searchAccountID(ID));
        new database().customActionQuery("DELETE FROM ClientInfo WHERE clientId = " + ID);
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

class customActionQuery extends JPanel {
    customActionQuery() {
        this.setLayout(new BorderLayout());
        JPanel pnl_enter = new JPanel();
        JPanel pnl_table = new JPanel();
        pnl_enter.setLayout(new GridBagLayout());
        pnl_table.setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel lbl_clientID = new JLabel("TYPE YOUR INSERT/UPDATE/DELETE QUERY HERE: ");
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
                    new database().customActionQuery(txt_clientID.getText());
                    // pnl_table.add(cq, BorderLayout.CENTER);
                    JOptionPane.showMessageDialog(new JFrame(), "QUERY SUCCESSFUL");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "PLEASE RECHECK YOUR QUERY");
                }
                pnl_table.setVisible(false);
                pnl_table.setVisible(true);
            }
        });

        this.add(pnl_enter, BorderLayout.NORTH);
        this.add(pnl_table, BorderLayout.CENTER);

    }
}