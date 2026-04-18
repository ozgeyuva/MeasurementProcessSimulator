public class AppController {
    private MainFrame frame;

    public AppController() {
        frame = new MainFrame(this);
    }
    public void start(){
        frame.setVisible(true);
    }
    public void loadScenario(String qualityType, String mode, String scenarioName) {
        frame.setDimensions(ScenarioRepository.getScenario(qualityType, mode, scenarioName));
    }

    public double calculateResults(Dimension dimension) {
        return dimension.calculateScore();
    }
    public MainFrame getFrame() {
        return frame;
    }
}
