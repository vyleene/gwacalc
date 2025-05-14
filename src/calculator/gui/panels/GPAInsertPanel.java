package calculator.gui.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculator.backend.Subject;
import calculator.gui.interfaces.PanelInterface;

public class GPAInsertPanel extends JPanel implements PanelInterface {
    private final String[] labels = {"Subject", "GPA", "Units"};
    private GridBagConstraints gbc;
    private JTextField[] fields;
    private JButton submitBtn;
    
    public GPAInsertPanel() {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
        fields = new JTextField[labels.length];

        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.anchor = GridBagConstraints.WEST;

        createComponents();
    }

    public void createComponents() {
        
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new JTextField(10);
            JLabel label = new JLabel(labels[i] + ":");

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            this.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            this.add(fields[i], gbc);
        }

        submitBtn = new JButton("INSERT");
        submitBtn.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = fields.length;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 5, 2, 5);

        this.add(submitBtn, gbc);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Insert GPA"));
    }

    @SuppressWarnings("unused")
    public void startListeners() {
        submitBtn.addActionListener(e -> GPAListPanel.addEntry(new Subject(fields[0].getText(), Integer.parseInt(fields[2].getText()), Double.parseDouble(fields[1].getText()))));
    }
}
