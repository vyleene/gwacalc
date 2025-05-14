package calculator.gui.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import calculator.backend.Config;
import calculator.backend.Subject;
import calculator.gui.interfaces.PanelInterface;

public class GPAConversionPanel extends JPanel implements PanelInterface {
    private GridBagConstraints gbc;

    private JComboBox<String> subjectsComboBox;

    private JTextField prelimsField;
    private JTextField midtermsField;
    private JTextField finalsField;

    private JSpinner unitsSpinner;
    private JSpinner prelimsWeightSpinner;
    private JSpinner midtermsWeightSpinner;
    private JSpinner finalsWeightSpinner;

    private JButton convertAndInsertButton;

    public GPAConversionPanel() {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        createComponents();
    }

    public void createComponents() {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Calculate GPA"));

        JLabel subjectLabel = new JLabel("Subject:");
        subjectsComboBox = new JComboBox<>();

        Config.subjects.keySet().forEach(k -> {
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

        JLabel unitsLabel = new JLabel("Units:");
        unitsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        this.add(unitsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 0.8;
        this.add(unitsSpinner, gbc);

        JLabel prelimsLabel = new JLabel("Prelims:");
        prelimsField = new JTextField(4);
        prelimsWeightSpinner = new JSpinner(new SpinnerNumberModel(0.00, 0.00, 1.00, 0.05));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        this.add(prelimsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4;
        this.add(prelimsField, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        this.add(new JLabel("Weight:"), gbc);

        gbc.gridx = 3;
        gbc.weightx = 0.2;
        this.add(prelimsWeightSpinner, gbc);

        JLabel midtermsLabel = new JLabel("Midterms:");
        midtermsField = new JTextField(4);
        midtermsWeightSpinner = new JSpinner(new SpinnerNumberModel(0.00, 0.00, 1.00, 0.05));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.2;
        this.add(midtermsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4;
        this.add(midtermsField, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        this.add(new JLabel("Weight:"), gbc);

        gbc.gridx = 3;
        gbc.weightx = 0.2;
        this.add(midtermsWeightSpinner, gbc);

        JLabel finalsLabel = new JLabel("Finals:");
        finalsField = new JTextField(4);
        finalsWeightSpinner = new JSpinner(new SpinnerNumberModel(0.00, 0.00, 1.00, 0.05));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.2;
        this.add(finalsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4;
        this.add(finalsField, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        this.add(new JLabel("Weight:"), gbc);

        gbc.gridx = 3;
        gbc.weightx = 0.2;
        this.add(finalsWeightSpinner, gbc);

        convertAndInsertButton = new JButton("CONVERT AND INSERT");
        convertAndInsertButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 2, 5);
        this.add(convertAndInsertButton, gbc);
    }

    @SuppressWarnings("unused")
    public void startListeners() {
        convertAndInsertButton.addActionListener(e -> {
            String subjectName = subjectsComboBox.getSelectedItem().toString();
            int units = Integer.parseInt(unitsSpinner.getValue().toString());
            double prelimsWeight = Double.parseDouble(prelimsWeightSpinner.getValue().toString());
            double midtermsWeight = Double.parseDouble(midtermsWeightSpinner.getValue().toString());
            double finalsWeight = Double.parseDouble(finalsWeightSpinner.getValue().toString());

            Subject subject = new Subject(subjectName, units, prelimsWeight, midtermsWeight, finalsWeight);
            subject.setGradingSystem(Config.subjects.get(subjectName));
            subject.setGrade(Double.parseDouble(prelimsField.getText()), Double.parseDouble(midtermsField.getText()), Double.parseDouble(finalsField.getText()));

            GPAListPanel.addEntry(subject);
        });
    }
}