package model.regulators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import model.genes.ConcreteGene;
import model.genes.Gene;

public class MaxCompositeRegulatorTest {

    private MaxCompositeRegulator regulator;

    @BeforeEach
    public void setUp() {
        Gene gene1 = new ConcreteGene("N", 1.0, 0.2, 6.0, true);
        Gene gene2 = new ConcreteGene("O", 2.0, 0.12, 2.0, false);
        Gene gene3 = new ConcreteGene("P", 1.0, 0.21, 3.0, true);

        BooleanActivator regulator1 = new BooleanActivator(5.0, gene1);
        BooleanActivator regulator2 = new BooleanActivator(2.0, gene2);
        BooleanRepressor regulator3 = new BooleanRepressor(3.0, gene3);

        List<Regulator> regulators = new ArrayList<>();
        regulators.add(regulator1);
        regulators.add(regulator2);
        regulators.add(regulator3);

        regulator = new MaxCompositeRegulator(regulators);
    }

    @Test
    void testCumulativeValue() {
        assertThat(regulator.cumulativeValue(7.0, regulator.initialValue())).isEqualTo(7.0);
        assertThat(regulator.cumulativeValue(10.0, 7.0)).isEqualTo(10.0);
    }

    @Test
    public void testInitialValue() {
        assertThat(regulator.initialValue()).isZero();
    }

    @Test
    public void testInputFunction() {
        assertThat(regulator.inputFunction()).isOne();
    }

    @Test
    public void description() {
        assertThat(regulator.description())
                .isEqualTo("BooleanActivator 5.0 N,BooleanActivator 2.0 O,BooleanRepressor 3.0 P");
    }
}
