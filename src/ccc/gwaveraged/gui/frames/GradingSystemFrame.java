package ccc.gwaveraged.gui.frames;

import javax.swing.JFrame;

import ccc.gwaveraged.gui.layouts.GradingSystemLayout;

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
