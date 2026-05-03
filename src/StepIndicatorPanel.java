import javax.swing.*;
import java.awt.*;

public class StepIndicatorPanel extends JPanel {
    private JLabel[] labels;
    private String[] stepNames = {"Profile", "Define", "Plan", "Collect", "Analyse"};

    public StepIndicatorPanel() {
        setLayout(new GridLayout(1, 5, 10, 10));
        labels = new JLabel[5];
        for (int i = 0; i < stepNames.length; i++) {
            labels[i] = new JLabel((i + 1) + ". " + stepNames[i], SwingConstants.CENTER);
            labels[i].setOpaque(true);
            labels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            labels[i].setBackground(Color.LIGHT_GRAY);
            add(labels[i]);
        }
    }
    public void updateSteps(int activeStep) {
        for (int i = 0; i < labels.length; i++) {
            if (i + 1 < activeStep) {
                labels[i].setText("✓ " + stepNames[i]);
                labels[i].setBackground(new Color(144, 238, 144));
            } else if (i + 1 == activeStep) {
                labels[i].setText((i + 1) + ". " + stepNames[i]);
                labels[i].setBackground(new Color(173, 216, 230));
            } else {
                labels[i].setText((i + 1) + ". " + stepNames[i]);
                labels[i].setBackground(Color.LIGHT_GRAY);
            }
        }
    }


}
