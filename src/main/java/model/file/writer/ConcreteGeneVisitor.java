package model.file.writer;

import model.file.serializers.gene.ConcreteGeneSerializer;
import model.file.serializers.gene.ConstantGeneSerializer;
import model.genes.ConcreteGene;
import model.genes.ConstantGene;

public class ConcreteGeneVisitor implements GeneVisitor {
    private RegulatoryNetworkWriter writer;

    public ConcreteGeneVisitor(RegulatoryNetworkWriter writer) {
        this.writer = writer;
    }

    @Override
    public String visit(ConcreteGene gene) {
        return ConcreteGeneSerializer.getInstance().serialize(gene, this.writer);
    }

    @Override
    public String visit(ConstantGene gene) {
        return ConstantGeneSerializer.getInstance().serialize(gene, this.writer);
    }

}
