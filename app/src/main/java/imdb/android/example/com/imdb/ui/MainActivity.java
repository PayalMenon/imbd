package imdb.android.example.com.imdb.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import imdb.android.example.com.imdb.Application;
import imdb.android.example.com.imdb.R;
import imdb.android.example.com.imdb.Service.MovieService;
import imdb.android.example.com.imdb.util.Constants;

public class MainActivity extends AppCompatActivity {

    @Inject
    MovieService service;

    private String queryString;
    private FragmentListener fragmentListener;
    private Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setTitle(getResources().getString(R.string.now_playing));

        ((Application) getApplication()).getNetworkComponent().inject(this);

        if (savedInstanceState == null) {
            queryString = Constants.DEFAULT_QUERY;
        }

        addListFragment();
    }

    private void addListFragment() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ListFragment fragment = (ListFragment) manager.findFragmentByTag(Constants.LIST_FRAGMENT);

        if (fragment == null) {

            fragment = new ListFragment();
            transaction.add(R.id.fragment_container, fragment, Constants.LIST_FRAGMENT);
            transaction.commit();
        }
    }

    @Override
    protected void onStart() {

        super.onStart();
        if (Constants.DEFAULT_QUERY.equals(queryString)) {
            this.fragmentListener.updateFragmentNowPlayingList();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()) {

            FragmentManager manager = getSupportFragmentManager();

            ListFragment fragment = (ListFragment) manager.findFragmentByTag(Constants.LIST_FRAGMENT);
            manager.beginTransaction().remove(fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getResources().getString(R.string.search_text));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                MainActivity.this.fragmentListener.updateFragmentSearchList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_up_coming: {
                getSupportActionBar().setTitle(getResources().getString(R.string.up_coming));
                this.fragmentListener.updateFragmentUpComingList();
                return true;
            }

            case R.id.action_now_playing: {
                getSupportActionBar().setTitle(getResources().getString(R.string.now_playing));
                this.fragmentListener.updateFragmentNowPlayingList();
                return true;
            }

            case R.id.action_popular: {
                getSupportActionBar().setTitle(getResources().getString(R.string.popular));
                this.fragmentListener.updateFragmentPopularList();
                return true;
            }

            case R.id.action_top_rated: {
                getSupportActionBar().setTitle(getResources().getString(R.string.top_rated));
                this.fragmentListener.updateFragmentTopRatedList();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFragmentListener(FragmentListener listener) {

        this.fragmentListener = listener;
    }

    public interface FragmentListener {

        void updateFragmentNowPlayingList();
        void updateFragmentUpComingList();
        void updateFragmentPopularList();
        void updateFragmentTopRatedList();
        void updateFragmentSearchList(String query);
        void onItemClicked(int position);
    }
}
