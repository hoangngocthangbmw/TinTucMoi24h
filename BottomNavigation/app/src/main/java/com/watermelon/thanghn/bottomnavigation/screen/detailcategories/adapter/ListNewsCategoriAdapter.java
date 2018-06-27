package com.watermelon.thanghn.bottomnavigation.screen.detailcategories.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;
import com.watermelon.thanghn.bottomnavigation.screen.newsonline.adapter.ListNewsAdapter;

import java.util.List;

/**
 * Created by thang on 3/12/2018.
 */

public class ListNewsCategoriAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<News> arrayNews;

    public ListNewsCategoriAdapter(Context context, int layout, List<News> newList) {
        myContext = context;
        myLayout = layout;
        arrayNews = newList;
    }

    @Override
    public int getCount() {
        return arrayNews.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayNews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {
        TextView title;
        TextView pubDate;
        ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListNewsCategoriAdapter.ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(myLayout, null);
            holder = new ListNewsCategoriAdapter.ViewHolder();

            holder.title = (TextView) view.findViewById(R.id.Title);
            holder.pubDate = (TextView) view.findViewById(R.id.date);
            holder.imageView = (ImageView) view.findViewById(R.id.img);

            view.setTag(holder);
        } else {
            holder = (ListNewsCategoriAdapter.ViewHolder) view.getTag();
        }
        holder.title.setText(arrayNews.get(i).getTitle());
        holder.pubDate.setText(arrayNews.get(i).getPubdate());
        if (arrayNews.get(i).getImg()!=null){
//            Picasso.with(myContext).load(arrayNews.get(i).getImg()).into(holder.imageView);
            Glide.with(myContext)
                    .load(arrayNews.get(i).getImg())
                    .into(holder.imageView);
        }
        return view;
    }
}
