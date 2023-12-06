package model.events;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.genes.ConcreteGene;
import model.genes.Gene;

public class SetSignaledEventTest {

    private SetSignaledEvent event;
    private List<Gene> genes;

    @BeforeEach
    public void setUp() {
        Gene gene1 = new ConcreteGene("N", 1.0, 0.2, 4.0, true);
        Gene gene2 = new ConcreteGene("O", 2.0, 0.12, 2.0, true);

        genes = Arrays.asList(gene1, gene2);
        event = new SetSignaledEvent(genes, 1.0, false);
    }

    @Test
    public void testDescription() {
        assertThat(event.description()).isEqualTo("false");
    }

    @Test
    public void testUpdateGene() {
        event.updateGenes();
        for (Gene gene : genes) {
            assertThat(gene.isSignaled()).isEqualTo(false);
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
