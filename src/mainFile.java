import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainFile extends JFrame {
    JFrame frm = this;

    mainFile() {
        new flatlaf();
        JPanel pnl = new JPanel();
        JButton btn = new JButton("START APP");
        pnl.setLayout(new GridBagLayout());
        pnl.add(btn);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frm.dispose();
                new landingWIndow();
            }
        });
        this.add(pnl);
        this.setSize(300, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new mainFile();
    }
}

// DESIGN THE FRONT END!!!! (layout nalang) - partially done
// ADD PRICE SA RESERVATION - partial done
// TODO: MAKE A WAY FOR THE TABLES TO BE SEEN WHEN EDITING/UPDATING/ADDING
// RECORDS (add sa east ng borderlayout)
// TODO: redesign the add records admin form