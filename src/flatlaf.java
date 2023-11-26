import javax.swing.UIManager;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

public class flatlaf {
    flatlaf() {
        try {
            FlatDarkPurpleIJTheme fl = new FlatDarkPurpleIJTheme();
            UIManager.setLookAndFeel(fl);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
    }
}
