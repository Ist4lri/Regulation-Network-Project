package model.genes;

import model.file.writer.GeneVisitor;

public interface Gene extends RegulatoryGene, RegulatedGene {

    public String accept(GeneVisitor visitor);

    public boolean getInitialIsSignaled();
};
