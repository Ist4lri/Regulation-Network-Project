package model.genes;

/**
 * This interface represents a gene that encodes a protein. It manages the protein concentration.
 */
public interface BasicGene {

  /**
   * Get the current protein concentration encoded by this gene.
   *
   * @return The current protein concentration.
   */
  double getProteinConcentration();

  /**
   * Get the initial protein concentration encoded by this gene.
   *
   * @return The initial protein concentration.
   */
  double getInitialProteinConcentration();

  /**
   * Set the protein concentration encoded by this gene to the specified value.
   *
   * @param proteinConcentration The new protein concentration to set.
   */
  void setProteinConcentration(double proteinConcentration);

  /**
   * Get the name of this gene.
   *
   * @return The name of the gene.
   */
  String getName();

  /**
   * Update the behavior of this gene for the specified duration.
   * The protein concentration encoded by this gene is updated according
   * to the production of the gene and the degradation rate of the protein.
   *
   * @param duration The duration for which to update the gene's behavior.
   */
  void update(double duration);

  /**
   * Get the maximal production rate of protein by this gene.
   *
   * @return The maximal protein production rate.
   */
  double getMaximalProduction();

  /**
   * Get the degradation rate of the protein encoded by this gene.
   *
   * @return The protein degradation rate.
   */
  double getDegradationRate();
}

