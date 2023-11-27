package model.file.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.genes.Gene;
import model.network.RegulatoryNetwork;
import model.events.SimulationEvent;
import model.file.serializers.event.SetProteinConcentrationEventSerializer;
import model.file.serializers.event.SetSignaledEventSerializer;
import model.file.serializers.gene.ConcreteGeneSerializer;
import model.file.serializers.gene.ConstantGeneSerializer;
import model.file.serializers.gene.EntitySerializer;

public class RegulatoryNetworkReader {
    private Map<String, EntitySerializer<? extends Gene>> geneSerializers;
    private Map<String, EntitySerializer<? extends SimulationEvent>> eventSerializers;
    private Map<String, Gene> genes;
    private ArrayList<SimulationEvent> simulationEvents;

    public RegulatoryNetworkReader() {
        this.geneSerializers = new HashMap<>();
        this.addGeneSerializer(ConcreteGeneSerializer.getInstance());
        this.addGeneSerializer(ConstantGeneSerializer.getInstance());
        this.eventSerializers = new HashMap<>();
        this.addEventSerializer(SetSignaledEventSerializer.getInstance());
        this.addEventSerializer(SetProteinConcentrationEventSerializer.getInstance());
        this.genes = new HashMap<>();
        this.simulationEvents = new ArrayList<>();
    };

    private void addGeneSerializer(EntitySerializer<? extends Gene> serializer) {
        this.geneSerializers.put(serializer.getCode(), serializer);
    }

    private void addEventSerializer(EntitySerializer<? extends SimulationEvent> serializer) {
        this.eventSerializers.put(serializer.getCode(), serializer);
    }

    private EntitySerializer<? extends Gene> getGeneSerializer(String code) {
        return this.geneSerializers.get(code);
    }

    private EntitySerializer<? extends SimulationEvent> getEventSerializer(String code) {
        return this.eventSerializers.get(code);
    }

    public void addGene(Gene gene) {
        this.genes.put(gene.getName(), gene);
    }

    public void addSiumlationEvents(SimulationEvent event) {
        this.simulationEvents.add(event);
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
                    } else if (this.eventSerializers.containsKey(dispatchElement[0])) {
                        this.addSiumlationEvents(getEventSerializer(dispatchElement[0]).deserialize(line, this));
                    } else {
                        throw new IOException("Error : Declare new geneSerializers type at this line :" + lineCounter);
                    }
            }
            lineCounter++;
        }
        return new RegulatoryNetwork(new ArrayList<>(this.genes.values()),
                this.simulationEvents, timeStep, timeUpperBound);
    }
}
