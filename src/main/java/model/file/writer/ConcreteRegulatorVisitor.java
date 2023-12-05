package model.file.writer;

import model.file.serializers.regulators.AlwaysOffRegulatorSerializer;
import model.file.serializers.regulators.AlwaysOnRegulatorSerializer;
import model.file.serializers.regulators.BooleanActivatorSerializer;
import model.file.serializers.regulators.BooleanRepressorSerializer;
import model.file.serializers.regulators.MaxCompositeRegulatorSerializer;
import model.file.serializers.regulators.MinCompositeRegulatorSerializer;
import model.regulators.AlwaysOffRegulator;
import model.regulators.AlwaysOnRegulator;
import model.regulators.BooleanActivator;
import model.regulators.BooleanRepressor;
import model.regulators.HillsActivatorRegulator;
import model.regulators.HillsRepressorRegulator;
import model.regulators.MaxCompositeRegulator;
import model.regulators.MinCompositeRegulator;

public class ConcreteRegulatorVisitor implements RegulatorVisitor {

    private RegulatoryNetworkWriter writer;

    public ConcreteRegulatorVisitor(RegulatoryNetworkWriter writer) {
        this.writer = writer;
    }

    @Override
    public String visit(AlwaysOffRegulator regulator) {
        return AlwaysOffRegulatorSerializer.getInstance().serialize(regulator, writer);
    }

    @Override
    public String visit(AlwaysOnRegulator regulator) {
        return AlwaysOnRegulatorSerializer.getInstance().serialize(regulator, writer);
    }

    @Override
    public String visit(BooleanActivator regulator) {
        return BooleanActivatorSerializer.getInstance().serialize(regulator, writer);
    }

    @Override
    public String visit(BooleanRepressor regulator) {
        return BooleanRepressorSerializer.getInstance().serialize(regulator, writer);
    }

    @Override
    public String visit(MaxCompositeRegulator regulator) {
        return MaxCompositeRegulatorSerializer.getInstance().serialize(regulator, writer);
    }

    @Override
    public String visit(MinCompositeRegulator regulator) {
        return MinCompositeRegulatorSerializer.getInstance().serialize(regulator, writer);
    }

    @Override
    public String visit(HillsActivatorRegulator regulator) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public String visit(HillsRepressorRegulator regulator) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

}
