package model.regulators;

import java.util.List;

import model.file.writer.RegulatorVisitor;

public class MinCompositeRegulator extends CompositeRegulator {

    public MinCompositeRegulator(List<Regulator> regulators) {
        super(regulators);
    }

    @Override
    protected double initialValue() {
        return 1.0;
    }

    @Override
    protected double cumulativeValue(double accumulator, double value) {
        return Math.min(accumulator, value);
    }

    @Override
    public String accept(RegulatorVisitor visitor) {
        return visitor.visit(this);
    }

}
