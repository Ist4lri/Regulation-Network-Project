package model.genes;

public interface Gene {
  double getProteinConcentration();
  double getInitialProteinConcentration();
  void setProteinConcentration(double proteinConcentration);
  String getName();
  void update(double duration);
  double getMaximalProduction();
  double getDegradationRate();
}
