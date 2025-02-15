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
            toSend += gene.getName() + ",";
        }
        return "[" + toSend.substring(0, toSend.length() - 1) + "]";

    }

    @Override
    public List<Gene> deserialize(String string, RegulatoryNetworkReader reader) {
        List<Gene> listOfGene = new ArrayList<>();
        String[] toDispatch = string.split("");
        for (String character : toDispatch) {
            if (!(character.equals(",") || character.equals("[") || character.equals("]"))) {
                Gene gene = reader.getGene(character);
                if (gene != null) {
                    listOfGene.add(gene);
                } else {
                    throw new IllegalArgumentException(
                            "Gene Unknown. PLease, verify your gene Name in your Event lines.");
                }
            }
        }
        return listOfGene;
    }
}
