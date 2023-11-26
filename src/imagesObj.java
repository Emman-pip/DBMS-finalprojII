import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class imagesObj {
    public Image logo;
    public Image outdoor;
    public Image packageicon;
    public Image grange;
    public Image petrus;
    public Image shiraz;
    public Image chardonn;
    public Image sauvig;
    public Image bordeaux;

    public void images() throws Exception {
        logo = ImageIO.read(getClass().getResource("./media/icon.png"));
        outdoor = ImageIO.read(getClass().getResource("./media/Untitled.png"));
        packageicon = ImageIO.read(getClass().getResource("./media/package.png"));
        grange = ImageIO.read(getClass().getResource("./media/grange.jpg"));
        petrus = ImageIO.read(getClass().getResource("./media/petrus.jpg"));
        shiraz = ImageIO.read(getClass().getResource("./media/shiraz.jpg"));
        chardonn = ImageIO.read(getClass().getResource("./media/chardonn.jpg"));
        sauvig = ImageIO.read(getClass().getResource("./media/sauvignon.jpg"));
        bordeaux = ImageIO.read(getClass().getResource("./media/bordeux.jpg"));

    }

    public Image getScaledImage(Image srcImg, int w, int h) {

        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
