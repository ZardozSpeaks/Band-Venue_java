import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst_equalToZero() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_recognizesBandsWithSameName_true() {
    Band firstBand = new Band("Mötorhead", "UK");
    Band secondBand = new Band("Mötorhead", "UK");
    assertTrue(secondBand.equals(firstBand));
  }

  @Test
  public void save_savesBandTotheDatabase_true() {
    Band testBand = new Band("Iron Maiden", "UK");
    testBand.save();
    assertTrue(Band.all().get(0).equals(testBand));
  }

  @Test
  public void find_returnsTheCorrectBand_true() {
    Band testBand1 = new Band("Iron Maiden", "UK");
    Band testBand2 = new Band("Mötorhead", "UK");
    Band testBand3 = new Band("Judas Priest", "UK");
    testBand1.save();
    testBand2.save();
    testBand3.save();
    Band savedBand = Band.find(testBand2.getId());
    assertTrue(savedBand.equals(testBand2));
  }

  @Test
  public void updateBio_addsABiography() {
    Band testBand = new Band("Iron Maiden", "UK");
    testBand.save();
    testBand.setBio("Originally from Leyton");
    testBand.updateBio();
    Band savedBand = Band.find(testBand.getId());
    assertEquals(savedBand.getBio(), "Originally from Leyton");
  }
}
