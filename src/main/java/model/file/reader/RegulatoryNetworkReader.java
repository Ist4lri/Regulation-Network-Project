package model.file.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.genes.Gene;
import model.network.RegulatoryNetwork;
import model.file.serializers.gene.ConcreteGeneSerializer;
import model.file.serializers.gene.ConstantGeneSerializer;
import model.file.serializers.gene.EntitySerializer;

public class RegulatoryNetworkReader {
    private Map<String, EntitySerializer<? extends Gene>> geneSerializers;
    private Map<String, Gene> genes;

    public RegulatoryNetworkReader() {
        this.geneSerializers = new HashMap<>();
        this.addGeneSerializer(ConcreteGeneSerializer.getInstance());
        this.addGeneSerializer(ConstantGeneSerializer.getInstance());
        this.genes = new HashMap<>();
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
        double timeStep = 0;
        double timeUpperBound = 0;
        int lineCounter = 0;
        for (String line = ""; line != null; line = bufferedReader.readLine()) {
            String[] dispatchElement = line.split(" ");
            switch (dispatchElement[0]) {
                case "TimeStep":
                    timeStep = Double.parseDouble(dispatchElement[1]);
                    break;
                case "TimeUpperBound":
                    timeUpperBound = Double.parseDouble(dispatchElement[1]);
                    break;
                default:
                    if (this.geneSerializers.containsKey(dispatchElement[0])) {
                        this.addGene(getGeneSerializer(dispatchElement[0]).deserialize(line, this));
                    } else {
                        throw new IOException("Error : Declare new geneSerializers type at this line :" + lineCounter);
                    }
            }
            lineCounter++;
        }
        return new RegulatoryNetwork(new ArrayList<>(this.genes.values()), new ArrayList<>(), timeStep, timeUpperBound);
    }
}
