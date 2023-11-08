package model.regulators;

import java.util.List;

public class MinCompositeRegulator extends CompositeRegulator {

    protected MinCompositeRegulator(List<Regulator> regulators) {
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

}
