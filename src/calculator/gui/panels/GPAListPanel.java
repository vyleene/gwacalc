package calculator.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import calculator.backend.Subject;
import calculator.gui.abstracts.Panel;

public class GPAListPanel extends Panel {
    private static JPanel entryList = new JPanel();
    
    private GridBagConstraints gbc;
    private JButton calculateButton;

    public GPAListPanel() {
        super();
        this.setLayout(new BorderLayout()); 
    }

    @Override
    public void createComponents() {
        gbc = new GridBagConstraints();
        
        entryList.setLayout(new BoxLayout(entryList, BoxLayout.Y_AXIS));
        entryList.setBorder(new EmptyBorder(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(entryList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        calculateButton = new JButton("CALCULATE GWA");
        calculateButton.setFocusPainted(false);

        JPanel wrapper = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.95;
        gbc.fill = GridBagConstraints.BOTH;
        wrapper.add(scrollPane, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.05;
        wrapper.add(calculateButton, gbc);

        this.add(wrapper, BorderLayout.CENTER);

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "GPA List"));
    }

    @Override
    public void startListeners() {
        calculateButton.addActionListener(_ -> {
            double totalWeightedPoints = 0.0;
            int totalUnits = 0;

            for (Subject subject : Subject.subjects) {
                double gradePoint = subject.calculateGradePoint();
                totalWeightedPoints += gradePoint * subject.getUnits();
                totalUnits += subject.getUnits();
            }

            double gwa = totalWeightedPoints / totalUnits;
            MessagePanel.setMessage(String.format("GWA: %.2f", gwa));
        });
    }

    public static void addEntry(Subject subject) {
        Subject.subjects.add(subject);
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setBorder(new EmptyBorder(3, 2, 3, 2));

        JLabel label = new JLabel(String.format("[%s] %.2f - %d units", subject.getSubjectName(), subject.calculateGradePoint(), subject.getUnits()));
        JButton removeButton = new JButton("❌");
        removeButton.setMargin(new Insets(1, 5, 1, 5));
        removeButton.setFocusPainted(false);

        removeButton.addActionListener(_ -> {
            removeEntry(subject, row);
        });

        row.add(label, BorderLayout.CENTER);
        row.add(removeButton, BorderLayout.EAST);

        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, row.getPreferredSize().height));

        entryList.add(row);
        entryList.revalidate();
        entryList.repaint();
    }

    public static void removeEntry(Subject subject, JPanel row) {
        Subject.subjects.remove(subject);
        entryList.remove(row);
        entryList.revalidate();
        entryList.repaint();
    }
}
