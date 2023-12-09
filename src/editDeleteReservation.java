import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

import javax.swing.*;

public class editDeleteReservation extends JFrame {
    JFrame frm = this;
    JButton btn_back;
    JButton btn_delete;

    editDeleteReservation(int clientID) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        new flatlaf();
        JPanel pnl_master = new JPanel();
        pnl_master.setLayout(new BorderLayout());
        pnl_master.add(new menuBar().menuB(), BorderLayout.NORTH);
        JPanel pnl_main = new JPanel();
        pnl_main.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        pnl_master.add(pnl_main, BorderLayout.CENTER);
        this.add(pnl_master);
        this.setTitle("Edit reservation");
        try {
            ArrayList<Object> reservation = new database()
                    .customQueries("SELECT * FROM Reservations WHERE clientID =" + clientID).get(0);

            JPanel pnl_fields = new JPanel();
            JTextField txt_type = new JTextField();
            txt_type.setText(String.valueOf(reservation.get(1)));
            JTextField txt_packageName = new JTextField();
            txt_packageName.setText(String.valueOf(reservation.get(2)));

            JTextField txt_checkIn = new JTextField();
            txt_checkIn.setText(String.valueOf(reservation.get(3)));
            JTextField txt_departure = new JTextField();

            txt_departure.setText(String.valueOf(reservation.get(4)));
            pnl_fields.setLayout(new GridBagLayout());
            gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridy = 0;
            pnl_fields.add(new JLabel("Type of reservation: "), gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            pnl_fields.add(txt_type, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            pnl_fields.add(new JLabel("PackageID: "), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            pnl_fields.add(txt_packageName, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            pnl_fields.add(new JLabel("Check-in: "), gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            pnl_fields.add(txt_checkIn, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            pnl_fields.add(new JLabel("Departure: "), gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            pnl_fields.add(txt_departure, gbc);

            JPanel pnl_buttons = new JPanel();
            JButton btn_edit = new JButton("Edit Reservation");
            btn_delete = new JButton("Delete Reservation");
            btn_back = new JButton("Back");

            gbc.gridx = 0;
            gbc.gridy = 0;
            pnl_main.add(new JLabel("EDIT/DELETE RESERVATION"));

            gbc.gridx = 0;
            gbc.gridy = 1;
            pnl_main.add(pnl_fields, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;

            pnl_main.add(pnl_buttons, gbc);
            pnl_buttons.add(btn_edit);
            pnl_buttons.add(btn_delete);
            pnl_buttons.add(btn_back);

            btn_edit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        new database().customActionQuery("UPDATE Reservations SET typeOfReservation = '"
                                + txt_type.getText() + "', packageID = " + txt_packageName.getText()
                                + ", checkinDate = '" + txt_checkIn.getText() + "', departureDate = '"
                                + txt_departure.getText() + "' WHERE clientID = " + clientID);
                        JOptionPane.showMessageDialog(new JFrame(), "RESERVATION UPDATED SUCCESSFULLY.");
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(new JFrame(), "AN ERROR OCCURED.");

                    }
                }
            });

            btn_delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        new database().customActionQuery("DELETE FROM Reservations where clientID = " + clientID);
                        JOptionPane.showMessageDialog(new JFrame(), "Reservation cancelled.");
                        String accountID = String.valueOf(new database()
                                .customQueries("SELECT accountNumber FROM ClientInfo WHERE clientId =" + clientID)
                                .get(0).get(0));
                        new userWindow(Integer.parseInt(accountID));
                        frm.dispose();

                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(new JFrame(), "AN ERROR OCCURED.");

                    }
                }
            });

            btn_back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        String accountID = String.valueOf(new database()
                                .customQueries("SELECT accountNumber FROM ClientInfo WHERE clientId =" + clientID)
                                .get(0).get(0));
                        new userWindow(Integer.parseInt(accountID));
                        frm.dispose();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            });

        } catch (Exception e) {
            System.out.println(e);
        }
        this.setSize(500, 500);
        pnl_main.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}