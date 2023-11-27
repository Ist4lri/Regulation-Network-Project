package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.AlwaysOffRegulator;

public class AlwaysOffRegulatorSerializer implements EntitySerializer<AlwaysOffRegulator> {

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCode'");
    }

    @Override
    public String serialize(AlwaysOffRegulator entity, RegulatoryNetworkWriter writer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serialize'");
    }

    @Override
    public AlwaysOffRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
    }

}
