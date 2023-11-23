package model.file.reader;

import java.io.BufferedReader;
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
        this.genes = new HashMap<>();
    };

    private void addGeneSerializer(EntitySerializer<? extends Gene> serializer) {

    }

    private EntitySerializer<? extends Gene> getGenSerializer(String code) {

    }

    public void addGene(Gene gene) {
        this.genes.put(gene.getName(), gene);
    }

    public Gene getGene(String geneName) {
        return this.genes.get(geneName);
    }

    public RegulatoryNetwork read(BufferedReader bufferedReader) {
        String line = bufferedReader.readLine();
        while (line != null) {

        }
    }
}
