import java.util.Map;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());

      int selectedBand = Integer.parseInt(request.queryParams("band-select"));
      List<Venue> venuesList = Band.find(selectedBand).getVenues();
      String bandName = Band.find(selectedBand).getName();

      model.put ("bandName", bandName);
      model.put ("venuesList", venuesList);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/new-band", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/new-band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/new-band", (request, response) -> {
      String newBandName = request.queryParams("band-name");
      String newBandCountry = request.queryParams("band-country");
      Band newBand = new Band(newBandName, newBandCountry);
      newBand.save();
      response.redirect("/");
      return null;
    });

    get("/new-venue", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/new-venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/new-venue", (request, response) -> {
      String newVenueName = request.queryParams("venue-name");
      String newVenueCity = request.queryParams("venue-city");
      String newVenueState = request.queryParams("venue-state")
      Venue newVenue = new Venue(newVenueName, newVenueCity, newVenueState);
      newVenue.save();
      response.redirect("/");
      return null;
    });

  }
}
