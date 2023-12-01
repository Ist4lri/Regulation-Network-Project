package model.regulators;

import model.file.writer.RegulatorVisitor;
import model.genes.Gene;

public class BooleanRepressor extends BooleanRegulator {

    public BooleanRepressor(double threshold, Gene regulatedGene) {
        super(threshold, regulatedGene);
    }

    @Override
    public double inputFunction() {
        if (this.geneIsSignaled() & this.thresholdIsAttained()) {
            return 0.0;
        }
        return 1.0;
    }

    @Override
    public String accept(RegulatorVisitor visitor) {
        return visitor.visit(this);
    }

}
