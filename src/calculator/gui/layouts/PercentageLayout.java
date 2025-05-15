package calculator.gui.layouts;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import calculator.gui.abstracts.Panel;
import calculator.gui.panels.GradingSystemPanel;

public class PercentageLayout extends JPanel {
    public PercentageLayout() {
        super(new BorderLayout());

        Panel gsp = new GradingSystemPanel();

        gsp.createComponents();
        gsp.startListeners();
        this.add(gsp, BorderLayout.CENTER);
    }
}
