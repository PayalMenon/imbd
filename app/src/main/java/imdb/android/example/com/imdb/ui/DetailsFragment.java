package imdb.android.example.com.imdb.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import imdb.android.example.com.imdb.Application;
import imdb.android.example.com.imdb.R;
import imdb.android.example.com.imdb.Service.MovieService;
import imdb.android.example.com.imdb.pojo.CastDetails;
import imdb.android.example.com.imdb.pojo.CastList;
import imdb.android.example.com.imdb.pojo.Genre;
import imdb.android.example.com.imdb.pojo.MovieDetails;
import imdb.android.example.com.imdb.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {

    @Inject
    MovieService service;

    private TextView titleView;
    private TextView durationView;
    private TextView genreView;
    private TextView releasedView;
    private ImageView posterView;
    private TextView taglineView;
    private TextView descriptionView;
    private TextView votesView;
    private TextView votersView;
    private RecyclerView castListView;
    private CastListAdapter adapter;
    private List<CastDetails> castList = new ArrayList<>();

    public static DetailsFragment newInstance(Long id) {

        Bundle args = new Bundle();
        args.putLong("ID", id);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_view, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        ((Application) getActivity().getApplication()).getNetworkComponent().inject(this);

        titleView = getActivity().findViewById(R.id.details_title);
        durationView = getActivity().findViewById(R.id.details_duration);
        genreView = getActivity().findViewById(R.id.details_genre);
        releasedView = getActivity().findViewById(R.id.details_releaseDate);
        posterView = getActivity().findViewById(R.id.details_posterImage);
        taglineView = getActivity().findViewById(R.id.details_tagline);
        descriptionView = getActivity().findViewById(R.id.details_description);
        votesView = getActivity().findViewById(R.id.details_votes);
        votersView = getActivity().findViewById(R.id.details_voters);

        castListView = getActivity().findViewById(R.id.details_castList);
        castListView.setNestedScrollingEnabled(false);
        adapter = new CastListAdapter(getActivity(), castList);
        castListView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        castListView.setLayoutManager(manager);

        ((MainActivity)getActivity()).getSupportActionBar().hide();

        getMovie();
    }

    private void getMovie() {
        Long id = getArguments().getLong("ID", 0L);
        Call<MovieDetails> call = service.getMovieDetails(id);
        getMovieDetails(call);
    }

    private void getMovieDetails(Call<MovieDetails> call) {

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if(response.isSuccessful()) {
                    MovieDetails details = response.body();
                    titleView.setText(details.getTitle());
                    int duration = details.getDuration();
                    String length = duration + "m";
                    if (duration > 60) {
                        length = duration/60 + "h" + duration%60 + "m";
                    }
                    durationView.setText(length);
                    releasedView.setText(details.getReleased());
                    taglineView.setText(details.getTagline());
                    descriptionView.setText(details.getOverview());
                    votesView.setText(details.getRating().toString());

                    votersView.setText(getActivity().getResources().getString((R.string.voters),
                            details.getVoters()));

                    List<Genre> genre = details.getGenres();
                    if(genre != null && genre.size() > 0) {
                        StringBuilder genreList = new StringBuilder();
                        for (Genre genre1 : genre) {
                            genreList.append(genre1.getName() + ",");
                        }
                        genreView.setText(genreList);
                    }

                    String imageUrl = Constants.IMAGE_DETAILS_URL + details.getPosterUrl();
                    Picasso.with(getActivity()).load(imageUrl)
                            .placeholder(R.drawable.placeholder)
                            .into(posterView);

                    getCastList();
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });
    }

    private void getCastList() {
        Long id = getArguments().getLong("ID", 0L);
        Call<CastList> call = service.getMovieCastList(id);
        getMovieCaseList(call);
    }

    private void getMovieCaseList(Call<CastList> call) {

        call.enqueue(new Callback<CastList>() {
            @Override
            public void onResponse(Call<CastList> call, Response<CastList> response) {
                if(response.isSuccessful()) {
                    CastList cast = response.body();
                    castList = cast.getCast();
                    adapter.updateDateSet(castList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CastList> call, Throwable t) {
                Toast.makeText(getActivity(), getResources().getString(R.string.cast_fetch_failed), Toast.LENGTH_LONG).show();
            }
        });
    }
}