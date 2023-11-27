package model.file.serializers.list;

import java.util.ArrayList;
import java.util.List;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.Gene;

public class ListGeneSerializer implements EntitySerializer<List<Gene>> {

    @Override
    public String getCode() {
        return "ListGene";
    }

    @Override
    public String serialize(List<Gene> entity, RegulatoryNetworkWriter writer) {
        String toSend = "";
        for (Gene gene : entity) {
            toSend += "," + gene.getName();
        }
        return "[" + toSend + "]";

    }

    @Override
    public List<Gene> deserialize(String string, RegulatoryNetworkReader reader) {
        List<Gene> listOfGene = new ArrayList<>();
        String[] toDispatch = string.split("");
        for (String character : toDispatch) {
            if (!(character.equals(",") || character.equals("[") || character.equals("]"))) {
                listOfGene.add(reader.getGene(character));
            }
        }
        return listOfGene;
    }
}
