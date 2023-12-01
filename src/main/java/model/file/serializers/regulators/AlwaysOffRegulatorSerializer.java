package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.AlwaysOffRegulator;

public class AlwaysOffRegulatorSerializer implements EntitySerializer<AlwaysOffRegulator> {

    private static AlwaysOffRegulatorSerializer instance = null;

    @Override
    public String getCode() {
        return "AlwaysOffRegulator";
    }

    @Override
    public String serialize(AlwaysOffRegulator entity, RegulatoryNetworkWriter writer) {
        return " " + entity.getClass().getSimpleName() + " [" + entity.description() + "]" + "\n";
    }

    @Override
    public AlwaysOffRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        return new AlwaysOffRegulator();
    }

    public synchronized static AlwaysOffRegulatorSerializer getInstance() {
        if (instance == null) {
            instance = new AlwaysOffRegulatorSerializer();
        }
        return instance;
    }

}
