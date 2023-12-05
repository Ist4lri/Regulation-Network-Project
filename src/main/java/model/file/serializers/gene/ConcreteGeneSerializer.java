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
                + entity.getInitialIsSignaled() + "\n";
    }

    @Override
    public ConcreteGene deserialize(String string, RegulatoryNetworkReader reader) {
        String[] dispatchElement = string.split(" ");
        Double maxProduction = Double.parseDouble(dispatchElement[2]);
        Double degradationRate = Double.parseDouble(dispatchElement[3]);
        Double initialProteinConcentration = Double.parseDouble(dispatchElement[4]);
        String isSignaled = dispatchElement[5];

        if (maxProduction < 0 || degradationRate < 0 || initialProteinConcentration < 0) {
            throw new IllegalArgumentException(
                    "Production, DegradationRate or Initial Concentration can't be negative");
        } else if (!isSignaled.equals("false") && !isSignaled.equals("true")) {
            throw new IllegalArgumentException(
                    "Is Signaled option must be 'false' or 'true' (case sensitive)");
        }

        return new ConcreteGene(
                dispatchElement[1],
                maxProduction,
                degradationRate,
                initialProteinConcentration,
                Boolean.parseBoolean(isSignaled));
    }

    public synchronized static ConcreteGeneSerializer getInstance() {
        if (instance == null) {
            instance = new ConcreteGeneSerializer();
        }
        return instance;
    }
}
