import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;

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

  @Test
  public void update_updatesNameAndCountry() {
    Band testBand = new Band("Judass Priest", "USA");
    testBand.save();
    testBand.setName("Judas Priest");
    testBand.setCountry("UK");
    testBand.update();
    Band savedBand = Band.find(testBand.getId());
    assertEquals(savedBand.getName(), "Judas Priest");
    assertEquals(savedBand.getCountry(), "UK");
  }

  @Test
  public void delete_deletesBandFromDatabase() {
    Band testBand = new Band("Mötorhead", "UK");
    testBand.save();
    Band savedBand = Band.find(testBand.getId());
    savedBand.delete();
    assertEquals(Band.all().size(), 0);
  }


  @Test
  public void getVenues_returnsAllVenues_List() {
    Venue myVenue = new Venue("Mollys", "London", "UK");
    myVenue.save();

    Band myBand = new Band("Metallica", "US");
    myBand.save();
    myBand.addVenue(myVenue);

    List savedVenues = myBand.getVenues();
    assertEquals(savedVenues.size(), 1);
  }
}
