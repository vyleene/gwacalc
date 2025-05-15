package ccc.gwaveraged.gui.frames;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ccc.gwaveraged.gui.layouts.CalculatorLayout;

public class CalculatorFrame extends JFrame {
    public CalculatorFrame(String title) {
        super(title);

        try {
            Image iconImg =  ImageIO.read(getClass().getResource("/resources/icons/favicon.png"));
            this.setIconImage(iconImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        JMenuItem percentageItem = new JMenuItem("Grade System");
        JMenuItem exitItem = new JMenuItem("Exit");

        percentageItem.addActionListener(_ -> {
            new GradingSystemFrame();
        });

        exitItem.addActionListener(_ -> {
            this.dispose();
        });

        moreMenu.add(percentageItem);
        moreMenu.add(exitItem);

        menuBar.add(moreMenu);

        return menuBar;
    }
}
