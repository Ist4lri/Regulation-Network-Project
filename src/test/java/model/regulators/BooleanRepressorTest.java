package model.regulators;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.genes.ConcreteGene;
import model.genes.Gene;

public class BooleanRepressorTest {

    private BooleanRepressor regulator1;
    private BooleanRepressor regulator2;

    @BeforeEach
    public void setUp() {
        Gene gene1 = new ConcreteGene("N", 1.0, 0.2, 6.0, true);
        Gene gene2 = new ConcreteGene("O", 2.0, 0.12, 2.0, false);

        regulator1 = new BooleanRepressor(5.0, gene1);
        regulator2 = new BooleanRepressor(2.0, gene2);
    }

    @Test
    public void testInputFunction() {
        assertThat(regulator1.inputFunction()).isZero();
        assertThat(regulator2.inputFunction()).isOne();
    }

    @Test
    public void testGeneIsSignaled() {
        assertThat(regulator1.geneIsSignaled()).isTrue();
        assertThat(regulator2.geneIsSignaled()).isFalse();

    }

    @Test
    public void testDescription() {
        assertThat(regulator1.description()).isEqualTo("5.0 N");
        assertThat(regulator2.description()).isEqualTo("2.0 O");
    }

    @Test
    public void testThresholdIsAttained() {
        assertThat(regulator1.thresholdIsAttained()).isTrue();
        assertThat(regulator2.thresholdIsAttained()).isTrue();
    }
}
