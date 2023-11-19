import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class signUp extends JFrame {
    JFrame frm = this;

    signUp() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        new flatlaf();
        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout());
        pnl.add(new signUpForm(frm));
        this.add(pnl);
        this.setTitle("SIGN UP");
        this.setSize(800, 600);
        this.setVisible(true);
    }

    // public static void main(String[] args) {
    // new signUp();
    // }
}

class signUpForm extends JPanel {
    int customerID;
    JPasswordField txt_password;
    JTextField txt_username;
    JPanel pnl = this;

    signUpForm(JFrame frm) {
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
                            return;
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
                            new database().insertUserAccount(txt_username.getText(),
                                    String.valueOf(txt_password.getPassword()));
                            txt_username.setEnabled(false);
                            txt_password.setEnabled(false);
                            pnl.add(new personalInfoForm(txt_username, txt_password, frm), gbc);

                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }
}
