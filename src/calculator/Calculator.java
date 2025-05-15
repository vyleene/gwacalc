package calculator;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import calculator.backend.Config;
import calculator.gui.GUI;

public class Calculator {
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
