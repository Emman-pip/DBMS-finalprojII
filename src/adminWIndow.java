import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
            imagesObj imgs = new imagesObj();
            imgs.images();
            Image imgmain = imgs.getScaledImage(imgs.outdoor, 1300, 800);
            lbl_icon.setIcon(new ImageIcon(imgmain));

            pnl_main.add(lbl_icon, BorderLayout.CENTER);

        } catch (Exception e) {
            System.out.println(e);
        }
        JPanel pnl_containerMain = new JPanel();

        pnl_containerMain.setLayout(new BorderLayout());
        pnl_containerMain.add(new menuBar().menuB(), BorderLayout.NORTH);
        pnl_containerMain.add(pnl_side, BorderLayout.WEST);
        pnl_containerMain.add(pnl_main, BorderLayout.CENTER);
        this.add(pnl_containerMain);
        this.setTitle("WELCOME ADMIN!");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(1000, 800));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

class sidePanel extends JPanel {
    sidePanel(JFrame frm, JPanel pnl_content) {
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton btn_seeClientInfo = new JButton("SEE DATABASE TABLES");
        JButton btn_edit_delete_update = new JButton("EDIT/DELETE/UPDATE");
        JButton btn_addRecords = new JButton("ADD RECORDS");
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
                pnl_content.add(new clientInfoDisplay(), BorderLayout.CENTER);

                pnl_content.setVisible(false);
                pnl_content.setVisible(true);
            }
        });

        btn_edit_delete_update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnl_content.add(new editDelete(pnl_content), BorderLayout.EAST);
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
                pnl_content.add(new customQuery());

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

                    for (JTextField txt : txt_reservation) {
                        txt.setEnabled(true);
                        btn_reserve.setEnabled(true);
                    }

                    txt_clientID.setEnabled(false);

                    String ID = String.valueOf(new database()
                            .customQueries(
                                    "SELECT clientId FROM ClientInfo WHERE accountNumber = " + txt_accNum.getText())
                            .get(0).get(0));
                    // .queryWithID(Integer.parseInt(txt_accNum.getText()), "ClientInfo",
                    // "accountNumber").get(0);
                    txt_clientID.setText(ID);

                    JOptionPane.showMessageDialog(new JFrame(), "DATABASE INSERTION SUCCESS");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "DATABASE INSERTION FAILED: " + ex);
                }
            }
        });

        // btn_reserve
        btn_reserve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
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
        JLabel lbl_reserveForm = new JLabel("Reservation Form:");

        // reservation components
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        lbl_reserveForm.setFont(getFont());
        pnl_reservation.add(lbl_reserveForm, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnl_reservation.add(new JLabel("Package type: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnl_reservation.add(new JLabel("Package ID: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pnl_reservation.add(new JLabel("Check In Date: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnl_reservation.add(new JLabel("Departure Date: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        pnl_reservation.add(new JLabel("Client ID: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnl_reservation.add(txt_type, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pnl_reservation.add(txt_package, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pnl_reservation.add(txt_checkIn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pnl_reservation.add(txt_depart, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        pnl_reservation.add(txt_clientID, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        pnl_reservation.add(btn_reserve, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnl_personalInfo.add(new JLabel("Personal Info Form: "));

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnl_personalInfo.add(new JLabel("Name: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnl_personalInfo.add(new JLabel("Gender: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pnl_personalInfo.add(new JLabel("Age: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnl_personalInfo.add(new JLabel("Email: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        pnl_personalInfo.add(new JLabel("Mobile number: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        pnl_personalInfo.add(new JLabel("Landline: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        pnl_personalInfo.add(new JLabel("Address: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        pnl_personalInfo.add(new JLabel("Nationality: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        pnl_personalInfo.add(new JLabel("Reason: "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        pnl_personalInfo.add(new JLabel("Account Number: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnl_personalInfo.add(txt_name, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pnl_personalInfo.add(txt_gender, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pnl_personalInfo.add(txt_age, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pnl_personalInfo.add(txt_email, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        pnl_personalInfo.add(txt_cp, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        pnl_personalInfo.add(txt_landline, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        pnl_personalInfo.add(txt_address, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        pnl_personalInfo.add(txt_nationality, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 9;
        txt_reason.setWrapStyleWord(true);
        pnl_personalInfo.add(txt_reason, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        pnl_personalInfo.add(txt_accNum, gbc);

        gbc.gridx = 1;
        gbc.gridy = 11;
        pnl_personalInfo.add(btn_setPersonalInfo, gbc);
        //

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnl_account.add(new JLabel("Account Form: "), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnl_account.add(new JLabel("username: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnl_account.add(txt_username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnl_account.add(new JLabel("password: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pnl_account.add(txt_password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        pnl_account.add(btn_createAccount, gbc);

        JPanel pnl_pnls = new JPanel();

        pnl_pnls.add(pnl_account);
        pnl_account.setBackground(new Color(35, 0, 35));
        pnl_account.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_pnls.add(pnl_personalInfo);
        pnl_personalInfo.setBackground(new Color(35, 0, 35));
        pnl_personalInfo.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_pnls.add(pnl_reservation);
        pnl_reservation.setBackground(new Color(35, 0, 35));
        pnl_reservation.setBorder(new EmptyBorder(10, 10, 10, 10));

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
    editDelete(JPanel pnl_main) {
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
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        pnl_components.add(btn_delete, gbc);

        JButton btn_updateReservation = new JButton("Update Reservation");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        pnl_components.add(btn_updateReservation, gbc);

        btn_updateReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editDeleteReservation update = new editDeleteReservation(Integer.parseInt(txt_id.getText()));
                update.btn_back.setVisible(false);
                update.btn_delete.setVisible(false);

                update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
        });

        btn_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new deleteUserData().deleteAll(txt_id.getText());
                    JOptionPane.showMessageDialog(new JFrame(), "All data related to the user ID was deleted.");

                } catch (Exception ex) {
                    System.out.println("DELETE ERR: " + ex);
                    JOptionPane.showMessageDialog(new JFrame(), "FAILED TO DELETE RECORDS:" + ex);
                }
            }
        });

        this.add(pnl_components, BorderLayout.CENTER);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    try {
                        BorderLayout layout = (BorderLayout) pnl_main.getLayout();
                        pnl_main.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                    JInternalFrame InternalFrm = new customQueries(

                            "SELECT * FROM ClientInfo\n" + //
                                    "LEFT OUTER JOIN Reservations\n" + //
                                    "ON Reservations.clientID = ClientInfo.clientId\n" + //
                                    "LEFT OUTER JOIN user_accounts\n" + //
                                    "ON ClientInfo.accountNumber = user_accounts.accountID\n" + //
                                    "WHERE ClientInfo.clientId = \n" + txt_id.getText() + //
                                    ";");
                    InternalFrm.setResizable(true);
                    InternalFrm.setPreferredSize(new Dimension(WIDTH, 100));
                    InternalFrm.setBorder(new EmptyBorder(0, 0, 0, 0));
                    InternalFrm.setTitle("Info about Client#" + txt_id.getText());
                    ;
                    pnl_main.add(InternalFrm,
                            BorderLayout.SOUTH);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "AN ERROR OCCURED. MAKE SURE YOU ENTERED A VALID CLIENT ID\n" + ex);
                }

            }
        });
    }
}

class deleteUserData {
    public void deleteAll(String ID) throws Exception {
        try {
            database db = new database();
            ArrayList clientInfo = db.customQueries("SELECT accountID FROM ClientInfo WHERE clientId = " + ID).get(0);
            int listMaxIndex = clientInfo.size() - 1;
            db.customActionQuery("DELETE FROM Reservations WHERE clientID = " + ID + ";");
            db.customActionQuery("DELETE FROM user_accounts WHERE accountID = "
                    + String.valueOf(clientInfo.get(listMaxIndex)));
            // queryWithID(Integer.parseInt(ID), "ClientInfo", "clientId").getLast()));
            db.customActionQuery("DELETE FROM ClientInfo WHERE clientId = " + ID + ";");
        } catch (Exception ex) {
            System.out.println(ex);
        }
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