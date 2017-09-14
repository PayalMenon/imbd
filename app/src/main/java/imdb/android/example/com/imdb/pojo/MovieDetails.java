package imdb.android.example.com.imdb.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {

    @Expose
    String overview;

    @Expose
    Double popularity;

    @Expose
    Long id;

    @SerializedName("poster_path")
    @Expose
    String posterUrl;

    @SerializedName("release_date")
    @Expose
    String released;

    @SerializedName("runtime")
    @Expose
    int duration;

    @Expose
    String status;

    @Expose
    String tagline;

    @Expose
    String title;

    @SerializedName("vote_average")
    @Expose
    Double rating;

    @SerializedName("vote_count")
    @Expose
    int voters;

    List<Genre> genres;

    public String getTitle() {
        return title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Double getRating() {
        return rating;
    }

    public int getDuration() {
        return duration;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public Long getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getReleased() {
        return released;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public int getVoters() {
        return voters;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVoters(int voters) {
        this.voters = voters;
    }
}
