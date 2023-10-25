package model.genes;

import model.regulators.Regulator;

public class ConcreteGene implements RegulatedGene {

    private Regulator regulator;
    private double proteinConcentration;
    private final double initialProteinConcentration;
    private double maximalProduction;
    private final double degradationRate;
    private final String name;
    private boolean isSignaled;

    public ConcreteGene(double initialProteinConcentration, double maximalProduction,
            double degradationRate, String name, boolean isSignaled) {
        this.regulator = null;
        this.initialProteinConcentration = initialProteinConcentration;
        this.proteinConcentration = initialProteinConcentration;
        this.maximalProduction = maximalProduction;
        this.degradationRate = degradationRate;
        this.name = name;
        this.isSignaled = isSignaled;
    }

    @Override
    public double getProteinConcentration() {
        return this.proteinConcentration;
    }

    @Override
    public double getInitialProteinConcentration() {
        return this.initialProteinConcentration;
    }

    @Override
    public void setProteinConcentration(double proteinConcentration) {
        this.proteinConcentration = proteinConcentration;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update(double duration) {
        System.out.println(this.getDegradationRate());
        System.out.println(this.getProteinConcentration());
        System.out.println(this.getMaximalProduction());

        this.setProteinConcentration(
                this.getProteinConcentration() + ((this.production() - this.degradation()) * duration));
        System.out.println(this.getProteinConcentration());
        if (getProteinConcentration() < 0) {
            setProteinConcentration(0.0);
        }
    }

    @Override
    public double getMaximalProduction() {
        return this.maximalProduction;
    }

    @Override
    public double getDegradationRate() {
        return this.degradationRate;
    }

    @Override
    public Regulator getRegulator() {
        return this.regulator;
    }

    @Override
    public void setRegulator(Regulator regulator) {
        this.regulator = regulator;
    }

    public boolean isSignaled() {
        return this.isSignaled;
    }

    public void setSignaled(boolean isSignaled) {
        this.isSignaled = isSignaled;
    }

    private double degradation() {
        return this.proteinConcentration - this.degradationRate;
    }

    private double production() {
        if (this.regulator == null) {
            return this.proteinConcentration + this.maximalProduction;
        }
        return this.proteinConcentration * (this.regulator.inputFunction() + this.maximalProduction);
    }

}
