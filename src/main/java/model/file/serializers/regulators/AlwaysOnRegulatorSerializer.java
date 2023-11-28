package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.AlwaysOnRegulator;

public class AlwaysOnRegulatorSerializer implements EntitySerializer<AlwaysOnRegulator> {

    @Override
    public String getCode() {
        return "AlwaysOnRegulator";
    }

    @Override
    public String serialize(AlwaysOnRegulator entity, RegulatoryNetworkWriter writer) {
        return entity.description();
    }

    @Override
    public AlwaysOnRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        return new AlwaysOnRegulator();
    }

}
