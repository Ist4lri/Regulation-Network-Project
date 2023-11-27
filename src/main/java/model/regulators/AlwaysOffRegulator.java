package model.regulators;

public class AlwaysOffRegulator implements Regulator {
    public double inputFunction() {
        return 0;
    }

    public String description() {
        return "";
    }
}
