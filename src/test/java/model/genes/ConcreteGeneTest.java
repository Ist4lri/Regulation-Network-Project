package model.genes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConcreteGeneTest {

    private ConcreteGene gene;

    @BeforeEach
    public void setUp() {
        double initialProteinConcentration = 10.0;
        double maximalProduction = 5.0;
        double degradationRate = 0.1;
        String name = "GeneA";
        boolean isSignaled = true;

        gene = new ConcreteGene(initialProteinConcentration, maximalProduction, degradationRate, name, isSignaled);
    }

    @Test
    public void testAllGettersOfClass() {
        assertThat(gene.getRegulator()).isNull();
        assertThat(gene.getProteinConcentration()).isEqualTo(10.0);
        assertThat(gene.getInitialProteinConcentration()).isEqualTo(10.0);
        assertThat(gene.getName()).isEqualTo("GeneA");
        assertThat(gene.getMaximalProduction()).isEqualTo(5.0);
        assertThat(gene.getDegradationRate()).isEqualTo(0.1);
        assertThat(gene.isSignaled()).isTrue();
    }

    @Test
    public void testSetProteinConcentration() {
        gene.setProteinConcentration(15.0);
        assertThat(gene.getProteinConcentration()).isEqualTo(15.0);
    }

    @Test
    public void testUpdate() {
        double duration = 2.0;
        double expectedProteinConcentration = 20.2;
        gene.update(duration);
        assertThat(gene.getProteinConcentration()).isEqualTo(expectedProteinConcentration);
    }
}