import model.Dimension;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

public class Step5Panel extends JPanel {

    private JPanel resultPanel;
    private MainFrame frame;
    private RadarChartPanel radar;

    public Step5Panel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Step 5 - Analyse", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        radar = new RadarChartPanel();

        add(new JScrollPane(resultPanel), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton back = new JButton("Back");
        JButton show = new JButton("Show Results");

        bottom.add(back);
        bottom.add(show);

        add(bottom, BorderLayout.SOUTH);

        back.addActionListener(e -> frame.next("step4"));
        show.addActionListener(e -> showResults());
    }

    public void showResults() {
        resultPanel.removeAll();

        List<Dimension> dims = frame.getDimensions();

        if (dims == null || dims.isEmpty()) {
            resultPanel.add(new JLabel("No data."));
            return;
        }

        double lowest = Double.MAX_VALUE;
        String lowestName = "";

        for (Dimension d : dims) {
            double score = d.calculateScore();

            resultPanel.add(new JLabel(d.getName() + ": " + score));

            JProgressBar bar = new JProgressBar(0, 50);
            bar.setValue((int)(score * 10));
            bar.setString(score + "/5");
            bar.setStringPainted(true);

            resultPanel.add(bar);

            if (score < lowest) {
                lowest = score;
                lowestName = d.getName();
            }
        }

        resultPanel.add(new JLabel("Lowest: " + lowestName));
        resultPanel.add(new JLabel("Gap: " + (5 - lowest)));

        radar.setDimensions(dims);
        resultPanel.add(radar);

        resultPanel.revalidate();
        resultPanel.repaint();
    }
}