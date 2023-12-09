import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ArrayList;

public class signUp extends JFrame {
    JFrame frm = this;

    signUp() {
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        new flatlaf();
        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout());
        pnl.add(new signUpForm(frm));
        this.add(pnl);
        this.setTitle("SIGN UP");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

class signUpForm extends JPanel {
    int customerID;
    JPasswordField txt_password;
    JTextField txt_username;
    JPanel pnl = this;
    JButton btn_back;

    signUpForm(JFrame frm) {
        JLabel lbl_username = new JLabel("new username: ");
        txt_username = new JTextField();
        txt_username.setColumns(20);
        lbl_username.setLabelFor(txt_username);

        JLabel lbl_password = new JLabel("new password: ");
        txt_password = new JPasswordField();
        txt_password.setColumns(20);

        JButton btn_signup = new JButton("Sign up");
        btn_back = new JButton("back");

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

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.ipady = 20;
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(btn_back, gbc);

        btn_signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    LinkedList<ArrayList<Object>> data = new database().customQueries("SELECT * FROM user_accounts");
                    for (int i = 0; i < data.size(); i++) {
                        if (txt_username.getText().equals(data.get(i).get(1))) {
                            customerID = Integer.parseInt(data.get(i).get(0).toString());
                            JOptionPane.showMessageDialog(new JFrame(), "pick another username");
                            throw new Exception("ERROR");
                        } else if (txt_username.getText().equals("")
                                || String.valueOf(txt_password.getPassword()).equals("")) {
                            JOptionPane.showMessageDialog(new JFrame(), "enter a username/password");
                            throw new Exception("ERROR");
                        } else if (!(txt_username.getText().equals(data.get(i).get(1)))
                                && i + 1 == data.size()) {
                            new database().customActionQuery("INSERT INTO user_accounts(username, password) VALUES ('"
                                    + txt_username.getText() + "', '" + txt_password.getPassword() + "');");
                            System.out.println("YAY ID:" + customerID);
                            btn_signup.setEnabled(false);
                            gbc.gridx = 0;
                            gbc.gridy = 3;
                            gbc.gridwidth = 2;
                            gbc.ipady = 20;
                            gbc.insets = new Insets(10, 0, 0, 0);
                            gbc.fill = GridBagConstraints.HORIZONTAL;
                            txt_username.setEnabled(false);
                            txt_password.setEnabled(false);
                            btn_back.setVisible(false);
                            pnl.add(new personalInfoForm(txt_username, txt_password, frm), gbc);

                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "PLEASE CHOOSE ANOTHER USERNAME");
                }
            }
        });
        btn_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frm.dispose();
                new landingWIndow();
            }
        });
    }
}
