package model.regulators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AlwaysOnRegulatorTest {

    @Test
    public void testInputFunction() {
        AlwaysOnRegulator regulator = new AlwaysOnRegulator();
        assertThat(regulator.inputFunction()).isEqualTo(1.0);
    }

    @Test
    public void testDescription() {
        AlwaysOnRegulator regulator = new AlwaysOnRegulator();
        assertThat(regulator.description()).isEqualTo("");
    }
}