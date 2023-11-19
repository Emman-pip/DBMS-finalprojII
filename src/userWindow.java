import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class userWindow extends JFrame {
    JFrame frm = this;

    userWindow(int ID) {
        new flatlaf();
        // this.setUndecorated(false);
        JPanel pnl_main = new JPanel();
        // pnl_main.setBackground(Color.GREEN);
        try {
            LinkedList<String> dataCheck = new database().queryWithID(ID, "Reservations", "clientID");
            if (dataCheck.size() == 0) {
                pnl_main.setLayout(new BorderLayout());

                JPanel pnl_form = new JPanel();

                pnl_form.add(new JLabel("You don't have a reservation yet. Make one with us!"));
                reservationForm rf = new reservationForm();
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
        this.add(pnl_main);
        this.setTitle("WELCOME");
        this.setSize(700, 600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new userWindow(2);
    }
}

class userWindowContent extends JPanel {
    userWindowContent() {
        System.out.println("pipiopopipi");
    }
}

class reservationForm extends JInternalFrame {

    reservationForm() {
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
        String[] packageList = { "Grange Pool Villa", "Petrus Pool Villa", "Shiraz Suite RoomShiraz Suite Room",
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
            daysList[i] = String.valueOf(i + 1);
        }

        JComboBox daysToChoose = new JComboBox(daysList);

        String[] monthsList = new String[12];
        for (int i = 0; i < 12; i++) {
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

        JComboBox daysToChoose2 = new JComboBox(daysList);

        JComboBox monthsToChoose2 = new JComboBox(monthsList);
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

        pnl_main.add(pnl_dateCheckin2, gbc);

        JButton btn_submit = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.ipady = 10;
        pnl_main.add(btn_submit, gbc);
        this.add(pnl_main);

        btn_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        this.setVisible(true);

    }
}

// TODO:
// check if account ID is possesed by one record in reservations -- create a
// query that will return this based on ID
// if there is none
// proceed to reservation form
// else
// show current reservations
// IN THE CURRENT RESERVATIONS:
// THE USER SHOULD BE ABLE TO DELETE AND UPDATE CURRENT RESERVATIONS