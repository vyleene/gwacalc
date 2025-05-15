package ccc.gwaveraged.gui.layouts;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ccc.gwaveraged.gui.abstracts.Panel;
import ccc.gwaveraged.gui.panels.GPAConversionPanel;
import ccc.gwaveraged.gui.panels.GPAInsertPanel;
import ccc.gwaveraged.gui.panels.GPAListPanel;
import ccc.gwaveraged.gui.panels.MessagePanel;

public class CalculatorLayout extends JPanel {
    public CalculatorLayout() {
        super();
        this.setLayout(new BorderLayout(5, 5));

        Panel gcp = new GPAConversionPanel();
        Panel gip = new GPAInsertPanel();
        Panel glp = new GPAListPanel();
        Panel mp = new MessagePanel();

        gcp.createComponents();
        gip.createComponents();
        glp.createComponents();
        mp.createComponents();

        JPanel leftPanels = new JPanel();
        leftPanels.setLayout(new BoxLayout(leftPanels, BoxLayout.Y_AXIS));
        leftPanels.add(gcp);
        leftPanels.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanels.add(gip);

        JPanel rightPanels = new JPanel(new BorderLayout());
        rightPanels.add(glp, BorderLayout.CENTER);

        JPanel mainContent = new JPanel(new BorderLayout(5, 5));
        mainContent.add(leftPanels, BorderLayout.WEST);
        mainContent.add(rightPanels, BorderLayout.CENTER);

        JPanel paddedPanel = new JPanel(new BorderLayout(5, 5));
        paddedPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        paddedPanel.add(mainContent, BorderLayout.CENTER);
        paddedPanel.add(mp, BorderLayout.SOUTH);

        gcp.startListeners();
        gip.startListeners();
        glp.startListeners();
        mp.startListeners();

        this.add(paddedPanel, BorderLayout.CENTER);
    }
}