package model.regulators;

import model.genes.Gene;

public abstract class BooleanRegulator implements Regulator {
    private final double threshold;
    private final Gene gene;

    BooleanRegulator(double threshold, Gene regulatedGene) {
        this.threshold = threshold;
        this.gene = regulatedGene;
    }

    protected boolean thresholdIsAttained() {
        return this.gene.getProteinConcentration() > this.threshold;
    }

    protected boolean geneIsSignaled() {
        return this.gene.isSignaled();
    }

    public String description() {
        return this.threshold + " " + this.gene.getName();
    }

}
