import java.util.LinkedList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class editDeleteReservation extends JFrame {
    JFrame frm = this;
    JButton btn_back;

    editDeleteReservation(int clientID) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        new flatlaf();
        JPanel pnl_main = new JPanel();
        pnl_main.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.add(pnl_main);
        this.setTitle("Edit reservation");
        try {
            LinkedList<String> reservation = new database().queryWithID(clientID, "Reservations", "clientID");

            JPanel pnl_fields = new JPanel();
            JTextField txt_type = new JTextField();
            txt_type.setText(reservation.get(1));
            JTextField txt_packageName = new JTextField();
            txt_packageName.setText(reservation.get(2));
            // continue displaying the data on the text fields so that the user can delete
            // the records or update.
            JTextField txt_checkIn = new JTextField();
            txt_checkIn.setText(reservation.get(3));
            JTextField txt_departure = new JTextField();

            txt_departure.setText(reservation.get(4));
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
            JButton btn_delete = new JButton("Delete Reservation");
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
                        new database().updateReservation(String.valueOf(clientID), txt_type.getText(),
                                txt_packageName.getText(), txt_checkIn.getText(), txt_departure.getText());
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
                        new database().deleteReservation(
                                new database().queryWithID(clientID, "Reservations", "clientID").get(0));
                        JOptionPane.showMessageDialog(new JFrame(), "Reservation cancelled.");
                        LinkedList<String> accountID = new database().queryWithID(clientID, "ClientInfo", "clientId");
                        new userWindow(Integer.parseInt(accountID.get(10)));
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
                        LinkedList<String> accountID = new database().queryWithID(clientID, "ClientInfo", "clientId");
                        new userWindow(Integer.parseInt(accountID.get(10)));
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
        this.setVisible(true);
    }

    // public static void main(String[] args) {
    // new editDeleteReservation(18);
    // }
}