package model.events;

import model.file.writer.EventVisitor;
import model.genes.Gene;

import java.util.List;

/**
 * This interface represents an event in a simulation that involves updating
 * genes.
 */
public interface SimulationEvent {

  /**
   * Update the state of genes associated with this event.
   */
  void updateGenes();

  /**
   * Get the time at which this event occurs in the simulation.
   *
   * @return The time of the event.
   */
  double getTime();

  /**
   * Get the list of regulatory genes affected by this event.
   *
   * @return A list of regulatory genes associated with this event.
   */
  List<Gene> getGenes();

  /**
   * Get a description of this simulation event.
   *
   * @return A string describing the event.
   */
  String description();

  String accept(EventVisitor visitor);
}
