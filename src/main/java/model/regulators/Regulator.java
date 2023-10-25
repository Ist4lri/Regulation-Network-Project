package model.regulators;

/**
 * This interface represents a regulator, which defines methods for the input value of the regulator, i.e.
 * the proportion of maximal production of the protein that is produced by the regulated gene.
 */
public interface Regulator {
  /**
   * Compute and provide the input value for a regulated gene.
   *
   * @return The input value generated by the regulator.
   */
  double inputFunction();

  /**
   * Get a description of this regulator.
   *
   * @return A string describing the regulator.
   */
  String description();
}

