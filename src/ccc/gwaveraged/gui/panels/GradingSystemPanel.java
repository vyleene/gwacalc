package ccc.gwaveraged.gui.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ccc.gwaveraged.gui.abstracts.Panel;
import ccc.gwaveraged.system.Config;

public class GradingSystemPanel extends Panel {
    private static JPanel percentageList = new JPanel();

    private GridBagConstraints gbc;
    private JComboBox<String> subjectsComboBox;

    public GradingSystemPanel() {
        super();
        this.setLayout(new BorderLayout(5,5));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    @Override
    public void createComponents() {
        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel bottomPanel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();

        JLabel subjectsLabel = new JLabel("Subject:");
        subjectsComboBox = new JComboBox<>();

        Config.gradingSystem.keySet().forEach(k -> {
            subjectsComboBox.addItem(k);
        });

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
        
        percentageList.setLayout(new BoxLayout(percentageList, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(percentageList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        bottomPanel.add(scrollPane, gbc);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);

        percentageList.removeAll();
        loadPercentages(subjectsComboBox.getSelectedItem().toString());
    }

    @Override
    public void startListeners() {
        subjectsComboBox.addActionListener(_ -> {
            String selectedSubject = subjectsComboBox.getSelectedItem().toString();
            if (selectedSubject != null) {
                percentageList.removeAll();
                loadPercentages(selectedSubject);
            }
        });
    }

    public void loadPercentages(String subject) {
        Config.gradingSystem.get(subject).forEach(v -> {
            if (v.isEmpty()) {
                addEntry(0.00, new double[]{0.00, 0.00});
            } else {
                addEntry(v.get(2), new double[]{v.get(0), v.get(1)});
            }
        });

        percentageList.revalidate();
        percentageList.repaint();
    }

    public void addEntry(double point, double[] range) {
        JPanel rowPanel = new JPanel(new GridBagLayout());
        rowPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 5, 3, 10);

        JLabel pointLabel = new JLabel(String.format("[ %.2f ]", point));
        pointLabel.setFont(new Font(pointLabel.getFont().getFontName(), Font.BOLD, pointLabel.getFont().getSize()));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        rowPanel.add(pointLabel, gbc);

        JLabel rangeLabel = new JLabel(String.format("%.2f - %.2f", range[0], range[1]));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        rowPanel.add(rangeLabel, gbc);
    
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowPanel.getPreferredSize().height));
    
        percentageList.add(rowPanel);
        percentageList.revalidate();
        percentageList.repaint();
    }

    public void removeEntry(List<Double> percentageToPoint, JPanel rowPanel) {
        Config.gradingSystem.get(subjectsComboBox.getSelectedItem().toString()).remove(percentageToPoint);
        percentageList.remove(rowPanel);
        percentageList.revalidate();
        percentageList.repaint();
    }
}
