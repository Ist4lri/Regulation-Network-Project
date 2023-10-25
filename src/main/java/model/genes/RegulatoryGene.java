package model.genes;

/**
 * This interface represents a regulatory gene, which extends the basic gene interface and includes methods
 * for managing a gene's regulation.
 */
public interface RegulatoryGene extends BasicGene{
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
