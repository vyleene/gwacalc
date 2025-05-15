package calculator.gui.abstracts;

import javax.swing.JPanel;

import calculator.gui.interfaces.PanelInterface;

public abstract class Panel extends JPanel implements PanelInterface {
    public Panel() {
        super();
    }

    public abstract void createComponents();
    public abstract void startListeners();

    public void showWarningMessage(String message) {
        javax.swing.JOptionPane.showMessageDialog(
            null,
            message,
            "Warrning",
            javax.swing.JOptionPane.WARNING_MESSAGE
        );
    }

    public void showErrorMessage(String message) {
        javax.swing.JOptionPane.showMessageDialog(
            null,
            message,
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
}