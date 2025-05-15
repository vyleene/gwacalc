package ccc.gwaveraged.gui.layouts;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import ccc.gwaveraged.gui.abstracts.Panel;
import ccc.gwaveraged.gui.panels.GradingSystemPanel;

public class GradingSystemLayout extends JPanel {
    public GradingSystemLayout() {
        super(new BorderLayout());

        Panel gsp = new GradingSystemPanel();

        gsp.createComponents();
        gsp.startListeners();
        this.add(gsp, BorderLayout.CENTER);
    }
}
