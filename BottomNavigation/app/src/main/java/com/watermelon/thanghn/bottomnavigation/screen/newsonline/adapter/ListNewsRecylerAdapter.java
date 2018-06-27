package com.watermelon.thanghn.bottomnavigation.screen.newsonline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by thang on 4/15/2018.
 */

public class ListNewsRecylerAdapter extends RecyclerView.Adapter<ListNewsRecylerAdapter.ViewHolder> {
    private List<News> mNews = new ArrayList<>();
    private Context myContext;

    private ClickRecylerCallback callback;

    public interface ClickRecylerCallback {
        void onItemClick(int position);
    }

    public ListNewsRecylerAdapter(Context context, List<News> news, ClickRecylerCallback clickRecylerCallback) {
        mNews = news;
        myContext=context;
        this.callback=clickRecylerCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("thang111","abc");
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_list_new_with_reycler, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("thang111",position+"");
        News news=mNews.get(position);
        holder.txtTitle.setText(news.getTitle());
        holder.txtDate.setText(news.getPubdate());
        if (mNews.get(position).getImg()!=null){
//            Picasso.with(myContext).load(news.getImg()).into(holder.img);
            Glide.with(myContext)
                    .load(news.getImg())
                    .into(holder.img);
        }
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public TextView txtDate;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle=(TextView)itemView.findViewById(R.id.Title);
            txtDate=(TextView)itemView.findViewById(R.id.date);
            img=(ImageView)itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return false;
                }
            });

        }
    }
}
