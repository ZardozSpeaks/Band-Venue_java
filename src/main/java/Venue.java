import org.sql2o.*;
import java.util.List;

public class Venue {

  private int id;
  private String venue_name;
  private String city;
  private String state;

  public Venue(String name, String city, String state) {
    this.venue_name = name;
    this.city = city;
    this.state = state;
  }

  @Override
  public boolean equals(Object otherVenue){
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) &&
      this.getCity().equals(newVenue.getCity()) &&
      this.getState().equals(newVenue.getState());
    }
  }

  //GETTER METHODS//

  public int getId() {
    return id;
  }

  public String getName() {
    return venue_name;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  //SETTER FUNCTIONS//

  public void setName(String newName) {
    venue_name = newName;
  }

  public void setCity(String newCity) {
    city = newCity;
  }

  public void setState(String newState) {
    state = newState;
  }

  //CREATE//

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO venues (venue_name, city, state) VALUES (:name, :city, :state)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.venue_name)
      .addParameter("city", this.city)
      .addParameter("state", this.state)
      .executeUpdate()
      .getKey();
    }
  }

  //READ//

  public static List<Venue> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues";
      return con.createQuery(sql)
      .executeAndFetch(Venue.class);
    }
  }

  public static Venue find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues WHERE id=:id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Venue.class);
    }
  }

  public List<Band> getBands() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT bands.* FROM venues JOIN bands_venues ON (bands_venues.venue_id = venues.id) JOIN bands ON (bands.id = bands_venues.band_id) WHERE venue_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Band.class);
      }
  }

  //UPDATE//

  public void addBand(Band band) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id);";
      con.createQuery(sql)
        .addParameter("band_id", band.getId())
        .addParameter("venue_id", this.getId())
        .executeUpdate();
    }
  }

}
