package model.file.serializers.gene;

import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConstantGene;

public class ConstantGeneSerializer implements EntitySerializer<ConstantGene> {

    private static ConstantGeneSerializer instance = null;

    private ConstantGeneSerializer() {

    }

    @Override
    public String getCode() {
        return "ConstantGene";
    }

    @Override
    public String serialize(ConstantGene entity, RegulatoryNetworkWriter writer) {
        return entity.getClass().getSimpleName() + " "
                + entity.getName() + " "
                + entity.getMaximalProduction() + " "
                + entity.getDegradationRate() + " "
                + entity.getInitialProteinConcentration() + " "
                + entity.isSignaled() + "\n";
    }

    @Override
    public ConstantGene deserialize(String string, RegulatoryNetworkReader reader) {
        String[] dispatchElement = string.split(" ");

        return new ConstantGene(
                dispatchElement[1],
                Double.parseDouble(dispatchElement[2]),
                Boolean.parseBoolean(dispatchElement[3]));
    }

    public synchronized static ConstantGeneSerializer getInstance() {
        if (instance == null) {
            instance = new ConstantGeneSerializer();
        }
        return instance;
    }
}
