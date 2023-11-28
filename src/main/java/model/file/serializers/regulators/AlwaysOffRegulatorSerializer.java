package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.AlwaysOffRegulator;

public class AlwaysOffRegulatorSerializer implements EntitySerializer<AlwaysOffRegulator> {

    @Override
    public String getCode() {
        return "AlwaysOffRegulator";
    }

    @Override
    public String serialize(AlwaysOffRegulator entity, RegulatoryNetworkWriter writer) {
        return entity.description();
    }

    @Override
    public AlwaysOffRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        return new AlwaysOffRegulator();
    }

}
