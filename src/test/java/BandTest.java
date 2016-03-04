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
    Band firstBand = new Band("Mötorhead");
    Band secondBand = new Band("Mötorhead");
    assertTrue(secondBand.equals(firstBand));
  }
}
