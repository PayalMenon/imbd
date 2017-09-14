package imdb.android.example.com.imdb.Service;

import imdb.android.example.com.imdb.pojo.CastList;
import imdb.android.example.com.imdb.pojo.MovieDetails;
import imdb.android.example.com.imdb.pojo.MovieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/now_playing?api_key=5d967c7c335764f39b1efbe9c5de9760")
    Call<MovieList> getNowPlaying();

    @GET("movie/upcoming?api_key=5d967c7c335764f39b1efbe9c5de9760")
    Call<MovieList> getUpComing();

    @GET("movie/popular?api_key=5d967c7c335764f39b1efbe9c5de9760")
    Call<MovieList> getPopular();

    @GET("movie/top_rated?api_key=5d967c7c335764f39b1efbe9c5de9760")
    Call<MovieList> getTopRated();

    @GET("search/movie?api_key=5d967c7c335764f39b1efbe9c5de9760")
    Call<MovieList> getMovies(@Query("query") String query);

    @GET("movie/{id}?api_key=5d967c7c335764f39b1efbe9c5de9760")
    Call<MovieDetails> getMovieDetails(@Path("id") Long id);

    @GET("movie/{id}/credits?api_key=5d967c7c335764f39b1efbe9c5de9760")
    Call<CastList> getMovieCastList(@Path("id") Long id);
}
