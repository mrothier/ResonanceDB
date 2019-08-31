package com.resonance.main;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.resonance.api.constants.Constants;
import com.resonance.model.simple.Artist;
import com.resonance.model.simple.SimilarTrackList;
import com.resonance.model.simple.Track;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class Main {
  public static void main(String[] args) {
    Gson gson = new Gson();
    Map<String, String> parameters = new HashMap<>();

    Vertx vertx = Vertx.vertx();
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    router
        .get("/")
        .handler(
            ctx -> {
              ctx.response()
                  .end(
                      "<html><h1>Welcome to the Simple Last.FM API Query tool.</h1>"
                          + " <br><h2>Structure your queries as follows.</h2><br>"
                          + "<i>Italic text denotes user input</i><br>"
                          + "<br>for track info: /<b>track</b>/<i>artist</i>/<i>track</i>"
                          + "<br>for similar artists /<b>artist</b>/<i>artist</i>"
                          + "<br>for similar tracks /<b>similarTrack</b>/<i>artist</i>/<i>track</i>"
                          + "<br><br>Enjoy!</html>");
            });
    router
        .get("/track/:artistParam/:songParam")
        .handler(
            ctx -> {
              String artistParam = ctx.request().getParam("artistParam");
              String songParameter = ctx.request().getParam("songParam");
              parameters.put(Constants.ARTIST_ID, "1");
              parameters.put(Constants.ARTIST, artistParam);
              parameters.put(Constants.TRACK, songParameter);
              parameters.put(Constants.TRACK_ID, "223");
              TrackCreator trackCreator = new TrackCreator();
              Track track = trackCreator.createTrack(parameters);
              ctx.response().putHeader("content-type", "application/json").end(gson.toJson(track));
            });
    router
        .get("/artist/:artistParam")
        .handler(
            ctx -> {
              String parameter = ctx.request().getParam("artistParam");
              parameters.put(Constants.ARTIST_ID, "1");
              parameters.put(Constants.ARTIST, parameter);
              ArtistCreator artistCreator = new ArtistCreator();
              Artist artist = artistCreator.createArtist(parameters);
              ctx.response().putHeader("content-type", "application/json").end(gson.toJson(artist));
            });
    router
        .get("/similarTrack/:artistParam/:trackParam")
        .handler(
            ctx -> {
              String artistParameter = ctx.request().getParam("artistParam");
              String trackParameter = ctx.request().getParam("trackParam");
              parameters.put(Constants.ARTIST, artistParameter);
              parameters.put(Constants.TRACK, trackParameter);
              SimilarTrackListCreator similarTrackCreator = new SimilarTrackListCreator();
              SimilarTrackList similarTrack = similarTrackCreator.createSimilarTrack(parameters);
              ctx.response()
                  .putHeader("content-type", "application/json")
                  .end(gson.toJson(similarTrack));
            });

    server.requestHandler(router).listen(8081);
  }
}
