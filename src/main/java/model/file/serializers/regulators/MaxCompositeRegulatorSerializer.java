package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.serializers.list.ListRegulatorSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.MaxCompositeRegulator;

public class MaxCompositeRegulatorSerializer implements EntitySerializer<MaxCompositeRegulator> {

    @Override
    public String getCode() {
        return "MaxCompositeRegulator";
    }

    @Override
    public String serialize(MaxCompositeRegulator entity, RegulatoryNetworkWriter writer) {
        return entity.description();
    }

    @Override
    public MaxCompositeRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        return new MaxCompositeRegulator(new ListRegulatorSerializer().deserialize(string, reader));
    }

}
