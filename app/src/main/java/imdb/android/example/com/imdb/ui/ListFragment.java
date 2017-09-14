package imdb.android.example.com.imdb.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import imdb.android.example.com.imdb.Application;
import imdb.android.example.com.imdb.R;
import imdb.android.example.com.imdb.Service.MovieService;
import imdb.android.example.com.imdb.pojo.MovieList;
import imdb.android.example.com.imdb.pojo.MovieResponse;
import imdb.android.example.com.imdb.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment implements MainActivity.FragmentListener{

    @Inject
    MovieService service;

    private RecyclerView movieListView;
    private ListAdapter adapter;
    private List<MovieResponse> movieList = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        ((Application) getActivity().getApplication()).getNetworkComponent().inject(this);

        ((MainActivity)getActivity()).getSupportActionBar().show();

        movieListView = getActivity().findViewById(R.id.list_view);

        adapter = new ListAdapter(getActivity(), movieList, this);
        movieListView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(getActivity());
        movieListView.setLayoutManager(layoutManager);

        ((MainActivity) getActivity()).setFragmentListener(this);
    }

    public void getMovieList(Call<MovieList> call) {

        call.enqueue(new Callback<MovieList>() {

            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                MovieList moviesInfo = response.body();
                if (response.isSuccessful() && null != moviesInfo) {
                    movieList = moviesInfo.getResults();
                    adapter.updateDateSet(movieList);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.fetch_failed), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(getActivity(), getResources().getString(R.string.fetch_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void updateFragmentNowPlayingList() {
        movieList.clear();
        Call<MovieList> call = service.getNowPlaying();
        getMovieList(call);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateFragmentUpComingList() {
        movieList.clear();
        Call<MovieList> call = service.getUpComing();
        getMovieList(call);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateFragmentPopularList() {
        movieList.clear();
        Call<MovieList> call = service.getPopular();
        getMovieList(call);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateFragmentTopRatedList() {
        movieList.clear();
        Call<MovieList> call = service.getTopRated();
        getMovieList(call);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateFragmentSearchList(String query) {
        movieList.clear();
        Call<MovieList> call = service.getMovies(query);
        getMovieList(call);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(int position) {

        MovieResponse info = movieList.get(position);

        Fragment detailsFragment = DetailsFragment.newInstance(info.getMovieId());
        getFragmentManager().beginTransaction()
                .addToBackStack(Constants.LIST_FRAGMENT)
                .replace(R.id.fragment_container, detailsFragment, Constants.DETAILS_FRAGMENT)
                .commit();
    }
}
