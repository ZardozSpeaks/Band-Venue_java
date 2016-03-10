import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst_equalToZero() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_recognizesVenuesWithSameName_true() {
    Venue firstVenue = new Venue("The Mermaid", "Birmingham", "UK");
    Venue secondVenue = new Venue("The Mermaid", "Birmingham", "UK");
    assertTrue(secondVenue.equals(firstVenue));
  }

  @Test
  public void save_savesVenueTotheDatabase_true() {
    Venue testVenue = new Venue("Gorilla Gardens", "Seattle", "WA");
    testVenue.save();
    assertTrue(Venue.all().get(0).equals(testVenue));
  }

  @Test
  public void find_returnsTheCorrectVenue_true() {
    Venue testVenue1 = new Venue("Roxy Theater", "Los Angeles", "CA");
    Venue testVenue2 = new Venue("The Rainbow Bar", "Los Angeles", "CA");
    Venue testVenue3 = new Venue("Whisky Go Go", "Los Angeles", "CA");
    testVenue1.save();
    testVenue2.save();
    testVenue3.save();
    Venue savedVenue = Venue.find(testVenue2.getId());
    assertTrue(savedVenue.equals(testVenue2));
  }

  @Test
  public void getBands_returnsAllBands_List() {
    Band myBand = new Band("Metallica", "US");
    myBand.save();

    Venue myVenue = new Venue("Mollys", "London", "UK");
    myVenue.save();
    myVenue.addBand(myBand);

    List savedBands = myVenue.getBands();
    assertEquals(savedBands.size(), 1);
  }
}
