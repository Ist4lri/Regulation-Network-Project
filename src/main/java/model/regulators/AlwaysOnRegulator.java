package model.regulators;

public class AlwaysOnRegulator implements Regulator {
    public double inputFunction() {
        return 1.0;
    }

    public String description() {
        return "";
    }
}
