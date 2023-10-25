package model.genes;

import model.regulators.Regulator;

/**
 * This interface represents a regulated gene, which extends the basic gene interface and includes methods
 * for managing a gene's regulation.
 */
public interface RegulatedGene extends BasicGene {

  /**
   * Get the regulator associated with this regulatory gene.
   *
   * @return The regulator linked to this gene.
   */
  Regulator getRegulator();

  /**
   * Set the regulator associated with this regulated gene.
   *
   * @param regulator The regulator to associate with this gene.
   */
  void setRegulator(Regulator regulator);
}
