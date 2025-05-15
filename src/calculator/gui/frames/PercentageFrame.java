package calculator.gui.frames;

import javax.swing.JFrame;

import calculator.gui.layouts.PercentageLayout;

public class PercentageFrame extends JFrame {
    public PercentageFrame() {
        super();

        this.setContentPane(new PercentageLayout());
        this.setSize(250, 325);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
