package model.file.serializers.regulators;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.Gene;
import model.regulators.BooleanActivator;

public class BooleanActivatorSerializer implements EntitySerializer<BooleanActivator> {

    private static BooleanActivatorSerializer instance = null;

    @Override
    public String getCode() {
        return "BooleanActivator";
    }

    @Override
    public String serialize(BooleanActivator entity, RegulatoryNetworkWriter writer) {
        return " " + entity.getClass().getSimpleName() + " [" + entity.description() + "]" + "\n";
    }

    @Override
    public BooleanActivator deserialize(String string, RegulatoryNetworkReader reader) {
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

        return new BooleanActivator(thresholds, gene);
    }

    public synchronized static BooleanActivatorSerializer getInstance() {
        if (instance == null) {
            instance = new BooleanActivatorSerializer();
        }
        return instance;
    }

}
