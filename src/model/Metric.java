package model;
public class Metric {
    private String name;
    private int coefficient;
    private String direction;
    private double min,max;
    private String unit;
    private double value;

    public Metric(String name, int coefficient, String direction, int min, int max, String unit) {
        this.name = name;
        this.coefficient = coefficient;
        this.direction = direction;
        this.min = min;
        this.max = max;
        this.unit = unit;
        this.value = 0;
    }
    public double calculateScore() {
        double score;
        if (direction.equals("Higher")) {
            score = 1 + (value - min) / (max - min) * 4;
        } else {
            score = 5 - (value - min) / (max - min) * 4;
        }
        score = Math.max(1, Math.min(5, score));

        return Math.round(score * 2) / 2.0;
    }

    public String getName() {return name;}
    public int getCoefficient() {return coefficient;}
    public String getDirection() {return direction;}
    public double getMin() {return min;}
    public double getMax() {return max;}
    public String getUnit() {return unit;}
    public double getValue() {return value;}
    public void setValue(double value) {this.value = value;}

}

