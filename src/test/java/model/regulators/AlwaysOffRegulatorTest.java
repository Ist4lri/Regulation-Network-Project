package model.regulators;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlwaysOffRegulatorTest {

    private AlwaysOffRegulator regulator;

    @BeforeEach
    public void setUp() {
        regulator = new AlwaysOffRegulator();
    }

    @Test
    void testDescription() {
        assertThat(regulator.description()).isNullOrEmpty();
    }

    @Test
    void testInputFunction() {
        assertThat(regulator.inputFunction()).isZero();
    }
}
