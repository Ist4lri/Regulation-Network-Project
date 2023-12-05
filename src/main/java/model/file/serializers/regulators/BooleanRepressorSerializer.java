package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.Gene;
import model.regulators.BooleanRepressor;

public class BooleanRepressorSerializer implements EntitySerializer<BooleanRepressor> {

    private static BooleanRepressorSerializer instance = null;

    @Override
    public String getCode() {
        return "BooleanRepressor";
    }

    @Override
    public String serialize(BooleanRepressor entity, RegulatoryNetworkWriter writer) {
        return " " + entity.getClass().getSimpleName() + " [" + entity.description() + "]" + "\n";
    }

    @Override
    public BooleanRepressor deserialize(String string, RegulatoryNetworkReader reader) {
        String[] toDispatch = string.split(" ");
        Double thresholds = Double.parseDouble(toDispatch[1]);
        Gene gene = reader.getGene(toDispatch[2]);

        if (thresholds < 0) {
            throw new IllegalArgumentException(
                    "Error : Thresholds can't be negative. Verify your Regulators.");
        } else if (gene == null) {
            throw new IllegalArgumentException(
                    "Gene Unknown. PLease, verify your gene Name in your regulators lines.");
        }

        return new BooleanRepressor(thresholds, gene);
    }

    public synchronized static BooleanRepressorSerializer getInstance() {
        if (instance == null) {
            instance = new BooleanRepressorSerializer();
        }
        return instance;
    }

}
