package model.regulators;

import java.util.List;

public class MaxCompositeRegulator extends CompositeRegulator {

    protected MaxCompositeRegulator(List<Regulator> regulators) {
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

}
