package model.file.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.genes.Gene;
import model.network.RegulatoryNetwork;
import model.regulators.Regulator;
import model.events.SimulationEvent;
import model.file.serializers.event.SetProteinConcentrationEventSerializer;
import model.file.serializers.event.SetSignaledEventSerializer;
import model.file.serializers.gene.ConcreteGeneSerializer;
import model.file.serializers.gene.ConstantGeneSerializer;
import model.file.serializers.gene.EntitySerializer;
import model.file.serializers.regulators.AlwaysOffRegulatorSerializer;
import model.file.serializers.regulators.AlwaysOnRegulatorSerializer;
import model.file.serializers.regulators.BooleanActivatorSerializer;
import model.file.serializers.regulators.BooleanRepressorSerializer;
import model.file.serializers.regulators.MaxCompositeRegulatorSerializer;
import model.file.serializers.regulators.MinCompositeRegulatorSerializer;

public class RegulatoryNetworkReader {
    private Map<String, EntitySerializer<? extends Gene>> geneSerializers;
    private Map<String, EntitySerializer<? extends SimulationEvent>> eventSerializers;
    private Map<String, EntitySerializer<? extends Regulator>> regulatorSerializers;
    private Map<String, Gene> genes;
    private Map<String, Regulator> regulators;
    private ArrayList<SimulationEvent> simulationEvents;

    public RegulatoryNetworkReader() {
        this.geneSerializers = new HashMap<>();
        this.addGeneSerializer(ConcreteGeneSerializer.getInstance());
        this.addGeneSerializer(ConstantGeneSerializer.getInstance());
        this.eventSerializers = new HashMap<>();
        this.addEventSerializer(SetSignaledEventSerializer.getInstance());
        this.addEventSerializer(SetProteinConcentrationEventSerializer.getInstance());
        this.regulatorSerializers = new HashMap<>();
        this.addRegulatorSerializer(AlwaysOffRegulatorSerializer.getInstance());
        this.addRegulatorSerializer(AlwaysOnRegulatorSerializer.getInstance());
        this.addRegulatorSerializer(BooleanActivatorSerializer.getInstance());
        this.addRegulatorSerializer(BooleanRepressorSerializer.getInstance());
        this.addRegulatorSerializer(MaxCompositeRegulatorSerializer.getInstance());
        this.addRegulatorSerializer(MinCompositeRegulatorSerializer.getInstance());
        this.genes = new HashMap<>();
        this.simulationEvents = new ArrayList<>();
    };

    private void addGeneSerializer(EntitySerializer<? extends Gene> serializer) {
        this.geneSerializers.put(serializer.getCode(), serializer);
    }

    private void addEventSerializer(EntitySerializer<? extends SimulationEvent> serializer) {
        this.eventSerializers.put(serializer.getCode(), serializer);
    }

    private void addRegulatorSerializer(EntitySerializer<? extends Regulator> serializer) {
        this.regulatorSerializers.put(serializer.getCode(), serializer);
    }

    private EntitySerializer<? extends Gene> getGeneSerializer(String code) {
        return this.geneSerializers.get(code);
    }

    private EntitySerializer<? extends SimulationEvent> getEventSerializer(String code) {
        return this.eventSerializers.get(code);
    }

    private EntitySerializer<? extends Regulator> getRegulatorSerializer(String code) {
        return this.regulatorSerializers.get(code);
    }

    public void addGene(Gene gene) {
        this.genes.put(gene.getName(), gene);
    }

    public void addSiumlationEvents(SimulationEvent event) {
        this.simulationEvents.add(event);
    }

    public void addRegulator(Regulator regulator) {
        this.regulators.put(regulator.getClass().getSimpleName(), regulator);
    }

    public Regulator getRegulators(String regulatorName) {
        return regulators.get(regulatorName);
    }

    public Gene getGene(String geneName) {
        return this.genes.get(geneName);
    }

    public RegulatoryNetwork read(BufferedReader bufferedReader) throws IOException {
        double timeStep = 0;
        double timeUpperBound = 0;
        int lineCounter = 0;
        for (String line = ""; line != null; line = bufferedReader.readLine()) {
            lineCounter++;
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
                    } else if (this.regulatorSerializers.containsKey(dispatchElement[0])) {
                        this.addRegulator(getRegulatorSerializer(dispatchElement[0]).deserialize(line, this));
                    } else {
                        throw new IOException("Error at : " + lineCounter);
                    }
            }
        }
        return new RegulatoryNetwork(new ArrayList<>(this.genes.values()),
                this.simulationEvents, timeStep, timeUpperBound);
    }
}
