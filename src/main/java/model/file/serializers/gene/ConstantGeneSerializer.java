package model.file.serializers.gene;

import javafx.scene.control.SingleSelectionModel;
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
        // TODO Auto-generated method : need to implement serialize.
    }

    @Override
    public ConstantGene deserialize(String string, RegulatoryNetworkReader reader) {
        // TODO Auto-generated method : need to implement deserialize
    }

    public synchronized static ConstantGeneSerializer getInstance() {
        if (instance == null) {
            instance = new ConstantGeneSerializer();
        }
        return instance;
    }
}
