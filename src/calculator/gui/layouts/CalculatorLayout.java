package calculator.gui.layouts;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculator.gui.panels.GPAConversionPanel;
import calculator.gui.panels.GPAInsertPanel;
import calculator.gui.panels.GPAListPanel;
import calculator.gui.panels.MessagePanel;

public class CalculatorLayout extends JPanel {
    public CalculatorLayout() {
        super();
        this.setLayout(new BorderLayout(5, 5));

        GPAConversionPanel gcp = new GPAConversionPanel();
        GPAInsertPanel gip = new GPAInsertPanel();
        GPAListPanel glp = new GPAListPanel();
        MessagePanel mp = new MessagePanel();

        gcp.startListeners();
        gip.startListeners();
        glp.startListeners();

        JPanel leftPanels = new JPanel();
        leftPanels.setLayout(new BoxLayout(leftPanels, BoxLayout.Y_AXIS));
        leftPanels.add(gcp);
        leftPanels.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanels.add(gip);

        JPanel rightPanels = new JPanel(new BorderLayout());
        rightPanels.add(glp, BorderLayout.CENTER);
        rightPanels.setPreferredSize(new Dimension(400, 350));

        JPanel mainContent = new JPanel(new BorderLayout(5, 5));
        mainContent.add(leftPanels, BorderLayout.WEST);
        mainContent.add(rightPanels, BorderLayout.CENTER);

        JPanel paddedPanel = new JPanel(new BorderLayout(5, 5));
        paddedPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        paddedPanel.add(mainContent, BorderLayout.CENTER);
        paddedPanel.add(mp, BorderLayout.SOUTH);

        this.add(paddedPanel, BorderLayout.CENTER);
    }
}