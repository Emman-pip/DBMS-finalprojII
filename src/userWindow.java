import javax.swing.*;

import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class userWindow extends JFrame {
    JFrame frm = this;

    userWindow(int ID) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        new flatlaf();
        // this.setUndecorated(false);
        JPanel pnl_main = new JPanel();
        this.add(pnl_main);

        // pnl_main.setBackground(Color.GREEN);
        try {
            String id = "";
            LinkedList<String> dataCheck = new LinkedList<String>();
            try {
                id = new database().queryWithID(ID, "ClientInfo", "accountNumber").getFirst();
                dataCheck = new database().queryWithID(Integer.parseInt(id), "Reservations", "clientID");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "You do not have a personal data record yet. Please fill out the form.");
                try {
                    JTextField user = new JTextField();
                    user.setText(new database().queryWithID(ID, "user_accounts", "accountID").get(1));
                    JPasswordField pass = new JPasswordField();
                    pass.setText(new database().queryWithID(ID, "user_accounts", "accountID").get(2));
                    JFrame frm_form = new JFrame();
                    frm_form.add(new personalInfoForm(user, pass, frm_form));
                    frm_form.setSize(600, 550);
                    frm_form.setVisible(true);

                } catch (Exception ex) {
                    System.out.println(ex);
                }
                return;

            }
            if (dataCheck.size() == 0 || id.equals("")) {
                pnl_main.setLayout(new BorderLayout());
                // System.out.println("YOURE IN!!");
                JPanel pnl_form = new JPanel();

                pnl_form.add(new JLabel(
                        "You don't have a reservation yet. Make one with us!"));
                // pnl_form.add(new JLabel("Each client is limited to one reservation only."));

                pnl_form.add(
                        new JLabel(
                                "We only entertain same month reservations due to monthly maintenance of the Villas and Resort."));

                reservationForm rf = new reservationForm(ID, this);
                pnl_form.add(rf);
                pnl_main.add(pnl_form);
            } else {
                pnl_main.setLayout(new BorderLayout());
                JPanel pnl_layout = new JPanel();
                // pnl_layout.setBackground(Color.GRAY);

                JPanel pnl_table = new JPanel();
                String[] column = { "ReservationID", "Type of reservation", "Package ID", "Check in Date",
                        "Departure Date" };
                Object[] objArr = dataCheck.toArray();
                String[] stringArr = new String[objArr.length];
                for (int i = 0; i < stringArr.length; i++) {
                    stringArr[i] = String.valueOf(objArr[i]);
                }
                String[][] content = { stringArr };
                System.out.println(column);
                JTable tbl_reservation = new JTable(content, column);
                // tbl_reservation.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                JScrollPane sp = new JScrollPane(tbl_reservation);
                pnl_table.setLayout(new BorderLayout());
                pnl_table.add(sp);
                JButton btn_edit = new JButton("edit/delete reservation");
                GridBagLayout gbl = new GridBagLayout();
                pnl_layout.setLayout(gbl);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.weightx = 1.0;
                // gbc.weighty = 1.0;

                gbc.gridx = 0;
                gbc.gridy = 0;
                pnl_layout.add(new JLabel("Your Reservation(s):"), gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                pnl_layout.add(pnl_table, gbc);
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.ipady = 20;
                gbc.insets = new Insets(10, 0, 0, 0);
                pnl_layout.add(btn_edit, gbc);
                pnl_main.add(pnl_layout, BorderLayout.CENTER);
                JButton btn_logout = new JButton("Logout");

                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.ipady = 10;
                pnl_layout.add(btn_logout, gbc);
                int clientID = Integer.parseInt(id);
                btn_edit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new editDeleteReservation(clientID);
                        frm.dispose();
                    }
                });
                btn_logout.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frm.dispose();
                        new landingWIndow();
                    }

                });
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        this.setTitle("WELCOME");
        this.setSize(650, 900);
        this.setVisible(true);
    }

    // public static void main(String[] args) {
    // new userWindow(18);
    // }
}

