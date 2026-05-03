import model.Dimension;
import model.Metric;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Font;
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
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton backButton = new JButton("Back");
        JButton loadButton = new JButton("Load Data");
        JButton nextButton = new JButton("Next");

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

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Dimension> dimensions = frame.getDimensions();

        if (dimensions != null) {
            for (Dimension d : dimensions) {
                for (Metric m : d.getMetrics()) {
                    model.addRow(new Object[]{
                            m.getName(),
                            m.getCoefficient(),
                            m.getDirection(),
                            m.getMin() + " - " + m.getMax(),
                            m.getUnit()
                    });
                }
            }
        }

        table.setModel(model);
    }
}