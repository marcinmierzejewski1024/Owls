package marcinmierzejewski.com.owls;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.collections.Collection;
import com.googlecode.flickrjandroid.test.TestInterface;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class OwlsAdapter extends RecyclerView.Adapter<OwlsAdapter.CustomViewHolder> {
    private List<Owl> items;
    private Context mContext;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getPosition();

            Owl feedItem = items.get(position);
            Toast.makeText(mContext, feedItem.getName(), Toast.LENGTH_SHORT).show();
        }
    };

    public OwlsAdapter(Context context, List<Owl> items) {
        this.items = items;
        this.mContext = context;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.owl_card, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Owl feedItem = items.get(i);

        //Download image using picasso library
        Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.owl)
                .placeholder(R.drawable.owl)
                .into(customViewHolder.imageView);



        customViewHolder.textView.setText(Html.fromHtml(feedItem.getName()));

        customViewHolder.textView.setOnClickListener(clickListener);
        customViewHolder.imageView.setOnClickListener(clickListener);

        customViewHolder.textView.setTag(customViewHolder);
        customViewHolder.imageView.setTag(customViewHolder);
    }

    @Override
    public int getItemCount() {
        return (null != items ? items.size() : 0);
    }
}