package imdb.android.example.com.imdb.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import imdb.android.example.com.imdb.R;
import imdb.android.example.com.imdb.pojo.MovieResponse;
import imdb.android.example.com.imdb.util.Constants;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context context;
    private List<MovieResponse> moviesList;
    private MainActivity.FragmentListener listener;

    public ListAdapter(Context context, List<MovieResponse> moviesList, MainActivity.FragmentListener listener) {
        this.context = context;
        this.moviesList = moviesList;
        this.listener = listener;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View frameView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ListAdapter.ListViewHolder(frameView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, final int position) {
        MovieResponse movieResponse = this.moviesList.get(position);

        if (movieResponse != null) {

            String imageUrl = Constants.IMAGE_URL + movieResponse.getPosterUrl();
            Picasso.with(context).load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.posterImageView);


            holder.titleView.setText(movieResponse.getTitle());
            holder.releaseDateView.setText(context.getResources().getString((R.string.release_date), movieResponse.getReleaseDate()));
            holder.votesView.setText(movieResponse.getVoteAverage().toString());
            holder.votersView.setText(context.getResources().getString((R.string.voters), movieResponse.getVotes()));
            holder.ratingView.setRating(movieResponse.getPopularity().floatValue()/2);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{

        ImageView posterImageView;
        TextView titleView;
        TextView releaseDateView;
        RatingBar ratingView;
        TextView votesView;
        TextView votersView;
        CardView cardView;

        public ListViewHolder(View itemView) {
            super(itemView);

            posterImageView = itemView.findViewById(R.id.itemImage);
            titleView = itemView.findViewById(R.id.itemTitle);
            releaseDateView = itemView.findViewById(R.id.itemReleaseDate);
            ratingView = itemView.findViewById(R.id.itemRating);
            votesView = itemView.findViewById(R.id.itemVotes);
            votersView = itemView.findViewById(R.id.itemVoters);
            cardView = itemView.findViewById(R.id.item_cardView);
        }
    }

    public void updateDateSet(List<MovieResponse> list) {
        this.moviesList = list;
    }
}
