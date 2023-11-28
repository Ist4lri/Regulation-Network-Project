package model.regulators;

import java.util.List;

import model.file.writer.RegulatorVisitor;

public class MaxCompositeRegulator extends CompositeRegulator {

    public MaxCompositeRegulator(List<Regulator> regulators) {
        super(regulators);
    }

    @Override
    protected double initialValue() {
        return 0.0;
    }

    @Override
    protected double cumulativeValue(double accumulator, double value) {
        return Math.max(accumulator, value);
    }

    @Override
    public String accept(RegulatorVisitor visitor) {
        return visitor.visit(this);
    }

}
