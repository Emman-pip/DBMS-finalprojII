import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class personalInfoForm extends JInternalFrame {
    // name gender, age, email, cp, landline, address, nationality, reason
    personalInfoForm(JTextField username, JPasswordField password, JFrame frm) {
        // System.out.println(username.getText() +
        // String.valueOf(password.getPassword()));
        JPanel pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lbl_name = new JLabel("Name: ");
        JTextField txt_name = new JTextField();

        JLabel lbl_gender = new JLabel("Gender: ");
        JTextField txt_gender = new JTextField();

        JLabel lbl_age = new JLabel("Age: ");
        JTextField txt_age = new JTextField();

        JLabel lbl_email = new JLabel("Email: ");
        JTextField txt_email = new JTextField();

        JLabel lbl_cp = new JLabel("Cell number: ");
        JTextField txt_cp = new JTextField();

        JLabel lbl_landline = new JLabel("Landline: ");
        JTextField txt_landline = new JTextField();

        JLabel lbl_address = new JLabel("Address: ");
        JTextField txt_address = new JTextField();

        JLabel lbl_nationality = new JLabel("Nationality: ");
        JTextField txt_nationality = new JTextField();

        JLabel lbl_reason = new JLabel("Reason for choosing us: ");
        JTextField txt_reason = new JTextField();

        JButton btn_submit = new JButton("Submit");

        JLabel[] labels = { lbl_name, lbl_gender, lbl_age, lbl_email, lbl_cp, lbl_landline, lbl_address,
                lbl_nationality, lbl_reason };
        JTextField[] fields = { txt_name, txt_gender, txt_age, txt_email, txt_cp, txt_landline, txt_address,
                txt_nationality, txt_reason };
        // System.out.println(fields.length);
        for (int i = 0; i < labels.length; i++) {
            fields[i].setColumns(20);
            gbc.gridx = 0;
            gbc.insets = new Insets(10, 0, 0, 0);
            gbc.gridy = i;
            pnl.add(labels[i], gbc);

            gbc.gridx = 1;
            gbc.insets = new Insets(10, 0, 0, 0);
            gbc.gridy = i;
            pnl.add(fields[i], gbc);
        }
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridwidth = 2;
        pnl.add(btn_submit, gbc);
        btn_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    for (int i = 0; i < fields.length; i++) {
                        if (fields[i].getText().equals("")) {
                            throw new Exception("PLEASE RECHECK ALL THE FIELDS");
                        }
                    }
                    new database().insertPersonalRecords(
                            username.getText(),
                            txt_name.getText(),
                            txt_gender.getText(),
                            txt_age.getText(),
                            txt_email.getText(),
                            txt_cp.getText(),
                            txt_landline.getText(),
                            txt_address.getText(),
                            txt_nationality.getText(),
                            txt_reason.getText());
                    JOptionPane.showMessageDialog(new JFrame(), "SIGN UP SUCCESSFUL\nLOGIN TO PROCEED TO RESERVATION");
                    frm.dispose();
                    new landingWIndow();
                } catch (Exception ex) {
                    System.err.println(ex);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "PLEASE MAKE SURE ALL THE FIELDS ARE FILLED AND YOU HAVE ENTERED THE RIGHT DATA.");
                }

            }
        });
        this.add(pnl);
        this.setSize(300, 300);
        this.setVisible(true);
    }
}
