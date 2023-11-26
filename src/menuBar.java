import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class menuBar {
    int imgW = 200;
    int imgH = 150;

    public JMenuBar menuB() {
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Packages Info");
        try {
            imagesObj imgs = new imagesObj();
            imgs.images();
            menu.setIcon(new ImageIcon(imgs.getScaledImage(imgs.packageicon, 50, 50)));
        } catch (Exception e) {
            System.out.println("FAILED TO LOAD ICONS: " + e);
        }
        JMenu menu2 = new JMenu("Help");

        JMenuItem menu_item1 = new JMenuItem("Grange Pool Villa (1)");
        try {
            imagesObj imgs = new imagesObj();
            imgs.images();
            menu_item1.setIcon(new ImageIcon(imgs.getScaledImage(imgs.grange, imgW, imgH)));

        } catch (Exception e) {
            System.out.println(e);
        }

        menu_item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("works");
                    JOptionPane.showMessageDialog(new JFrame(),
                            new database().queryWithID(1, "Packages", "packageId").get(2));
                } catch (Exception ev) {
                    System.out.println(ev);
                }
            }
        });

        JMenuItem menu_item2 = new JMenuItem("Petrus Pool Villa (2)");
        try {
            imagesObj imgs = new imagesObj();
            imgs.images();
            menu_item2.setIcon(new ImageIcon(imgs.getScaledImage(imgs.petrus, imgW, imgH)));

        } catch (Exception e) {
            System.out.println(e);
        }
        menu_item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("works");
                    JOptionPane.showMessageDialog(new JFrame(),
                            new database().queryWithID(2, "Packages", "packageId").get(2));
                } catch (Exception ev) {
                    System.out.println(ev);
                }
            }
        });
        JMenuItem menu_item3 = new JMenuItem("Shiraz Suite Room (3)");
        try {
            imagesObj imgs = new imagesObj();
            imgs.images();
            menu_item3.setIcon(new ImageIcon(imgs.getScaledImage(imgs.shiraz, imgW, imgH)));

        } catch (Exception e) {
            System.out.println(e);
        }
        menu_item3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("works");
                    JOptionPane.showMessageDialog(new JFrame(),
                            new database().queryWithID(3, "Packages", "packageId").get(2));
                } catch (Exception ev) {
                    System.out.println(ev);
                }
            }
        });
        JMenuItem menu_item4 = new JMenuItem("Chardonnay Suite Room (4)");
        try {
            imagesObj imgs = new imagesObj();
            imgs.images();
            menu_item4.setIcon(new ImageIcon(imgs.getScaledImage(imgs.chardonn, imgW, imgH)));

        } catch (Exception e) {
            System.out.println(e);
        }
        menu_item4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("works");
                    JOptionPane.showMessageDialog(new JFrame(),
                            new database().queryWithID(4, "Packages", "packageId").get(2));
                } catch (Exception ev) {
                    System.out.println(ev);
                }
            }
        });
        JMenuItem menu_item5 = new JMenuItem("Sauvignon Grand Villa (5)");
        try {
            imagesObj imgs = new imagesObj();
            imgs.images();
            menu_item5.setIcon(new ImageIcon(imgs.getScaledImage(imgs.sauvig, imgW, imgH)));

        } catch (Exception e) {
            System.out.println(e);
        }
        menu_item5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("works");
                    JOptionPane.showMessageDialog(new JFrame(),
                            new database().queryWithID(5, "Packages", "packageId").get(2));
                } catch (Exception ev) {
                    System.out.println(ev);
                }
            }
        });
        JMenuItem menu_item6 = new JMenuItem("Bordeaux Grand Villa (6)");
        try {
            imagesObj imgs = new imagesObj();
            imgs.images();
            menu_item6.setIcon(new ImageIcon(imgs.getScaledImage(imgs.bordeaux, imgW, imgH)));

        } catch (Exception e) {
            System.out.println(e);
        }
        menu_item6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("works");
                    JOptionPane.showMessageDialog(new JFrame(),
                            new database().queryWithID(6, "Packages", "packageId").get(2));
                } catch (Exception ev) {
                    System.out.println(ev);
                }
            }
        });

        menu.add(menu_item1);
        menu.add(menu_item2);
        menu.add(menu_item3);
        menu.add(menu_item4);
        menu.add(menu_item5);
        menu.add(menu_item6);

        mb.add(menu);
        mb.add(menu2);
        return mb;
    }
}
