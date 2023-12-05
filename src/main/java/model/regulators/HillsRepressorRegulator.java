package model.regulators;

import model.file.writer.RegulatorVisitor;
import java.util.List;

public class HillsRepressorRegulator extends HillsRegulators {

    public HillsRepressorRegulator(List<Regulator> regulators, Double hillCoefficient, Double coefficientOfActivation) {
        super(regulators, hillCoefficient, coefficientOfActivation);
    }

    @Override
    protected double cumulativeValue(double concentration) {
        return (1 / (1 + (Math.pow((concentration / this.getCoefficientOfActivation()), this.getHillCoefficient()))));
    }

    @Override
    public String accept(RegulatorVisitor visitor) {
        return visitor.visit(this);
    }

}
