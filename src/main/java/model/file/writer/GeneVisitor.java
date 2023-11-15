package model.file.writer;

import model.genes.ConcreteGene;
import model.genes.ConstantGene;

public interface GeneVisitor {

    public String visit(ConcreteGene gene);

    public String visit(ConstantGene gene);
}
