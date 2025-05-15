package calculator.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import calculator.Calculator;
import calculator.backend.Config;
import calculator.gui.abstracts.Panel;

public class MessagePanel extends Panel {
    private static JLabel label;
    private JButton toggleButton;

    private Image sunIcon;
    private Image moonIcon;

    public MessagePanel() {
        super();
        this.setLayout(new BorderLayout());
    }

    @Override
    public void createComponents() {
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        label = new JLabel("GWA: 0.00");
        label.setBorder(new EmptyBorder(5, 10, 5, 5));
        this.add(label, BorderLayout.CENTER);

        toggleButton = new JButton();
        toggleButton.setMargin(new Insets(5, 10, 5, 10));

        try {
            Image sunImg = ImageIO.read(getClass().getResource("/resources/gui/icons/sun.png"));
            Image moonImg = ImageIO.read(getClass().getResource("/resources/gui/icons/moon.png"));
            
            sunIcon = sunImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            moonIcon = moonImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            toggleButton.setIcon(new ImageIcon((Config.isDarkMode) ? moonIcon : sunIcon));
        } catch (Exception e) {}

        this.add(toggleButton, BorderLayout.EAST);

        this.setPreferredSize(new Dimension(0, 40));
    }

    @Override
    public void startListeners() {
        toggleButton.addActionListener(_ -> {
            Config.isDarkMode = !Config.isDarkMode;
    
            Calculator.setLaF();

            toggleButton.setIcon(new ImageIcon((Config.isDarkMode) ? moonIcon : sunIcon));

            javax.swing.SwingUtilities.updateComponentTreeUI(this.getTopLevelAncestor());
        });
    }

    public static void setMessage(String message) {
        label.setText(message);
    }
    
}
