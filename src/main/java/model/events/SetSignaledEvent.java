package model.events;

import java.util.List;

import model.file.writer.EventVisitor;
import model.genes.Gene;

public class SetSignaledEvent extends AbstractSimulationEvent {
    private boolean newSignaledValue;

    public SetSignaledEvent(List<Gene> genes, double time, boolean newSignaledValue) {
        super(genes, time);
        this.newSignaledValue = newSignaledValue;
    }

    @Override
    public String description() {
        return "" + this.newSignaledValue;
    }

    @Override
    protected void updateGene(Gene gene) {
        gene.setSignaled(this.newSignaledValue);
    }

    @Override
    public String accept(EventVisitor visitor) {
        return visitor.visit(this);
    }

}
