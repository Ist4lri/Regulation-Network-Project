package model.regulators;

import java.util.List;

public abstract class CompositeRegulator implements Regulator {
    private final List<Regulator> regulators;

    protected CompositeRegulator(List<Regulator> regulators) {
        this.regulators = regulators;
    }

    protected abstract double initialValue();

    protected abstract double cumulativeValue(double accumulator, double value);

    public double inputFunction() {
        double result = this.initialValue();
        for (Regulator regulator : this.regulators) {
            result = this.cumulativeValue(result, regulator.inputFunction());
        }
        return result;
    }

    public String description() {
        String toSend = "";
        for (Regulator regulator : this.regulators) {
            toSend += regulator.getClass().getSimpleName() + " " + regulator.description() + ",";
        }
        return toSend.substring(0, toSend.length() - 1);
    }

}
