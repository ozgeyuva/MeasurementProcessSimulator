import javax.swing.*;
import java.awt.*;
import java.util.List;
public class Step2Panel extends JPanel {
    private JRadioButton productBtn;
    private JRadioButton processBtn;

    private JRadioButton educationBtn;
    private JRadioButton healthBtn;

    private JComboBox<String> scenarioBox;

    private MainFrame frame;

    public Step2Panel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 2 - Define Quality Dimensions", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Quality Type Panel
        JPanel qualityPanel = new JPanel();
        qualityPanel.setBorder(BorderFactory.createTitledBorder("Quality Type"));

        productBtn = new JRadioButton("Product");
        processBtn = new JRadioButton("Process");

        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(productBtn);
        qualityGroup.add(processBtn);

        qualityPanel.add(productBtn);
        qualityPanel.add(processBtn);

        // Mode Panel
        JPanel modePanel = new JPanel();
        modePanel.setBorder(BorderFactory.createTitledBorder("Mode"));

        educationBtn = new JRadioButton("Education");
        healthBtn = new JRadioButton("Health");

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(educationBtn);
        modeGroup.add(healthBtn);

        modePanel.add(educationBtn);
        modePanel.add(healthBtn);

        // Scenario Panel
        JPanel scenarioPanel = new JPanel();
        scenarioPanel.setBorder(BorderFactory.createTitledBorder("Scenario"));

        scenarioBox = new JComboBox<>();
        scenarioBox.setPreferredSize(new Dimension(250, 25));
        scenarioPanel.add(scenarioBox);

        centerPanel.add(qualityPanel);
        centerPanel.add(modePanel);
        centerPanel.add(scenarioPanel);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel bottomPanel = new JPanel();

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        bottomPanel.add(backButton);
        bottomPanel.add(nextButton);

        add(bottomPanel, BorderLayout.SOUTH);

        educationBtn.addActionListener(e -> loadScenarios("Education"));
        healthBtn.addActionListener(e -> loadScenarios("Health"));

        backButton.addActionListener(e -> frame.next("step1"));
        nextButton.addActionListener(e -> goNext());
    }

    private void loadScenarios(String mode) {
        scenarioBox.removeAllItems();

        List<String> scenarios = ScenarioRepository.getScenarioNames(mode);
        for (String scenario : scenarios) {
            scenarioBox.addItem(scenario);
        }
    }

    public String getSelection() {
        if (productBtn.isSelected()) {
            return "Product";
        } else if (processBtn.isSelected()) {
            return "Process";
        }
        return "";
    }

    private void goNext() {
        String qualityType = "";
        String mode = "";
        String scenarioName;

        if (productBtn.isSelected()) {
            qualityType = "Product";
        } else if (processBtn.isSelected()) {
            qualityType = "Process";
        }

        if (educationBtn.isSelected()) {
            mode = "Education";
        } else if (healthBtn.isSelected()) {
            mode = "Health";
        }

        scenarioName = (String) scenarioBox.getSelectedItem();

        if (qualityType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a quality type.");
            return;
        }

        if (mode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a mode.");
            return;
        }

        if (scenarioName == null || scenarioName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a scenario.");
            return;
        }

        frame.getController().loadScenario(qualityType, mode, scenarioName);
        frame.next("step3");
    }



}
