package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.BooleanRepressor;

public class BooleanRepressorSerializer implements EntitySerializer<BooleanRepressor> {

    private static BooleanRepressorSerializer instance = null;

    @Override
    public String getCode() {
        return "BooleanRepressor";
    }

    @Override
    public String serialize(BooleanRepressor entity, RegulatoryNetworkWriter writer) {
        return entity.description();
    }

    @Override
    public BooleanRepressor deserialize(String string, RegulatoryNetworkReader reader) {
        String[] toDispatch = string.split(" ");
        return new BooleanRepressor(Double.parseDouble(toDispatch[0]), reader.getGene(toDispatch[1]));
    }

    public synchronized static BooleanRepressorSerializer getInstance() {
        if (instance == null) {
            instance = new BooleanRepressorSerializer();
        }
        return instance;
    }

}
