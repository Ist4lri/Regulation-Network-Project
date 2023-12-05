package model.regulators;

import model.file.writer.RegulatorVisitor;
import java.util.List;

public class HillsActivatorRegulator extends HillsRegulators {

    public HillsActivatorRegulator(List<Regulator> regulators, Double hillCoefficient, Double coefficientOfActivation) {
        super(regulators, hillCoefficient, coefficientOfActivation);
    }

    @Override
    public double cumulativeValue(double concentration) {
        return (Math.pow(concentration, this.getHillCoefficient()) /
                ((Math.pow(this.getCoefficientOfActivation(), this.getHillCoefficient())) +
                        (Math.pow(concentration, this.getHillCoefficient()))));
    }

    @Override
    public String accept(RegulatorVisitor visitor) {
        return visitor.visit(this);
    }

}
