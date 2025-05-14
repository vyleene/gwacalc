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
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setJMenuBar(createMenuBar());
    }

    @SuppressWarnings("unused")
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu optionsMenu = new JMenu("Options");
        JMenuItem percentageItem = new JMenuItem("Percentage");
        JMenu themesMenu = new JMenu("Themes");
        JMenuItem lightThemeItem = new JMenuItem("Light Mode");
        JMenuItem darkThemeItem = new JMenuItem("Dark Mode");

        percentageItem.addActionListener(e -> {
            new PercentageFrame();
        });

        themesMenu.add(lightThemeItem);
        themesMenu.add(darkThemeItem);

        optionsMenu.add(percentageItem);
        optionsMenu.add(themesMenu);

        JMenu aboutMenu = new JMenu("About");
        menuBar.add(optionsMenu);
        menuBar.add(aboutMenu);

        return menuBar;
    }
}
