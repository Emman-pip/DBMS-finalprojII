import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class signUp extends JFrame {
    signUp() {
        new flatlaf();
        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout());
        pnl.add(new signUpForm());
        this.add(pnl);
        this.setTitle("SIGN UP");
        this.setSize(800, 600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new signUp();
    }
}

class signUpForm extends JPanel {
    int customerID;
    JPasswordField txt_password;
    JTextField txt_username;
    JPanel pnl = this;

    signUpForm() {
        JLabel lbl_username = new JLabel("new username: ");
        txt_username = new JTextField();
        txt_username.setColumns(20);
        lbl_username.setLabelFor(txt_username);

        JLabel lbl_password = new JLabel("new password: ");
        txt_password = new JPasswordField();
        txt_password.setColumns(20);

        JButton btn_signup = new JButton("Sign up");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(lbl_username, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(txt_username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(lbl_password, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(txt_password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.ipady = 20;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(btn_signup, gbc);

        // JInternalFrame internal = new JInternalFrame();

        btn_signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    LinkedList<LinkedList<String>> data = new database().queries("user_accounts");
                    for (int i = 0; i < data.size(); i++) {

                        if (txt_username.getText().equals(data.get(i).get(1))) {
                            customerID = Integer.parseInt(data.get(i).get(0));
                            JOptionPane.showMessageDialog(new JFrame(), "pick another username");
                        } else if (txt_username.getText().equals("")
                                || String.valueOf(txt_password.getPassword()).equals("")) {
                            JOptionPane.showMessageDialog(new JFrame(), "enter a username/password");
                        } else if (!(txt_username.getText().equals(data.get(i).get(1)))
                                && i + 1 == data.size()) {
                            System.out.println("YAY ID:" + customerID);
                            btn_signup.setEnabled(false);
                            gbc.gridx = 0;
                            gbc.gridy = 3;
                            gbc.gridwidth = 2;
                            gbc.ipady = 20;
                            gbc.insets = new Insets(10, 0, 0, 0);
                            gbc.fill = GridBagConstraints.HORIZONTAL;

                            pnl.add(new personalInfoForm(txt_username, txt_password), gbc);

                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }
}

class personalInfoForm extends JInternalFrame {
    // name gender, age, email, cp, landline, address, nationality, reason
    personalInfoForm(JTextField username, JPasswordField password) {
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
        gbc.gridy = 9;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridwidth = 2;
        pnl.add(btn_submit, gbc);
        this.add(pnl);
        this.setSize(300, 300);
        this.setVisible(true);
    }
}