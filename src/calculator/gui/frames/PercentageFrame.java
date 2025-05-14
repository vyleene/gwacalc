package calculator.gui.frames;

import javax.swing.JFrame;

import calculator.gui.layouts.PercentageLayout;

public class PercentageFrame extends JFrame {
    public PercentageFrame() {
        super();

        this.setContentPane(new PercentageLayout());
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
