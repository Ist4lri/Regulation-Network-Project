package model.events;

import model.genes.RegulatoryGene;

import java.util.List;

public interface SimulationEvent  {
  void updateGenes();
  double getTime();

  List<RegulatoryGene> getGenes();

  String getInfo();

}
