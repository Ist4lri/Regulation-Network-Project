package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.BooleanRepressor;

public class BooleanRepressorSerializer implements EntitySerializer<BooleanRepressor> {

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCode'");
    }

    @Override
    public String serialize(BooleanRepressor entity, RegulatoryNetworkWriter writer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serialize'");
    }

    @Override
    public BooleanRepressor deserialize(String string, RegulatoryNetworkReader reader) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
    }

}
