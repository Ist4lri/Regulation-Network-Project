package model.file.serializers.gene;

import javafx.scene.control.SingleSelectionModel;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
    }

    @Override
    public ConcreteGene deserialize(String string, RegulatoryNetworkReader reader) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
    }

    public synchronized static ConcreteGeneSerializer getInstance() {
        if (instance == null) {
            instance = new ConcreteGeneSerializer();
        }
        return instance;
    }
}
