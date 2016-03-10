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
      Band band = Band.find(selectedBand);

      model.put ("band", band);
      model.put("venues", Venue.all());
      model.put("selectedBand", selectedBand);
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
      String newVenueState = request.queryParams("venue-state");
      Venue newVenue = new Venue(newVenueName, newVenueCity, newVenueState);
      newVenue.save();
      response.redirect("/");
      return null;
    });

    get("/band/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      model.put("band", band);
      model.put("playedVenues", band.getVenues());
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/band/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      String newBandName = request.queryParams("band-name");
      String newBandCountry = request.queryParams("band-country");
      String bio = request.queryParams("bio");
      String[] venueList = request.queryParamsValues("venue-list");
      if (venueList != null) {
        for(String venue : venueList) {
          band.addVenue(Venue.find(Integer.parseInt(venue)));
        }
      }
      band.setBio(bio);
      band.setName(newBandName);
      band.setCountry(newBandCountry);
      band.updateBio();
      band.update();
      response.redirect("/");
      return null;
    });

    post("/band/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      band.delete();
      response.redirect("/");
      return null;
    });

    get("/venue/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(id);
      model.put("venue", venue);
      model.put("playedBands", venue.getBands());
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
