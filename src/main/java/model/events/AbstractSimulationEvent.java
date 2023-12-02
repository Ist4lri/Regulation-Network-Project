package model.events;

import java.util.List;

import model.genes.Gene;

public abstract class AbstractSimulationEvent implements SimulationEvent {

    private final double time;
    private List<Gene> genes;

    protected AbstractSimulationEvent(List<Gene> genes, double time) {
        this.time = time;
        this.genes = genes;
    }

    @Override
    public void updateGenes() {
        for (Gene gene : this.genes) {
            updateGene(gene);
        }
    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public List<Gene> getGenes() {
        return this.genes;
    }

    protected abstract void updateGene(Gene gene);

}
