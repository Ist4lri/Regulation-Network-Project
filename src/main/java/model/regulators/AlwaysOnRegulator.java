package model.regulators;

import model.file.writer.RegulatorVisitor;

public class AlwaysOnRegulator implements Regulator {
    public double inputFunction() {
        return 1.0;
    }

    public String description() {
        return "";
    }

    @Override
    public String accept(RegulatorVisitor visitor) {
        return visitor.visit(this);
    }
}
