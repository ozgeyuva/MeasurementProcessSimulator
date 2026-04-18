import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Step3Panel extends JPanel {
    private JTable table;
    private MainFrame frame;

    public Step3Panel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 3 - Plan Measurement", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");
        JButton loadButton = new JButton("Load Data");

        bottomPanel.add(backButton);
        bottomPanel.add(loadButton);
        bottomPanel.add(nextButton);

        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> frame.next("step2"));
        loadButton.addActionListener(e -> loadData());
        nextButton.addActionListener(e -> frame.next("step4"));
    }

    public void loadData() {
        String[] columns = {"Metric", "Coefficient", "Direction", "Range", "Unit"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<Dimension> dimensions = frame.getDimensions();

        if (dimensions != null) {
            for (Dimension dimension : dimensions) {
                for (Metric metric : dimension.getMetrics()) {
                    Object[] row = {
                            metric.getName(),
                            metric.getCoefficient(),
                            metric.getDirection(),
                            metric.getMin() + " - " + metric.getMax(),
                            metric.getUnit()
                    };
                    model.addRow(row);
                }
            }
        }
        table.setModel(model);
    }
}
