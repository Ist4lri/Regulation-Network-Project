package model.events;

import java.util.List;

import model.genes.Gene;

public class SetProteinConcentrationEvent extends AbstractSimulationEvent {
    private double newConcentration;

    protected SetProteinConcentrationEvent(List<Gene> genes, double time, double newConcentration) {
        super(genes, time);
        this.newConcentration = newConcentration;
    }

    @Override
    public String description() {
        return "New Concentration " + this.newConcentration;
    }

    @Override
    protected void updateGene(Gene gene) {
        gene.setProteinConcentration(newConcentration);
    }

}
