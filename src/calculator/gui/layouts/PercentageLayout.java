package calculator.gui.layouts;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import calculator.gui.panels.GradingSystemPanel;

public class PercentageLayout extends JPanel {
    public PercentageLayout() {
        super(new BorderLayout());

        GradingSystemPanel gsp = new GradingSystemPanel();

        gsp.startListeners();
        this.add(gsp, BorderLayout.CENTER);
    }
}
