package model.genes;

import model.regulators.Regulator;

/**
 * This interface represents a regulatory gene, which extends the basic gene interface and includes methods
 * for managing a gene's regulation.
 */
public interface RegulatoryGene extends Gene {

  /**
   * Get the regulator associated with this regulatory gene.
   *
   * @return The regulator linked to this gene.
   */
  Regulator getRegulator();

  /**
   * Set the regulator associated with this regulatory gene.
   *
   * @param regulator The regulator to associate with this gene.
   */
  void setRegulator(Regulator regulator);

  /**
   * Check if this regulatory gene is currently signaled, i.e., is influenced by its regulator.
   *
   * @return {@code true} if the gene is signaled, {@code false} otherwise.
   */
  boolean isSignaled();

  /**
   * Set the signaling status of this regulatory gene.
   *
   * @param isSignaled {@code true} to signal the gene, {@code false} to deactivate it.
   */
  void setSignaled(boolean isSignaled);
}