class userWindowContent extends JPanel {
    userWindowContent() {
        System.out.println("pipiopopipi");
    }
}

class reservationForm extends JInternalFrame {

    reservationForm(int ID, JFrame frm) {
        this.setTitle("RESERVATION FORM");
        JPanel pnl_main = new JPanel();
        pnl_main.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // type of reservation
        JLabel lbl_type = new JLabel("Type of Reservation: ");
        String[] obj = { "Confirmed/Guaranteeed Reservation", "Tentative Reservation" };
        // tentative reservations will be cut off after 3 days without confirmation
        JComboBox types = new JComboBox(obj);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 0, 0);

        pnl_main.add(lbl_type, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        // gbc.gridwidth = 3;
        gbc.gridy = 0;
        pnl_main.add(types, gbc);
        // packageID
        JLabel lbl_packageChosen = new JLabel("Package: ");
        String[] packageList = { "Grange Pool Villa", "Petrus Pool Villa", "Shiraz Suite Room",
                "Chardonnay Suite Room", "Sauvignon Grand Villa", "Bordeaux Grand Villa" };
        // edit content based on DB
        JComboBox packagesToChoose = new JComboBox(packageList);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);

        pnl_main.add(lbl_packageChosen, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnl_main.add(packagesToChoose, gbc);

        // checkinDate
        JLabel lbl_date = new JLabel("Check-in Date: ");
        String[] datesList = new String[200];
        for (int i = 0; i < 200; i++) {
            datesList[i] = String.valueOf(i + 2023);
        }

        JComboBox datesToChoose = new JComboBox(datesList);

        String[] daysList = new String[31];
        for (int i = 0; i < 31; i++) {
            if (i + 1 < 10) {
                daysList[i] = 0 + String.valueOf(i + 1);
                continue;
            }
            daysList[i] = String.valueOf(i + 1);
        }

        JComboBox daysToChoose = new JComboBox(daysList);

        String[] monthsList = new String[12];
        for (int i = 0; i < 12; i++) {
            if (i + 1 < 10) {
                monthsList[i] = 0 + String.valueOf(i + 1);
                continue;
            }
            monthsList[i] = String.valueOf(i + 1);
        }
        JComboBox monthsToChoose = new JComboBox(monthsList);
        JPanel pnl_dateCheckin = new JPanel();
        pnl_dateCheckin.setLayout(new FlowLayout());
        pnl_dateCheckin.add(monthsToChoose);
        pnl_dateCheckin.add(daysToChoose);
        pnl_dateCheckin.add(datesToChoose);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);

        pnl_main.add(lbl_date, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);

        pnl_main.add(pnl_dateCheckin, gbc);

        // departureDate
        JLabel lbl_date2 = new JLabel("Departure Date: ");
        String[] datesList2 = new String[200];
        for (int i = 0; i < 200; i++) {
            datesList2[i] = String.valueOf(i + 2023);
        }

        JComboBox datesToChoose2 = new JComboBox(datesList);
        datesToChoose2.setEnabled(false);

