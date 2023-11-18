import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        pnl_left2.add(new adminLogin());

        pnl_left.add(new loginWindow());
        pnl_main.add(new designPanel());
        tb.addTab("USER LOGIN", pnl_left);
        tb.addTab("ADMIN LOGIN", pnl_left2);
        pnl_main.add(tb);

        this.add(pnl_main);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new landingWIndow();
    }
}

class designPanel extends JPanel {
    designPanel() {
        JLabel lbl_sample = new JLabel("new label");
        this.add(lbl_sample);
    }
}

class loginWindow extends JPanel {
    public JLabel lbl_username;
    public JTextField txt_username;
    public JLabel lbl_pass;
    public JPasswordField txt_pass;
    public int customerID;

    public JButton btn_login;

    loginWindow() {
        this.setLayout(new GridLayout(3, 1));
        JPanel pnl_username = new JPanel();
        JLabel lbl_username = new JLabel("Username:");
        txt_username = new JTextField();
        txt_username.setColumns(20);

        JPanel pnl_pass = new JPanel();
        JLabel lbl_pass = new JLabel("Password:");
        txt_pass = new JPasswordField();
        txt_pass.setColumns(20);

        btn_login = new JButton("LOGIN");

        btn_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    LinkedList<LinkedList<String>> data = new database().queries("user_accounts");
                    for (int i = 0; i < data.size(); i++) {

                        if (txt_username.getText().equals(data.get(i).get(1))
                                && String.valueOf(txt_pass.getPassword()).equals(data.get(i).get(2))) {
                            customerID = Integer.parseInt(data.get(i).get(0));
                            System.out.println("YAY ID:" + customerID);
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "INVALID CREDENTIALS");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        pnl_username.add(lbl_username);
        pnl_username.add(txt_username);
        pnl_pass.add(lbl_pass);
        pnl_pass.add(txt_pass);

        this.add(pnl_username);
        this.add(pnl_pass);
        this.add(btn_login);

    }
}

class adminLogin extends JPanel {
    public JLabel lbl_username;
    public JTextField txt_username;
    public JLabel lbl_pass;
    public JPasswordField txt_pass;

    public JButton btn_login;

    adminLogin() {
        this.setLayout(new GridLayout(3, 1));
        JPanel pnl_username = new JPanel();
        JLabel lbl_username = new JLabel("ADMIN:");
        txt_username = new JTextField();
        txt_username.setColumns(20);

        JPanel pnl_pass = new JPanel();
        JLabel lbl_pass = new JLabel("PASS:");
        txt_pass = new JPasswordField();
        txt_pass.setColumns(20);

        btn_login = new JButton("LOGIN");

        pnl_username.add(lbl_username);
        pnl_username.add(txt_username);
        pnl_pass.add(lbl_pass);
        pnl_pass.add(txt_pass);

        btn_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    if (txt_username.getText().equals("ADMIN")
                            && String.valueOf(txt_pass.getPassword()).equals("ADMIN")) {
                        System.out.println("YAY WELCOME ADMIN");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "INVALID CREDENTIALS");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        this.add(pnl_username);
        this.add(pnl_pass);
        this.add(btn_login);
    }
}