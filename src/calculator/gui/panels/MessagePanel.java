package calculator.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculator.gui.interfaces.PanelInterface;

public class MessagePanel extends JPanel implements PanelInterface {
    private static JLabel label;
    private JButton submitBtn;

    public MessagePanel() {
        super(new BorderLayout());
        
        createComponents();
    }

    public void createComponents() {
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        label = new JLabel("GWA: 0.00");
        label.setBorder(new EmptyBorder(5, 10, 5, 5));
        this.add(label, BorderLayout.CENTER);

        submitBtn = new JButton("➕");
        submitBtn.setMargin(new Insets(5, 10, 5, 10));
        this.add(submitBtn, BorderLayout.EAST);

        this.setPreferredSize(new Dimension(0, 40));
    }

    @SuppressWarnings("unused")
    public void startListeners() {
        submitBtn.addActionListener(e -> {

        });
    }

    public static void setMessage(String message) {
        label.setText(message);
    }
    
}
