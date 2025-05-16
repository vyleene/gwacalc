package ccc.gwaveraged.gui.panels;

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

import ccc.gwaveraged.GWAveraged;
import ccc.gwaveraged.gui.abstracts.Panel;
import ccc.gwaveraged.system.Config;

public class MessagePanel extends Panel {
    private static JLabel label;
    private JButton toggleButton;

    private Image sun;
    private Image moon;

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
            Image sunImg = ImageIO.read(getClass().getResource("/resources/icons/sun.png"));
            Image moonImg = ImageIO.read(getClass().getResource("/resources/icons/moon.png"));
            
            sun = sunImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            moon = moonImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            toggleButton.setIcon(new ImageIcon((Config.isDarkMode) ? moon : sun));
        } catch (Exception e) {}

        this.add(toggleButton, BorderLayout.EAST);

        this.setPreferredSize(new Dimension(0, 40));
    }

    @Override
    public void startListeners() {
        toggleButton.addActionListener(_ -> {
            Config.isDarkMode = !Config.isDarkMode;
    
            GWAveraged.setLaF();
            Config.updateTheme();

            toggleButton.setIcon(new ImageIcon((Config.isDarkMode) ? moon : sun));

            javax.swing.SwingUtilities.updateComponentTreeUI(this.getTopLevelAncestor());
        });
    }

    public static void setMessage(String message) {
        label.setText(message);
    }
    
}
