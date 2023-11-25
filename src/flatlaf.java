import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
// import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

public class flatlaf {
    flatlaf() {
        try {
            FlatDarkPurpleIJTheme fl = new FlatDarkPurpleIJTheme();
            // FlatLightLaf fl = new FlatLightLaf();
            // fl.installColorsAndFont(null, null, null, null);
            UIManager.setLookAndFeel(fl);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
    }
}
