package imdb.android.example.com.imdb.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @Expose
    private String title;

    @SerializedName("vote_average")
    @Expose
    Double voteAverage;

    @SerializedName("vote_count")
    @Expose
    int votes;

    @Expose
    String overview;

    @SerializedName("poster_path")
    @Expose
    String posterUrl;

    @SerializedName("backdrop_path")
    @Expose
    String backgroundUrl;

    @SerializedName("release_date")
    @Expose
    String releaseDate;

    @Expose
    Double popularity;

    @SerializedName("id")
    @Expose
    Long movieId;

    public Double getPopularity() {
        return popularity;
    }

    public int getVotes() {
        return votes;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
