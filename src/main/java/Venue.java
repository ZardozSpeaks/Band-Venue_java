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

  //READ//

  public static List<Venue> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues";
      return con.createQuery(sql)
      .executeAndFetch(Venue.class);
    }
  }
}
