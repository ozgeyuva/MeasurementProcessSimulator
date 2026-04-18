import model.Metric;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Step4Panel extends JPanel {
    private JTable table;
    private MainFrame frame;

    public Step4Panel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 4 - Collect Data", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton backButton = new JButton("Back");
        JButton loadButton = new JButton("Calculate Scores");
        JButton nextButton = new JButton("Next");

        bottomPanel.add(backButton);
        bottomPanel.add(loadButton);
        bottomPanel.add(nextButton);

        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> frame.next("step3"));
        loadButton.addActionListener(e -> calculateScores());
        nextButton.addActionListener(e -> frame.next("step5"));
    }

    public void calculateScores() {
        String[] columns = {"Metric", "Direction", "Range", "Value", "Score", "Coeff / Unit"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<Dimension> dimensions = frame.getDimensions();

        if (dimensions != null) {
            for (Dimension dimension : dimensions) {
                for (Metric metric : dimension.getMetrics()) {
                    Object[] row = {
                            metric.getName(),
                            metric.getDirection(),
                            metric.getMin() + " - " + metric.getMax(),
                            metric.getValue(),
                            metric.calculateScore(),
                            metric.getCoefficient() + " / " + metric.getUnit()
                    };
                    model.addRow(row);
                }
            }
        }

        table.setModel(model);
    }
}