        datesToChoose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datesToChoose2.setSelectedItem(datesToChoose.getSelectedItem());
            }
        });

        JComboBox daysToChoose2 = new JComboBox(daysList);

        JComboBox monthsToChoose2 = new JComboBox(monthsList);

        monthsToChoose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                monthsToChoose2.setSelectedItem(monthsToChoose.getSelectedItem());
            }
        });

        monthsToChoose2.setEnabled(false);
        JPanel pnl_dateCheckin2 = new JPanel();
        pnl_dateCheckin2.setLayout(new FlowLayout());
        pnl_dateCheckin2.add(monthsToChoose2);
        pnl_dateCheckin2.add(daysToChoose2);
        pnl_dateCheckin2.add(datesToChoose2);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 0, 0);

        pnl_main.add(lbl_date2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 0, 0);

        String[][] checkSchedList;
        try {
            LinkedList<LinkedList<String>> data = new database().queries("Reservations");
            System.out.println(data.size());
            JPanel reservedDates = new JPanel();
            String[] columns = {
                    "From", "Until"
            };
            String[][] content = new String[data.size()][1];
            for (int i = 0; i < data.size(); i++) {
                String[] record = new String[2];
                record[0] = data.get(i).get(3);
                record[1] = data.get(i).get(4);
                content[i] = record;
            }
            checkSchedList = content.clone();
            for (String[] i : content) {
                System.out.println(i);
            }
            JTable tbl = new JTable(content, columns);
            JScrollPane sp = new JScrollPane(tbl);
            // sp.setBounds(0, 0, WIDTH, 30);
            reservedDates.setLayout(new BorderLayout());
            reservedDates.add(new JLabel("Possible unavailable dates"), BorderLayout.NORTH);
            reservedDates.add(sp, BorderLayout.CENTER);
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.ipady = 10;
            pnl_main.add(reservedDates, gbc);

            JButton btn_refresh_dates = new JButton("See all scedules for your package");
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 2;
            gbc.ipady = 10;
            pnl_main.add(btn_refresh_dates, gbc);

            gbc.gridx = 1;
            gbc.gridy = 8;
            // gbc.gridwidth = 1;
            JPanel pnl_price = new JPanel();
            pnl_main.add(pnl_price, gbc);

            btn_refresh_dates.addActionListener(new ActionListener() {

                JPanel pnl_date = new JPanel();

                public void actionPerformed(ActionEvent e) {
                    // if (e.MOUSE_MOTION_EVENT_MASK){}
                    String packageToID;

                    String packageID = String.valueOf(packagesToChoose.getSelectedItem());
                    if (packageID.equals("Grange Pool Villa")) {
                        packageToID = "1";
                    } else if (packageID.equals("Petrus Pool Villa")) {
                        packageToID = "2";
                    } else if (packageID.equals("Shiraz Suite Room")) {
                        packageToID = "3";
                    } else if (packageID.equals("Chardonnay Suite Room")) {
                        packageToID = "4";
                    } else if (packageID.equals("Sauvignon Grand Villa")) {
                        packageToID = "5";
                    } else {
                        packageToID = "6";
                    }
                    JInternalFrame iF = new JInternalFrame();

                    pnl_date.removeAll();
                    try {
                        // pnl_date.remove(iF);
                        pnl_date.setLayout(new BorderLayout());
                        iF = new customQueries(
                                "SELECT checkinDate, departureDate FROM Reservations WHERE packageID = " + packageToID);
                        iF.setTitle("Reserved dates for " + String.valueOf(packagesToChoose.getSelectedItem()));
                        System.out.println(pnl_date.getComponentCount());
                        pnl_date.add(iF, BorderLayout.CENTER);

                        gbc.gridx = 0;
                        gbc.gridy = 4;
                        gbc.gridwidth = 2;
                        gbc.ipady = 400;
                        pnl_main.add(pnl_date, gbc);
                        reservedDates.setVisible(false);
                        pnl_date.setVisible(false);
                        pnl_date.setVisible(true);

                        pnl_price.removeAll();
                        pnl_price.add(new JLabel("Total price: " + new database()
                                .queryWithID(Integer.parseInt(packageToID), "Packages", "packageId").getLast()
                                + " x days stayed"));

                        pnl_price.setVisible(false);
                        pnl_price.setVisible(true);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "SQL ERROR: " + ex);

                    }
                }
            });

            // frm.setSize(700, 800);

        } catch (Exception e) {
            System.out.println(e);
            checkSchedList = new String[0][0];
        }
        if (checkSchedList.length == 0) {
            try {
                throw new Exception("ERROR");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        // for (String[] i : checkSchedList) {
        // System.out.println(i[0] + " " + i[1]);
        // }
        // n*[-]n*[-]n*
        // REGEX EXPRESSION HERE TO FIND CONFLICTS;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 0, 0);
        pnl_main.add(pnl_dateCheckin2, gbc);

        JButton btn_submit = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.ipady = 10;
        pnl_main.add(btn_submit, gbc);
        this.add(pnl_main);

        String[][] allDatesArr = checkSchedList.clone();

        btn_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LinkedList<LinkedList<String>> data = new database().queries("Reservations");
                    LinkedList<LinkedList<String>> relatedData = new LinkedList<LinkedList<String>>();
                    String packageToID;
                    String packageID = String.valueOf(packagesToChoose.getSelectedItem());
                    if (packageID.equals("Grange Pool Villa")) {
                        packageToID = "1";
                    } else if (packageID.equals("Petrus Pool Villa")) {
                        packageToID = "2";
                    } else if (packageID.equals("Shiraz Suite Room")) {
                        packageToID = "3";
                    } else if (packageID.equals("Chardonnay Suite Room")) {
                        packageToID = "4";
                    } else if (packageID.equals("Sauvignon Grand Villa")) {
                        packageToID = "5";
                    } else {
                        packageToID = "6";
                    }
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).get(2).equals(packageToID)) {
                            relatedData.add(data.get(i));
                        }
                    }
                    String[][] content = new String[relatedData.size()][2];
                    for (int i = 0; i < relatedData.size(); i++) {
                        String[] record = new String[2];
                        record[0] = relatedData.get(i).get(3);
                        record[1] = relatedData.get(i).get(4);
                        content[i] = record;
                    }

                    LinkedList<String> allReservedDates = new dates().fillDates(content);
                    System.out.println("DATES: " + allReservedDates);
                    // System.err.println(allReservedDates);
                    if (allReservedDates.contains(String.valueOf(datesToChoose.getSelectedItem()) + "-" +
                            String.valueOf(monthsToChoose.getSelectedItem()) + "-" +
                            String.valueOf(daysToChoose.getSelectedItem()))
                            || allReservedDates.contains(String.valueOf(datesToChoose2.getSelectedItem()) + "-" +
                                    String.valueOf(monthsToChoose2.getSelectedItem()) + "-" +
                                    String.valueOf(daysToChoose2.getSelectedItem()))) {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "PLEASE RECHECK THE DATES. \nOur system has detected a date conflict.");

                        return;
                    }
                    // System.out.println((datesToChoose.getSelectedItem()) + "-" +
                    // (monthsToChoose.getSelectedItem()) + "-" +
                    // (daysToChoose.getSelectedItem()));
                    new database().insertReservation(String.valueOf(types.getSelectedItem()),
                            String.valueOf(packagesToChoose.getSelectedItem()),
                            String.valueOf(datesToChoose.getSelectedItem()) + "-" +
                                    String.valueOf(monthsToChoose.getSelectedItem()) + "-" +
                                    String.valueOf(daysToChoose.getSelectedItem())

                            ,
                            String.valueOf(datesToChoose2.getSelectedItem()) + "-" +
                                    String.valueOf(monthsToChoose2.getSelectedItem()) + "-" +
                                    String.valueOf(daysToChoose2.getSelectedItem()),
                            String.valueOf(ID));
                    JOptionPane.showMessageDialog(new JFrame(),
                            "RESERVATION SUCCESSFULL. PLEASE LOGIN AGAIN TO CHECK FOR YOUR RESERVATION.");
                    frm.dispose();
                    new landingWIndow();

                } catch (Exception ex) {
                    System.out.println(ex);
                    return;
                }

            }
        });

        this.setVisible(true);

    }
}

// TODO:
// add functionality to the edit/delete button
// FIX BUG, ONLY CONFLICT IF SAME PACKAGE ID