package model.events;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.genes.ConcreteGene;
import model.genes.Gene;

public class SetProteinConcentrationEventTest {

    private SetProteinConcentrationEvent event;
    private List<Gene> genes;

    @BeforeEach
    public void setUp() {
        Gene gene1 = new ConcreteGene("N", 1.0, 0.2, 4.0, true);
        Gene gene2 = new ConcreteGene("O", 2.0, 0.12, 2.0, true);

        genes = Arrays.asList(gene1, gene2);
        event = new SetProteinConcentrationEvent(genes, 1.0, 5.0);
    }

    @Test
    public void testDescription() {
        assertThat(event.description()).isEqualTo(5.0 + "");
    }

    @Test
    public void testUpdateGene() {
        event.updateGenes();
        for (Gene gene : genes) {
            assertThat(gene.getProteinConcentration()).isEqualTo(5.0);
        }
    }

    @Test
    public void testGetGenes() {
        assertThat(event.getGenes()).isEqualTo(genes);
    }

    @Test
    public void testGetTime() {
        assertThat(event.getTime()).isEqualTo(1.0);
    }

}
