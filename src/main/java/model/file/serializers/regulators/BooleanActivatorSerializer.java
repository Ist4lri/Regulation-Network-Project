package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.BooleanActivator;

public class BooleanActivatorSerializer implements EntitySerializer<BooleanActivator> {

    private static BooleanActivatorSerializer instance = null;

    @Override
    public String getCode() {
        return "BooleanActivator";
    }

    @Override
    public String serialize(BooleanActivator entity, RegulatoryNetworkWriter writer) {
        return entity.description();
    }

    @Override
    public BooleanActivator deserialize(String string, RegulatoryNetworkReader reader) {
        String[] toDispatch = string.split(" ");
        return new BooleanActivator(Double.parseDouble(toDispatch[0]), reader.getGene(toDispatch[1]));
    }

    public synchronized static BooleanActivatorSerializer getInstance() {
        if (instance == null) {
            instance = new BooleanActivatorSerializer();
        }
        return instance;
    }

}
