package ccc.gwaveraged;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ccc.gwaveraged.backend.Config;
import ccc.gwaveraged.gui.GUI;

public class GWAveraged {
    public static void setLaF() {
        try {
            UIManager.setLookAndFeel(
                (Config.isDarkMode) ? new com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme()
                : new com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme()
            );
        } catch (Exception ex) {
            System.out.println("LaF can not be initialized.");
        }
    }

    public static void main(String[] args) throws Exception {
        Config.initialize();
        
        setLaF();
        SwingUtilities.invokeLater(() -> {
            new GUI();
        });
    }
}
