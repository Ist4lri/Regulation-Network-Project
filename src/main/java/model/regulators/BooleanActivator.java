package model.regulators;

import model.genes.Gene;

public class BooleanActivator extends BooleanRegulator {

    public BooleanActivator(double threshold, Gene regulatedGene) {
        super(threshold, regulatedGene);
    }

    @Override
    public double inputFunction() {
        if (this.geneIsSignaled() && this.thresholdIsAttained()) {
            return 1.0;
        }
        return 0;
    }

}
