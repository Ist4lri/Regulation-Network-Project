package model.file.serializers.gene;

import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConcreteGene;

public class ConcreteGeneSerializer implements EntitySerializer<ConcreteGene> {

    private static ConcreteGeneSerializer instance = null;

    private ConcreteGeneSerializer() {

    }

    @Override
    public String getCode() {
        return "ConcreteGene";
    }

    @Override
    public String serialize(ConcreteGene entity, RegulatoryNetworkWriter writer) {
        return entity.getClass().getSimpleName() + " "
                + entity.getName() + " "
                + entity.getMaximalProduction() + " "
                + entity.getDegradationRate() + " "
                + entity.getInitialProteinConcentration() + " "
                + entity.isSignaled() + "\n";
    }

    @Override
    public ConcreteGene deserialize(String string, RegulatoryNetworkReader reader) {
        String[] dispatchElement = string.split(" ");
        return new ConcreteGene(
                dispatchElement[1],
                Double.parseDouble(dispatchElement[2]),
                Double.parseDouble(dispatchElement[3]),
                Double.parseDouble(dispatchElement[4]),
                Boolean.parseBoolean(dispatchElement[5]));
    }

    public synchronized static ConcreteGeneSerializer getInstance() {
        if (instance == null) {
            instance = new ConcreteGeneSerializer();
        }
        return instance;
    }
}
