package model.regulators;

import java.util.List;

public abstract class HillsRegulators implements Regulator {
    private final double hillCoefficient;
    private final double coefficientOfActivation;
    private final List<Regulator> regulators;

    protected HillsRegulators(List<Regulator> regulators, Double hillCoefficient, Double coefficientOfActivation) {
        this.hillCoefficient = hillCoefficient;
        this.coefficientOfActivation = coefficientOfActivation;
        this.regulators = regulators;
    }

    public double inputFunction() {
        // JE dois récupérer la concentration de la protéine régulée pour
        // l'appliquer à
        // la cumulative
        // value
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    protected abstract double cumulativeValue(double concentration);

    public double getHillCoefficient() {
        return this.hillCoefficient;
    };

    public double getCoefficientOfActivation() {
        return this.coefficientOfActivation;
    };

    public String description() {
        String toSend = "";
        for (Regulator regulator : this.regulators) {
            toSend += regulator.getClass().getSimpleName() + " " + regulator.description() + ",";
        }
        return toSend.substring(0, toSend.length() - 1);
    }

}
