package com.watermelon.thanghn.bottomnavigation.screen.videos.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.videos.Video;
import com.watermelon.thanghn.bottomnavigation.screen.detailvideos.DetailVideosActivity;

import java.util.List;

/**
 * Created by thang on 2/9/2018.
 */

public class ListVideosAdapter extends RecyclerView.Adapter<ListVideosAdapter.ViewHolder> {
    private List<Video> listVideo;
    private Context context;
    //    public static String linkVideo;
    private ClickCallback callback;

    public interface ClickCallback {
        void onItemClick(int position);
    }

    public ListVideosAdapter(List<Video> listVideo, Context context, ClickCallback clickCallback) {
        this.listVideo = listVideo;
        this.context = context;
        this.callback=clickCallback;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_video, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTitle.setText(listVideo.get(position).getTitle());
        Picasso.with(context).load(listVideo.get(position).getImage()).into(holder.imgVideo);
    }

    @Override
    public int getItemCount() {
        return listVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imgVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            imgVideo = (ImageView) itemView.findViewById(R.id.imgVideo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //  Toast.makeText(App.getAppContext(),listVideo.get(getAdapterPosition()).getLink(),Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(App.getAppContext(), DetailVideosActivity.class);
//                    intent.putExtra("linkvideos", listVideo.get(getAdapterPosition()).getLink());
//                    App.getAppContext().startActivity(intent);
                    callback.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
