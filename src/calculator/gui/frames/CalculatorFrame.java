package calculator.gui.frames;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import calculator.gui.layouts.CalculatorLayout;

public class CalculatorFrame extends JFrame {
    public CalculatorFrame(String title) {
        super(title);

        this.setContentPane(new CalculatorLayout());
        this.setSize(600, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu moreMenu = new JMenu("Menu");
        JMenuItem percentageItem = new JMenuItem("System");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem exitItem = new JMenuItem("Exit");

        percentageItem.addActionListener(_ -> {
            new GradingSystemFrame();
        });

        exitItem.addActionListener(_ -> {
            this.dispose();
        });

        moreMenu.add(percentageItem);
        moreMenu.add(aboutItem);
        moreMenu.add(exitItem);

        menuBar.add(moreMenu);

        return menuBar;
    }
}
