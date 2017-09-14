package imdb.android.example.com.imdb.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import imdb.android.example.com.imdb.R;
import imdb.android.example.com.imdb.pojo.CastDetails;
import imdb.android.example.com.imdb.util.Constants;

public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.CastListHolder> {

    private Context context;
    private List<CastDetails> castList;

    public CastListAdapter(Context context, List<CastDetails> castList) {
        this.castList = castList;
        this.context = context;
    }

    @Override
    public CastListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View frameView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_row_item, parent, false);
        return new CastListAdapter.CastListHolder(frameView);
    }

    @Override
    public void onBindViewHolder(CastListHolder holder, int position) {
        CastDetails details = castList.get(position);
        holder.castName.setText(details.getName());
        String imageUrl = Constants.IMAGE_URL + details.getProfileUrl();
        Picasso.with(context).load(imageUrl).placeholder(R.drawable.placeholder).into(holder.castImage);
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class CastListHolder extends RecyclerView.ViewHolder {

        TextView castName;
        ImageView castImage;

        public CastListHolder(View itemView) {
            super(itemView);

            castName = itemView.findViewById(R.id.castName);
            castImage = itemView.findViewById(R.id.castImage);
        }
    }

    public void updateDateSet(List<CastDetails> list) {
        this.castList = list;
    }
}
