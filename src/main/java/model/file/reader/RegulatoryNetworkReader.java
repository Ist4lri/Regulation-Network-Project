package model.file.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.genes.Gene;
import model.network.RegulatoryNetwork;
import model.file.serializers.gene.EntitySerializer;

public class RegulatoryNetworkReader {
    private Map<String, EntitySerializer<? extends Gene>> geneSerializers;
    private Map<String, Gene> genes;

    public RegulatoryNetworkReader() {
        this.geneSerializers = new HashMap<>();
    };

    private void addGeneSerializer(EntitySerializer<? extends Gene> serializer) {
        this.geneSerializers.put(serializer.getCode(), serializer);
    }

    private EntitySerializer<? extends Gene> getGeneSerializer(String code) {
        return this.geneSerializers.get(code);
    }

    public void addGene(Gene gene) {
        this.genes.put(gene.getName(), gene);
    }

    public Gene getGene(String geneName) {
        return this.genes.get(geneName);
    }

    public RegulatoryNetwork read(BufferedReader bufferedReader) throws IOException {
        this.genes = new HashMap<>();
        for (String line = ""; line != null; line = bufferedReader.readLine()) {
            String[] dispatchElement = line.split(" ");
            switch (dispatchElement[0]) {
                case "TimeStep", "TimeUpperBound":
                    break;
                default:
                    if (this.geneSerializers.containsKey(dispatchElement[0])) {
                        this.addGene(getGeneSerializer(dispatchElement[0]).deserialize(line, null));
                    }
            }
        }
        return new RegulatoryNetwork(new ArrayList<>(this.genes.values()), null, 0, 0);
    }
}
