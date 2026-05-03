import model.Dimension;
import model.UserProfile;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.Component;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<Dimension> dimensions;
    private UserProfile userProfile;

    private AppController controller;
    private StepIndicatorPanel stepIndicatorPanel;

    public MainFrame(AppController controller) {
        this.controller = controller;

        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Step Indicator
        stepIndicatorPanel = new StepIndicatorPanel();
        add(stepIndicatorPanel, BorderLayout.NORTH);

        // Card Layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Paneller
        mainPanel.add(new Step1Panel(this), "step1");
        mainPanel.add(new Step2Panel(this), "step2");
        mainPanel.add(new Step3Panel(this), "step3");
        mainPanel.add(new Step4Panel(this), "step4");
        mainPanel.add(new Step5Panel(this), "step5");

        add(mainPanel, BorderLayout.CENTER);

        // başlangıç step
        stepIndicatorPanel.updateSteps(1);
    }

    public void next(String step) {
        cardLayout.show(mainPanel, step);

        if (step.equals("step1")) {
            stepIndicatorPanel.updateSteps(1);
        } else if (step.equals("step2")) {
            stepIndicatorPanel.updateSteps(2);
        } else if (step.equals("step3")) {
            stepIndicatorPanel.updateSteps(3);

            for (Component component : mainPanel.getComponents()) {
                if (component instanceof Step3Panel) {
                    ((Step3Panel) component).loadData();
                }
            }
        } else if (step.equals("step4")) {
            stepIndicatorPanel.updateSteps(4);

            for (Component component : mainPanel.getComponents()) {
                if (component instanceof Step4Panel) {
                    ((Step4Panel) component).calculateScores();
                }
            }

        } else if (step.equals("step5")) {
            stepIndicatorPanel.updateSteps(5);

            for (Component component : mainPanel.getComponents()) {
                if (component instanceof Step5Panel) {
                    ((Step5Panel) component).showResults();
                }
            }
        }
    }
    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public AppController getController() {
        return controller;
    }
}