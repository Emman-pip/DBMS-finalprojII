import javax.swing.*;
import java.awt.*;

public class signUp extends JFrame {
    signUp() {
        new flatlaf();
        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout());
        pnl.add(new signUpForm());
        this.add(pnl);
        this.setTitle("SIGN UP");
        this.setSize(400, 400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new signUp();
    }
}

class signUpForm extends JPanel {
    signUpForm() {
        JLabel lbl_username = new JLabel("new username: ");
        JTextField txt_username = new JTextField();
        txt_username.setColumns(20);
        lbl_username.setLabelFor(txt_username);

        JLabel lbl_password = new JLabel("new password: ");
        JPasswordField txt_password = new JPasswordField();
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
    }
}