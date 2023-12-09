import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class landingWIndow extends JFrame {
    landingWIndow() {
        JPanel pnl_main = new JPanel();
        pnl_main.setLayout(new GridLayout(1, 2));
        new flatlaf();
        this.setTitle("Welcome to Vineyard, Tanauan");
        this.setSize(800, 600);

        JTabbedPane tb = new JTabbedPane();
        JPanel pnl_left = new JPanel();

        JPanel pnl_left2 = new JPanel();

        pnl_left2.add(new adminLogin(this));

        pnl_left.add(new loginWindow(this));
        pnl_main.add(new designPanel());
        tb.addTab("USER LOGIN", pnl_left);
        tb.addTab("ADMIN LOGIN", pnl_left2);
        pnl_main.add(tb);

        this.add(pnl_main);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

class designPanel extends JPanel {
    designPanel() {

        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);
        try {
            JLabel lbl_sample = new JLabel();
            imagesObj imgs = new imagesObj();
            imgs.images();
            lbl_sample.setIcon(new ImageIcon(imgs.logo));
            this.add(lbl_sample, BorderLayout.NORTH);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class loginWindow extends JPanel {
    public JLabel lbl_username;
    public JTextField txt_username;
    public JLabel lbl_pass;
    public JPasswordField txt_pass;
    public int customerID;

    public JButton btn_login;

    loginWindow(JFrame frm) {
        this.setLayout(new GridLayout(4, 1));
        JPanel pnl_username = new JPanel();
        JLabel lbl_username = new JLabel("Username:");
        txt_username = new JTextField();
        txt_username.setColumns(20);

        JPanel pnl_pass = new JPanel();
        JLabel lbl_pass = new JLabel("Password:");
        txt_pass = new JPasswordField();
        txt_pass.setColumns(20);

        btn_login = new JButton("LOGIN");
        JButton btn_createAcc = new JButton("Create Account");

        btn_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    LinkedList<ArrayList<Object>> data = new database().customQueries("SELECT * FROM user_accounts");
                    // queries("user_accounts");
                    for (int i = 0; i < data.size(); i++) {

                        if (txt_username.getText().equals(data.get(i).get(1))
                                && String.valueOf(txt_pass.getPassword()).equals(data.get(i).get(2))) {
                            customerID = Integer.parseInt(data.get(i).get(0).toString());
                            new userWindow(customerID);
                            frm.dispose();
                            return;
                        } else if ((!txt_username.getText().equals(data.get(i).get(1))
                                || !String.valueOf(txt_pass.getPassword()).equals(data.get(i).get(2)))
                                && i + 1 == data.size()) {
                            JOptionPane.showMessageDialog(new JFrame(), "INVALID CREDENTIALS");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        btn_createAcc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new signUp();
                frm.dispose();
            }
        });

        pnl_username.add(lbl_username);
        pnl_username.add(txt_username);
        pnl_pass.add(lbl_pass);
        pnl_pass.add(txt_pass);

        this.add(pnl_username);
        this.add(pnl_pass);
        this.add(btn_login);
        this.add(btn_createAcc);

    }
}

class adminLogin extends JPanel {
    public JLabel lbl_username;
    public JTextField txt_username;
    public JLabel lbl_pass;
    public JPasswordField txt_pass;

    public JButton btn_login;

    adminLogin(JFrame frm) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lbl_username = new JLabel("ADMIN:");
        txt_username = new JTextField();
        txt_username.setColumns(20);

        JLabel lbl_pass = new JLabel("PASS:");
        txt_pass = new JPasswordField();
        txt_pass.setColumns(20);

        btn_login = new JButton("LOGIN");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 0, 0);

        this.add(lbl_username, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 0, 0);

        this.add(txt_username, gbc);
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridy = 1;
        this.add(lbl_pass, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);

        this.add(txt_pass, gbc);

        btn_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    if (txt_username.getText().equals("ADMIN")
                            && String.valueOf(txt_pass.getPassword()).equals("ADMIN")) {
                        frm.dispose();
                        new adminWIndow();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "INVALID CREDENTIALS");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridwidth = 2;
        gbc.ipady = 20;
        this.add(btn_login, gbc);
    }
}