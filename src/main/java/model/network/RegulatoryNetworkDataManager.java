package model.network;

import model.events.SetProteinConcentrationEvent;
import model.events.SetSignaledEvent;
import model.events.SimulationEvent;
import model.genes.ConcreteGene;
import model.genes.Gene;
import model.regulators.BooleanActivator;
import model.regulators.BooleanRepressor;
import model.regulators.MaxCompositeRegulator;
import model.regulators.MinCompositeRegulator;

import java.util.ArrayList;
import java.util.List;

public class RegulatoryNetworkDataManager {

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
