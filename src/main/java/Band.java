import org.sql2o.*;
import java.util.List;
import java.util.Date;


public class Band {

  private int id;
  private String name;

  public Band (String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object otherBand){
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName());
    }
  }

  //GETTER FUNCTIONS//

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  //SETTER FUNCTIONS//

  public void setName(String newName) {
    name = newName;
  }

//   //CREATE//
//
//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO students (first_name, last_name, enrollment_date) VALUES (:first_name, :last_name, :enrollment_date)";
//       this.id = (int) con.createQuery(sql, true)
//       .addParameter("first_name", this.first_name)
//       .addParameter("last_name", this.last_name)
//       .addParameter("enrollment_date", this.enrollment_date)
//       .executeUpdate()
//       .getKey();
//     }
//   }
//
//   //READ//
//
  public static List<Band> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands";
      return con.createQuery(sql)
      .executeAndFetch(Band.class);
    }
  }
//
//   public static Student find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM students WHERE id=:id";
//       return con.createQuery(sql)
//       .addParameter("id", id)
//       .executeAndFetchFirst(Student.class);
//     }
//   }
//
//   //UPDATE//
//
//   public void updateEnrollmentDate() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "UPDATE students SET enrollment_date = :enrollment_date WHERE id=:id";
//       con.createQuery(sql)
//       .addParameter("id", id)
//       .addParameter("enrollment_date", enrollment_date)
//       .executeUpdate();
//     }
//   }
//
//   public void update() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "UPDATE students SET first_name = :first_name, last_name = :last_name WHERE id = :id";
//       con.createQuery(sql)
//       .addParameter("id", this.id)
//       .addParameter("first_name", this.first_name)
//       .addParameter("last_name", this.last_name)
//       .executeUpdate();
//     }
//   }
//
//   //DELETE//
//
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
