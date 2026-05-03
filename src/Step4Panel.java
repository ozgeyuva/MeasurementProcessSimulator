import model.Dimension;
import model.Metric;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

public class Step4Panel extends JPanel {

    private JTable table;
    private MainFrame frame;

    public Step4Panel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Step 4 - Collect Data", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JButton back = new JButton("Back");
        JButton calc = new JButton("Calculate Scores");
        JButton next = new JButton("Next");

        bottom.add(back);
        bottom.add(calc);
        bottom.add(next);

        add(bottom, BorderLayout.SOUTH);

        back.addActionListener(e -> frame.next("step3"));
        calc.addActionListener(e -> calculateScores());
        next.addActionListener(e -> frame.next("step5"));
    }

    void calculateScores() {
        String[] cols = {"Metric", "Direction", "Range", "Value", "Score", "Coeff / Unit"};

        DefaultTableModel model = new DefaultTableModel(cols, 0);

        List<Dimension> dims = frame.getDimensions();

        if (dims != null) {
            for (Dimension d : dims) {
                for (Metric m : d.getMetrics()) {
                    model.addRow(new Object[]{
                            m.getName(),
                            m.getDirection(),
                            m.getMin() + " - " + m.getMax(),
                            m.getValue(),
                            m.calculateScore(),
                            m.getCoefficient() + " / " + m.getUnit()
                    });
                }
            }
        }

        table.setModel(model);
    }
}