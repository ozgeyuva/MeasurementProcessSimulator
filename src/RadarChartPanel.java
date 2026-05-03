import model.Dimension;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.util.List;

public class RadarChartPanel extends JPanel {

    private List<Dimension> dimensions;

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (dimensions == null || dimensions.isEmpty()) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 120;
        int n = dimensions.size();

        for (int level = 1; level <= 5; level++) {
            double r = radius * (level / 5.0);
            Polygon polygon = new Polygon();

            for (int i = 0; i < n; i++) {
                double angle = 2 * Math.PI * i / n - Math.PI / 2;
                int x = centerX + (int) (r * Math.cos(angle));
                int y = centerY + (int) (r * Math.sin(angle));
                polygon.addPoint(x, y);
            }

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawPolygon(polygon);
        }
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n - Math.PI / 2;
            int x = centerX + (int) (radius * Math.cos(angle));
            int y = centerY + (int) (radius * Math.sin(angle));

            g2.setColor(Color.GRAY);
            g2.drawLine(centerX, centerY, x, y);

            g2.drawString(dimensions.get(i).getName(), x, y);
        }

        Path2D path = new Path2D.Double();

        for (int i = 0; i < n; i++) {
            double score = dimensions.get(i).calculateScore();
            double r = radius * (score / 5.0);

            double angle = 2 * Math.PI * i / n - Math.PI / 2;
            int x = centerX + (int) (r * Math.cos(angle));
            int y = centerY + (int) (r * Math.sin(angle));

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }

        path.closePath();

        g2.setColor(new Color(0, 100, 255, 100));
        g2.fill(path);

        g2.setColor(Color.BLUE);
        g2.draw(path);
    }
}
