import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst_equalToZero() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_recognizesBandsWithSameName_true() {
    Venue firstVenue = new Venue("The Mermaid", "Birmingham", "UK");
    Venue secondVenue = new Venue("The Mermaid", "Birmingham", "UK");
    assertTrue(secondVenue.equals(firstVenue));
  }
}
