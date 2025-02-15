package model.regulators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AlwaysOnRegulatorTest {

    private AlwaysOnRegulator regulator;

    @BeforeEach
    public void setUp() {
        regulator = new AlwaysOnRegulator();
    }

    @Test
    void testDescription() {
        assertThat(regulator.description()).isNullOrEmpty();
    }

    @Test
    void testInputFunction() {
        assertThat(regulator.inputFunction()).isOne();
    }
}