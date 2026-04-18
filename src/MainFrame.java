import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<Dimension> dimensions;
    private UserProfile userProfile;

    private AppController controller;

    public MainFrame(AppController controller) {
        this.controller = controller;

        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new Step1Panel(this), "step1");
        mainPanel.add(new Step2Panel(this), "step2");
        mainPanel.add(new Step3Panel(this), "step3");
        mainPanel.add(new Step4Panel(this), "step4");
        mainPanel.add(new Step5Panel(this), "step5");

        add(mainPanel);
    }

    public void next(String step) {
        cardLayout.show(mainPanel, step);
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
