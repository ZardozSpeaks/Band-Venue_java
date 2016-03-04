import org.sql2o.*;
import java.util.List;
import java.util.Date;


public class Band {

  private int id;
  private String name;
  private String country;
  private String bio;

  public Band (String name, String country) {
    this.name = name;
    this.country = country;
  }

  @Override
  public boolean equals(Object otherBand){
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName()) &&
      this.getCountry().equals(newBand.getCountry());
    }
  }

  //GETTER FUNCTIONS//

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCountry() {
    return country;
  }

  public String getBio() {
    return bio;
  }

  //SETTER FUNCTIONS//

  public void setName(String newName) {
    name = newName;
  }

  public void setCountry(String newCountry) {
    country = newCountry;
  }

  public void setBio(String newBio) {
    bio = newBio;
  }

  //CREATE//

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands (name, country) VALUES (:name, :country)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("country", this.country)
      .executeUpdate()
      .getKey();
    }
  }

  //READ//

  public static List<Band> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands";
      return con.createQuery(sql)
      .executeAndFetch(Band.class);
    }
  }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands WHERE id=:id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Band.class);
    }
  }

  //UPDATE//

  public void updateBio() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE bands SET bio = :bio WHERE id=:id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .addParameter("bio", this.bio)
      .executeUpdate();
    }
  }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE bands SET name = :name, country = country WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .addParameter("name", this.name)
      .addParameter("country", this.country)
      .executeUpdate();
    }
  }

  //DELETE//

//   public void delete() {
//     try(Connection con DB.sql2o.open()) {
//       String sql = "DELETE FROM students WHERE id = :id";
//       con.createQuery(sql)
//       .addParameter("id", this.id)
//       .executeUpdate();
//     }
//   }
// }



}
