package model.network;

import model.events.SetProteinConcentrationEvent;
import model.events.SetSignaledEvent;
import model.events.SimulationEvent;
import model.genes.ConcreteGene;
import model.genes.ConstantGene;
import model.genes.Gene;
import model.regulators.BooleanActivator;
import model.regulators.BooleanRepressor;
import model.regulators.MaxCompositeRegulator;
import model.regulators.MinCompositeRegulator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegulatoryNetworkDataManager {
  public void write(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
    bufferedWriter.write("TimeStep " + regulatoryNetwork.getTimeStepLength() +
        "\n");
    bufferedWriter.write("TimeUpperBound " +
        regulatoryNetwork.getTimeUpperBound() + "\n");
    writeGenes(bufferedWriter, regulatoryNetwork);
    writeEvents(bufferedWriter, regulatoryNetwork);
  }

  private static void writeEvents(BufferedWriter bufferedWriter,
      RegulatoryNetwork regulatoryNetwork)
      throws IOException {
    for (SimulationEvent event : regulatoryNetwork.getSimulationEvents()) {
      StringBuilder eventString = new StringBuilder(event.getClass().getSimpleName() + " ");
      eventString.append(event.getTime()).append(" ");
      List<Gene> genes = event.getGenes();
      for (int index = 0; index < genes.size(); index++) {
        if (index != 0) {
          eventString.append(",");
        }
        eventString.append(genes.get(index));
      }
      eventString.append(" ");
      eventString.append(event).append("\n");
      bufferedWriter.write(eventString.toString());
    }
  }

  private static void writeGenes(BufferedWriter bufferedWriter,
      RegulatoryNetwork regulatoryNetwork)
      throws IOException {
    for (Gene gene : regulatoryNetwork.getGenes()) {
      String geneString = gene.getClass().getSimpleName() + " ";
      geneString += gene.getName() + " ";
      geneString += gene.getMaximalProduction() + " ";
      geneString += gene.getDegradationRate() + " ";
      geneString += gene.getInitialProteinConcentration() + " ";
      geneString += gene.isSignaled() + "\n";
      geneString += gene.getRegulator().getClass().getSimpleName() + " ";
      geneString += gene.getName() + " ";
      geneString += gene.getRegulator().description() + "\n";

      bufferedWriter.write(geneString);
    }
  }

  public RegulatoryNetwork read(BufferedReader bufferedReader) throws IOException {
    Map<String, Gene> genes = new HashMap<>();
    List<SimulationEvent> events = new ArrayList<>();

    double timeUpperBound = 20;
    double timeStepLength = 0.01;

    int lineNumber = 0;

    for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
      String[] tokens = line.split(" ");
      switch (tokens[0]) {
        case "TimeStep" -> timeStepLength = Double.parseDouble(tokens[1]);
        case "TimeUpperBound" -> timeUpperBound = Double.parseDouble(tokens[1]);
        case "ConstantRegulatoryGene" -> readConstantRegulatoryGene(genes, tokens);
        default -> throw new IOException("Parse error line " + lineNumber);
      }
      lineNumber++;
    }
    return new RegulatoryNetwork(new ArrayList<>(genes.values()), events,
        timeStepLength, timeUpperBound);
  }

  private static void readConstantRegulatoryGene(Map<String, Gene> genes,
      String[] tokens) {
    String name = tokens[1];
    double concentration = Double.parseDouble(tokens[2]);
    boolean isSignaled = Boolean.parseBoolean(tokens[3]);
    genes.put(name, new ConstantGene(name, concentration, isSignaled));
  }

  public RegulatoryNetwork generate() {
    List<Gene> genes = new ArrayList<>();
    Gene x = new ConcreteGene("X", 3.0, 0.1, 2.0, true);
    genes.add(x);
    Gene y = new ConcreteGene("Y", 4.0, 0.12, 2.0, true);
    genes.add(y);
    Gene z = new ConcreteGene("Z", 5.0, 0.15, 2.0, true);
    genes.add(z);
    BooleanActivator booleanActivator1 = new BooleanActivator(3, x);
    BooleanRepressor booleanRepressor1 = new BooleanRepressor(7, y);
    BooleanActivator booleanActivator2 = new BooleanActivator(1, z);
    BooleanRepressor booleanRepressor2 = new BooleanRepressor(2, y);
    BooleanActivator booleanActivator3 = new BooleanActivator(2, x);
    BooleanRepressor booleanRepressor3 = new BooleanRepressor(2, z);
    MinCompositeRegulator minCompositeRegulator = new MinCompositeRegulator(
        List.of(booleanActivator1, booleanActivator2));
    MaxCompositeRegulator maxCompositeRegulator1 = new MaxCompositeRegulator(
        List.of(booleanRepressor1, booleanRepressor2));
    MaxCompositeRegulator maxCompositeRegulator2 = new MaxCompositeRegulator(
        List.of(booleanRepressor3, booleanActivator3));
    x.setRegulator(maxCompositeRegulator1);
    y.setRegulator(maxCompositeRegulator2);
    z.setRegulator(minCompositeRegulator);
    List<SimulationEvent> simulationEvents = new ArrayList<>();
    simulationEvents.add(new SetProteinConcentrationEvent(List.of(x), 10.0, 3.0));
    simulationEvents.add(new SetProteinConcentrationEvent(List.of(x, y), 5.0, 4.0));
    simulationEvents.add(new SetSignaledEvent(List.of(x, y), 15.0, false));
    return new RegulatoryNetwork(genes, simulationEvents, 0.01, 20.0);
  }

}
