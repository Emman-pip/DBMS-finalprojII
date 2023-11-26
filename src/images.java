import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class images {
    public Image logo;
    public Image outdoor;
    public Image logo2;

    public void images() throws Exception {
        logo = ImageIO.read(getClass().getResource("./media/icon.png"));
        outdoor = ImageIO.read(getClass().getResource("./media/Untitled.png"));
        logo2 = ImageIO.read(getClass().getResource("./media/logo.png"));
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
