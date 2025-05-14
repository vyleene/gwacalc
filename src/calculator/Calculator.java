package calculator;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


import calculator.backend.Config;
import calculator.gui.GUI;

public class Calculator {
    public static void main(String[] args) throws Exception {
        new Config();
        try {
            UIManager.setLookAndFeel(new calculator.gui.themes.FlatArcDarkOrangeIJTheme());
        } catch (Exception ex) {
            System.out.println("LaF can not be initialized.");
        }
        SwingUtilities.invokeLater(() -> {
            new GUI();
        });
    }
}
