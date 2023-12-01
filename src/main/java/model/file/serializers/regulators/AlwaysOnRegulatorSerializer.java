package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.AlwaysOnRegulator;

public class AlwaysOnRegulatorSerializer implements EntitySerializer<AlwaysOnRegulator> {

    private static AlwaysOnRegulatorSerializer instance = null;

    @Override
    public String getCode() {
        return "AlwaysOnRegulator";
    }

    @Override
    public String serialize(AlwaysOnRegulator entity, RegulatoryNetworkWriter writer) {
        return " " + entity.getClass().getSimpleName() + " [" + entity.description() + "]" + "\n";
    }

    @Override
    public AlwaysOnRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        return new AlwaysOnRegulator();
    }

    public synchronized static AlwaysOnRegulatorSerializer getInstance() {
        if (instance == null) {
            instance = new AlwaysOnRegulatorSerializer();
        }
        return instance;
    }

}
