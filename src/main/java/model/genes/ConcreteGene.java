package model.genes;

import model.file.writer.GeneVisitor;
import model.regulators.Regulator;

public class ConcreteGene implements Gene {

    private Regulator regulator;
    private double proteinConcentration;
    private final double initialProteinConcentration;
    private double maximalProduction;
    private final double degradationRate;
    private final String name;
    private boolean isSignaled;

    private final boolean initialIsSignaled;

    public ConcreteGene(String name, double maximalProduction, double degradationRate,
            double initialProteinConcentration, boolean isSignaled) {
        this.regulator = null;
        this.initialProteinConcentration = initialProteinConcentration;
        this.proteinConcentration = initialProteinConcentration;
        this.maximalProduction = maximalProduction;
        this.degradationRate = degradationRate;
        this.name = name;
        this.isSignaled = isSignaled;
        this.initialIsSignaled = isSignaled;
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
        this.setProteinConcentration(
                this.getProteinConcentration() + ((this.production() - this.degradation()) * duration));
        if (this.getProteinConcentration() < 0) {
            this.setProteinConcentration(0.0);
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
        return (this.proteinConcentration * this.degradationRate);
    }

    private double production() {
        if (this.regulator == null) {
            return this.maximalProduction;
        }
        return (this.regulator.inputFunction() * this.maximalProduction);
    }

    @Override
    public String accept(GeneVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean getInitialIsSignaled() {
        return this.initialIsSignaled;
    }

}
