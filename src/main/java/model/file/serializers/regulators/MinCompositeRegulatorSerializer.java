package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.serializers.list.ListRegulatorSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.MinCompositeRegulator;

public class MinCompositeRegulatorSerializer implements EntitySerializer<MinCompositeRegulator> {

    private static MinCompositeRegulatorSerializer instance = null;

    @Override
    public String getCode() {
        return "MinCompositeRegulator";
    }

    @Override
    public String serialize(MinCompositeRegulator entity, RegulatoryNetworkWriter writer) {
        return entity.description();
    }

    @Override
    public MinCompositeRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        return new MinCompositeRegulator(new ListRegulatorSerializer().deserialize(string, reader));
    }

    public synchronized static MinCompositeRegulatorSerializer getInstance() {
        if (instance == null) {
            instance = new MinCompositeRegulatorSerializer();
        }
        return instance;
    }

}
