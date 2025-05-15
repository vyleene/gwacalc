package calculator.gui.panels;

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

import calculator.backend.Config;
import calculator.backend.Subject;
import calculator.gui.abstracts.Panel;

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

/*public class GPAInsertPanel extends JPanel implements PanelInterface {
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
}*/
