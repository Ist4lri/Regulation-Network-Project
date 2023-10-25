package model.network;

import javafx.scene.chart.XYChart;
import model.events.SimulationEvent;
import model.genes.Gene;
import model.genes.RegulatedGene;

import java.util.ArrayList;
import java.util.List;

public class RegulatoryNetwork {
  private final List<Gene> genes;
  private final double timeStepLength;
  private final double timeUpperBound;
  private List<XYChart.Series<Number, Number>> seriesList;

  public List<Gene> getGenes() {
    return genes;
  }

  public List<SimulationEvent> getSimulationEvents() {
    return simulationEvents;
  }

  public double getTimeStepLength() {
    return timeStepLength;
  }

  public double getTimeUpperBound() {
    return timeUpperBound;
  }

  private final List<SimulationEvent> simulationEvents = new ArrayList<>();

  public RegulatoryNetwork(List<Gene> genes, List<SimulationEvent> simulationEvents, double timeStepLength, double timeUpperBound) {
    this.genes = genes;
    this.timeStepLength = timeStepLength;
    this.timeUpperBound = timeUpperBound;
    this.simulationEvents.addAll(simulationEvents);
  }

  public List<XYChart.Series<Number, Number>> getData() {
    initializeSeriesList();
    simulateGenes();
    return seriesList;
  }

  private void simulateGenes() {
    for(int stepCount = 0; stepCount * timeStepLength <= timeUpperBound; stepCount++){
      double time = stepCount * timeStepLength;
      manageEvents(time);
      collectData(time);
      updateGenes();
    }
  }

  private void manageEvents(double time) {
    List<SimulationEvent> currentEvents = getCurrentEvents(time);
    applyToGenes(currentEvents);
  }

  private void applyToGenes(List<SimulationEvent> currentEvents) {
    for(SimulationEvent event : currentEvents)
      event.updateGenes();
  }

  private List<SimulationEvent> getCurrentEvents(double time) {
    List<SimulationEvent> currentEvents = new ArrayList<>();
    for(SimulationEvent event : simulationEvents) {
      if (Math.abs(event.getTime() - time) < timeStepLength/2.) {
        currentEvents.add(event);
      }
    }
    return currentEvents;
  }

  private void updateGenes() {
    for (RegulatedGene gene : genes) {
      gene.update(timeStepLength);
    }
  }

  private void collectData(double time) {
    for(int index = 0; index < genes.size(); index++) {
      seriesList.get(index).getData().add(new XYChart.Data<>(time, genes.get(index).getProteinConcentration()));
    }
  }


  private void initializeSeriesList() {
    seriesList = new ArrayList<>();

    for (RegulatedGene gene : genes) {
      XYChart.Series<Number, Number> series = new XYChart.Series<>();
      series.setName(gene.getName());
      seriesList.add(series);
    }
  }

}
