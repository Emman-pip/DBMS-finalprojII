import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;

public class flatlaf {
    flatlaf() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
    }
}
