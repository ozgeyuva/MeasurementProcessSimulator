import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Step5Panel extends JPanel {
    private JPanel resultPanel;
    private MainFrame frame;
    private RadarChartPanel radarChartPanel;

    public Step5Panel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 5 - Analyse", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        radarChartPanel = new RadarChartPanel();

        JScrollPane scrollPane = new JScrollPane(resultPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton backButton = new JButton("Back");
        JButton showButton = new JButton("Show Results");

        bottomPanel.add(backButton);
        bottomPanel.add(showButton);

        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> frame.next("step4"));
        showButton.addActionListener(e -> showResults());
    }
    public void showResults() {
        resultPanel.removeAll();

        List<Dimension> dimensions = frame.getDimensions();

        if (dimensions == null || dimensions.isEmpty()) {
            resultPanel.add(new JLabel("No analysis data available."));
            resultPanel.revalidate();
            resultPanel.repaint();
            return;
        }
        double lowestScore = Double.MAX_VALUE;
        String lowestDimensionName = "";

        for (Dimension dimension : dimensions) {
            double score = dimension.calculateScore();

            JLabel nameLabel = new JLabel(dimension.getName() + " Score: " + score);

            JProgressBar progressBar = new JProgressBar(0, 50);
            progressBar.setValue((int) (score * 10));
            progressBar.setString(score + " / 5.0");
            progressBar.setStringPainted(true);

            resultPanel.add(nameLabel);
            resultPanel.add(progressBar);
            resultPanel.add(Box.createVerticalStrut(10));

            if (score < lowestScore) {
                lowestScore = score;
                lowestDimensionName = dimension.getName();
            }
        }
        double gapValue = 5.0 - lowestScore;
        String qualityLevel = getQualityLevel(lowestScore);

        resultPanel.add(new JLabel("Lowest Dimension: " + lowestDimensionName));
        resultPanel.add(new JLabel("Gap Value: " + String.format("%.2f", gapValue)));
        resultPanel.add(new JLabel("Quality Level: " + qualityLevel));
        resultPanel.add(new JLabel("This dimension has the lowest score and requires the most improvement."));

        radarChartPanel.setDimensions(dimensions);
        resultPanel.add(new JLabel("Radar Chart"));
        resultPanel.add(radarChartPanel);

        resultPanel.revalidate();
        resultPanel.repaint();
    }
    private String getQualityLevel(double score) {
        if (score >= 4.5) {
            return "Excellent";
        } else if (score >= 3.5) {
            return "Good";
        } else if (score >= 2.5) {
            return "Needs Improvement";
        } else {
            return "Poor";
        }
    }
}
