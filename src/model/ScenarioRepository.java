package model;

import java.util.*;

public class ScenarioRepository {

    private static Map<String, List<String>> scenarios = new HashMap<>();

    static {
        scenarios.put("Education", Arrays.asList(
                "Scenario C — Team Alpha",
                "Scenario D — Team Beta"
        ));

        scenarios.put("Health", Arrays.asList(
                "Scenario A — Clinic One",
                "Scenario B — Hospital Plus"
        ));
    }

    public static List<String> getScenarioNames(String mode) {
        return scenarios.getOrDefault(mode, new ArrayList<>());
    }

    public static List<Dimension> getScenario(String qualityType, String mode, String scenarioName) {
        if (mode.equals("Education")) {
            return buildEducationScenario();
        } else {
            return buildHealthScenario();
        }
    }

    private static List<Dimension> buildEducationScenario() {

        List<Dimension> dims = new ArrayList<>();

        Dimension usability = new Dimension("Usability", 25);

        Metric m1 = new Metric("SUS Score", 50, "Higher", 0, 100, "points");
        m1.setValue(89);

        Metric m2 = new Metric("Onboarding Time", 50, "Lower", 0, 60, "min");
        m2.setValue(5);

        usability.addMetric(m1);
        usability.addMetric(m2);

        Dimension performance = new Dimension("Performance", 20);

        Metric m3 = new Metric("Video Start Time", 50, "Lower", 0, 15, "sec");
        m3.setValue(4);

        Metric m4 = new Metric("Concurrent Exams", 50, "Higher", 0, 600, "users");
        m4.setValue(510);

        performance.addMetric(m3);
        performance.addMetric(m4);

        dims.add(usability);
        dims.add(performance);

        return dims;
    }

    private static List<Dimension> buildHealthScenario() {

        List<Dimension> dims = new ArrayList<>();

        Dimension usability = new Dimension("Usability", 25);

        Metric m1 = new Metric("Doctor UI Score", 50, "Higher", 0, 100, "points");
        m1.setValue(84);

        Metric m2 = new Metric("Registration Time", 50, "Lower", 0, 30, "min");
        m2.setValue(9);

        usability.addMetric(m1);
        usability.addMetric(m2);

        dims.add(usability);

        return dims;
    }
}