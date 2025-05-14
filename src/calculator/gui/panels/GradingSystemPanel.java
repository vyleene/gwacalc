package calculator.gui.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import calculator.backend.Config;
import calculator.gui.interfaces.PanelInterface;

public class GradingSystemPanel extends JPanel implements PanelInterface {
    private static JPanel percentageList = new JPanel();
    private GridBagConstraints gbc;
    private JComboBox<String> subjectsComboBox;
    private JButton addSubjectButton;
    private JButton removeSubjectButton;
    private JButton duplicateSubjectButton;
    private JButton pointButton;

    public GradingSystemPanel() {
        super(new BorderLayout(5, 5));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        createComponents();
    }

    public void createComponents() {
        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel bottomPanel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();

        JLabel subjectsLabel = new JLabel("Subjects:");
        subjectsComboBox = new JComboBox<>();

        Config.subjects.keySet().forEach(k -> {
            subjectsComboBox.addItem(k);
        });

        addSubjectButton = new JButton("➕");
        removeSubjectButton = new JButton("➖");
        duplicateSubjectButton = new JButton("🔁");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        topPanel.add(subjectsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        topPanel.add(subjectsComboBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        topPanel.add(addSubjectButton, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        topPanel.add(removeSubjectButton, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        topPanel.add(duplicateSubjectButton, gbc);
        
        percentageList.setLayout(new BoxLayout(percentageList, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(percentageList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.95;
        gbc.fill = GridBagConstraints.BOTH;
        bottomPanel.add(scrollPane, gbc);

        pointButton = new JButton("ADD POINT");

        gbc.gridy = 1;
        gbc.weighty = 0.05;
        bottomPanel.add(pointButton, gbc);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);

        percentageList.removeAll();
        loadPercentages(subjectsComboBox.getSelectedItem().toString());
    }

    @SuppressWarnings("unused")
    public void startListeners() {
        subjectsComboBox.addActionListener(e -> {
            String selectedSubject = subjectsComboBox.getSelectedItem().toString();
            if (selectedSubject != null) {
                percentageList.removeAll();
                loadPercentages(selectedSubject);
            }
        });

        pointButton.addActionListener(e -> {
            addPoint(new String[]{"0.00", "0.00"}, 1.00);
        });
    }

    public void loadPercentages(String subject) {
        Config.subjects.get(subject).forEach(v -> {
            addRow(v.get(2), new double[]{v.get(0), v.get(1)});
        });

        percentageList.revalidate();
        percentageList.repaint();
    }

    @SuppressWarnings("unused")
    public void addRow(double point, double[] range) {
        JPanel rowPanel = new JPanel(new GridBagLayout());
        rowPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 5, 3, 5);

        JLabel pointLabel = new JLabel(String.format("[ %.2f ]", point));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        rowPanel.add(pointLabel, gbc);

        JLabel rangeLabel = new JLabel(String.format("%.2f - %.2f", range[0], range[1]));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rowPanel.add(rangeLabel, gbc);

        JButton removeButton = new JButton("❌");
        removeButton.setMargin(new Insets(1, 5, 1, 5));
        removeButton.setFocusPainted(false);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        rowPanel.add(removeButton, gbc);

        removeButton.addActionListener(e -> {
            List<Double> percentageToPoint = new ArrayList<>();
            percentageToPoint.add(range[0]);
            percentageToPoint.add(range[1]);
            percentageToPoint.add(point);

            removePoint(percentageToPoint, rowPanel);
        });
    
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowPanel.getPreferredSize().height));
    
        percentageList.add(rowPanel);
        percentageList.revalidate();
        percentageList.repaint();
    }

    public void addPoint(String[] range, double point) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel rangeLabel = new JLabel("Range:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(rangeLabel, gbc);

        JTextField fromField = new JTextField(5);
        fromField.setToolTipText("From");
        fromField.setText(range[0]);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(fromField, gbc);

        JLabel dashLabel = new JLabel(" - ");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(dashLabel, gbc);

        JTextField toField = new JTextField(5);
        toField.setToolTipText("To");
        toField.setText(range[1]);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(toField, gbc);

        JLabel pointLabel = new JLabel("Point:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(pointLabel, gbc);

        JSpinner pointSpinner = new JSpinner(new SpinnerNumberModel(point, 0.0, 10.0, 0.25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(pointSpinner, gbc);

        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Add Point",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            double fromValue = Double.parseDouble(fromField.getText());
            double toValue = Double.parseDouble(toField.getText());
            double pointValue = Double.parseDouble(pointSpinner.getValue().toString());

            List<Double> percentageToPoint = new ArrayList<>();
            percentageToPoint.add(fromValue);
            percentageToPoint.add(toValue);
            percentageToPoint.add(pointValue);

            List<List<Double>> percentages = Config.subjects.get(subjectsComboBox.getSelectedItem().toString());
            percentages.add(percentageToPoint);

            Collections.sort(percentages, (a, b) -> Double.compare(a.get(0), b.get(0)));
            Collections.reverse(percentages);


            percentageList.removeAll();
            loadPercentages(subjectsComboBox.getSelectedItem().toString());
        }
    }

    public void removePoint(List<Double> percentageToPoint, JPanel rowPanel) {
        Config.subjects.get(subjectsComboBox.getSelectedItem().toString()).remove(percentageToPoint);
        percentageList.remove(rowPanel);
        percentageList.revalidate();
        percentageList.repaint();
    }
}
