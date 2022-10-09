package model.events;

import model.genes.RegulatoryGene;

import java.util.List;

public interface SimulationEvent extends Comparable<SimulationEvent> {
  void updateGenes();
  double getTime();

  List<RegulatoryGene> getGenes();

  String getInfo();

}
