package model;
import java.util.ArrayList;
import java.util.List;

public class Dimension {
    private String name;
    private int coefficient;
    private List<Metric> metrics;

    public Dimension(String name, int coefficient) {
        this.name = name;
        this.coefficient = coefficient;
        this.metrics = new ArrayList<>();
    }
    public void addMetric(Metric metric) {
        metrics.add(metric);
    }
    public double calculateScore() {
        double total = 0;
        int sumCoeff = 0;

        for (Metric metric : metrics) {
            total += metric.calculateScore() * metric.getCoefficient();
            sumCoeff += metric.getCoefficient();
        }

        return total / sumCoeff;
    }
    public String getName() { return name; }
    public int getCoefficient() { return coefficient; }
    public List<Metric> getMetrics() { return metrics; }

}
