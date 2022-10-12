package model.genes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConstantRegulatoryGeneTest {
  public static final double EPSILON = 0.0000001;

  private ConstantRegulatoryGene geneX;
  private ConstantRegulatoryGene geneY;
  private ConstantRegulatoryGene geneZ;

  @BeforeEach
  void initializeGenes(){
    geneX = new ConstantRegulatoryGene("X", 1, true);
    geneY = new ConstantRegulatoryGene("Y", 2, false);
    geneZ = new ConstantRegulatoryGene("Z", 3, false);
  }

  @Test
  void testGetProteinConcentration_afterConstruction(){
    assertThat(geneX.getProteinConcentration()).isCloseTo(1, within(EPSILON));
    assertThat(geneY.getProteinConcentration()).isCloseTo(2, within(EPSILON));
    assertThat(geneZ.getProteinConcentration()).isCloseTo(3, within(EPSILON));
  }

  @Test
  void testGetInitialProteinConcentration_afterConstruction(){
    assertThat(geneX.getInitialProteinConcentration()).isCloseTo(1, within(EPSILON));
    assertThat(geneY.getInitialProteinConcentration()).isCloseTo(2, within(EPSILON));
    assertThat(geneZ.getInitialProteinConcentration()).isCloseTo(3, within(EPSILON));
  }

  @Test
  void testGetInitialProteinConcentration_afterSettingProteinConcentration() {
    geneX.setProteinConcentration(11);
    assertThat(geneX.getInitialProteinConcentration()).isCloseTo(1, within(EPSILON));
    geneY.setProteinConcentration(10);
    assertThat(geneY.getInitialProteinConcentration()).isCloseTo(2, within(EPSILON));
    geneZ.setProteinConcentration(2);
    assertThat(geneZ.getInitialProteinConcentration()).isCloseTo(3, within(EPSILON));
  }

  @Test
  void testGetProteinConcentration_afterSettingProteinConcentration() {
    geneX.setProteinConcentration(11);
    assertThat(geneX.getProteinConcentration()).isCloseTo(11, within(EPSILON));
    geneY.setProteinConcentration(10);
    assertThat(geneY.getProteinConcentration()).isCloseTo(10, within(EPSILON));
    geneZ.setProteinConcentration(21);
    assertThat(geneZ.getProteinConcentration()).isCloseTo(21, within(EPSILON));
  }

  @Test
  void testGetName(){
    assertThat(geneX.getName()).isEqualTo("X");
    assertThat(geneY.getName()).isEqualTo("Y");
    assertThat(geneZ.getName()).isEqualTo("Z");
  }

  @Test
  void testIsSignaled(){
    assertThat(geneX.isSignaled()).isTrue();
    assertThat(geneY.isSignaled()).isFalse();
    assertThat(geneZ.isSignaled()).isFalse();
  }

  @Test
  void testSetSignaled() {
    assertThat(geneX.isSignaled()).isTrue();
    geneX.setSignaled(false);
    assertThat(geneX.isSignaled()).isFalse();

    assertThat(geneY.isSignaled()).isFalse();
    geneY.setSignaled(true);
    assertThat(geneY.isSignaled()).isTrue();

    assertThat(geneZ.isSignaled()).isFalse();
    geneX.setSignaled(true);
    assertThat(geneX.isSignaled()).isTrue();
  }

  @Test
  void testGetMaximalProduction(){
    assertThat(geneX.getMaximalProduction()).isCloseTo(0, withinPercentage(0.001));
    assertThat(geneY.getMaximalProduction()).isCloseTo(0, withinPercentage(0.001));
  }

  @Test
  void testGetDegradationRate(){
    assertThat(geneX.getDegradationRate()).isCloseTo(0, withinPercentage(0.001));
    assertThat(geneY.getDegradationRate()).isCloseTo(0, withinPercentage(0.001));
  }
}

