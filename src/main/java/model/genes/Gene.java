package model.genes;

public interface Gene {
  double getProteinConcentration();
  double getInitialProteinConcentration();
  void setProteinConcentration(double proteinConcentration);
  String toString();
  void update();
}
