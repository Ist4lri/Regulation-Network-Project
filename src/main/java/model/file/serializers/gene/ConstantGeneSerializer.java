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
        Double proteinConcentration = Double.parseDouble(dispatchElement[2]);
        String isSignaled = dispatchElement[3];

        if (proteinConcentration < 0) {
            throw new IllegalArgumentException(
                    "Error : Protein Concentration can't be negative. Verify your file !");
        } else if (!isSignaled.equals("false") && !isSignaled.equals("true")) {
            throw new IllegalArgumentException(
                    "Is Signaled option must be 'false' or 'true' (case sensitive)");
        }

        return new ConstantGene(
                dispatchElement[1],
                proteinConcentration,
                Boolean.parseBoolean(isSignaled));
    }

    public synchronized static ConstantGeneSerializer getInstance() {
        if (instance == null) {
            instance = new ConstantGeneSerializer();
        }
        return instance;
    }
}
