package model.events;

import java.util.List;

import model.file.writer.EventVisitor;
import model.genes.Gene;

public class SetProteinConcentrationEvent extends AbstractSimulationEvent {
    private double newConcentration;

    public SetProteinConcentrationEvent(List<Gene> genes, double time, double newConcentration) {
        super(genes, time);
        this.newConcentration = newConcentration;
    }

    @Override
    public String description() {
        return this.newConcentration + "";
    }

    @Override
    protected void updateGene(Gene gene) {
        gene.setProteinConcentration(this.newConcentration);
    }

    @Override
    public String accept(EventVisitor visitor) {
        return visitor.visit(this);
    }

}
