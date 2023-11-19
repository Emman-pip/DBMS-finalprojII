import javax.swing.*;
import java.awt.*;

public class userWindow extends JFrame {
    userWindow(int ID) {
        new flatlaf();
        this.add(new reservationForm());
        this.setTitle("WELCOME");
        this.setSize(600, 400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new userWindow(1);
    }
}

class reservationForm extends JPanel {
    reservationForm() {
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
        String[] packageList = { "Package1", "Package2" };
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
        this.add(pnl_main);
    }
}