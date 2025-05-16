package ccc.gwaveraged.gui.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import ccc.gwaveraged.gui.abstracts.Panel;
import ccc.gwaveraged.system.Config;
import ccc.gwaveraged.system.Subject;

public class GPAInsertPanel extends Panel {
    private GridBagConstraints gbc;

    private JComboBox<String> subjectsComboBox;

    private JTextField gpaTextField;
    private JSpinner unitsSpinner;

    private JButton insertButton;

    public GPAInsertPanel() {
        super();
        this.setLayout(new GridBagLayout());
    }

    @Override
    public void createComponents() {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Insert GPA"));

        gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
    
        JLabel subjectLabel = new JLabel("Subject:");
        subjectsComboBox = new JComboBox<>();

        Config.gradingSystem.keySet().forEach(k -> {
            subjectsComboBox.addItem(k);
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        this.add(subjectLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 0.8;
        this.add(subjectsComboBox, gbc);

        JLabel gpaLabel = new JLabel("GPA:");
        JLabel unitsLabel = new JLabel("Units:");
        gpaTextField = new JTextField(4);
        unitsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        this.add(gpaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4;
        this.add(gpaTextField, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        this.add(unitsLabel, gbc);

        gbc.gridx = 3;
        gbc.weightx = 0.2;
        this.add(unitsSpinner, gbc);

        insertButton = new JButton("INSERT");
        insertButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 2, 5);
        this.add(insertButton, gbc);
    }

    @Override
    public void startListeners() {
        insertButton.addActionListener(_ -> {
            String subjectName;
            try {
                subjectName = subjectsComboBox.getSelectedItem().toString();
            } catch (Exception e) {
                this.showWarningMessage("Must select a subject");
                return;
            }

            double gpa;
            try {
                gpa = Double.parseDouble(gpaTextField.getText());
            } catch (Exception e) {
                this.showWarningMessage("Invalid GPA format");
                return;
            }

            if (gpa < 1.00 || gpa > 5.00) {
                this.showWarningMessage("GPA must be in the range (1.00 - 5.00)");
                return;
            }

            int units = Integer.parseInt(unitsSpinner.getValue().toString());

            Subject subject = new Subject(subjectName, units, gpa);
            GPAListPanel.addEntry(subject);
        });
    }
}
