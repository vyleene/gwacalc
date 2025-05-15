package calculator.gui.frames;

import javax.swing.JFrame;

import calculator.gui.layouts.GradingSystemLayout;

public class GradingSystemFrame extends JFrame {
    public GradingSystemFrame() {
        super();

        this.setContentPane(new GradingSystemLayout());
        this.setSize(250, 325);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
