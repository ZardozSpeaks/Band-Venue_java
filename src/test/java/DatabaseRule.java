import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/band_venue_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRestaurantsQuery = "DELETE FROM bands *";
      String deleteCuisineQuery = "DELETE FROM venues *";
      String deleteJoinsQuery = "DELETE FROM bands_venues *";
      con.createQuery(deleteRestaurantsQuery).executeUpdate();
      con.createQuery(deleteCuisineQuery).executeUpdate();
      con.createQuery(deleteJoinsQuery).executeUpdate();
    }
  }
}
