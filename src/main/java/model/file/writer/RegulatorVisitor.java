package model.file.writer;

import model.regulators.AlwaysOffRegulator;
import model.regulators.AlwaysOnRegulator;
import model.regulators.BooleanActivator;
import model.regulators.BooleanRepressor;
import model.regulators.HillsActivatorRegulator;
import model.regulators.HillsRepressorRegulator;
import model.regulators.MaxCompositeRegulator;
import model.regulators.MinCompositeRegulator;

public interface RegulatorVisitor {

    public String visit(AlwaysOffRegulator regulator);

    public String visit(AlwaysOnRegulator regulator);

    public String visit(BooleanActivator regulator);

    public String visit(BooleanRepressor regulator);

    public String visit(MaxCompositeRegulator regulator);

    public String visit(MinCompositeRegulator regulator);

    public String visit(HillsActivatorRegulator regulator);

    public String visit(HillsRepressorRegulator regulator);
}
