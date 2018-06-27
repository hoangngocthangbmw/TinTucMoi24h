package com.watermelon.thanghn.bottomnavigation.screen.newsoffline.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.app.PhotoLoader;
import com.watermelon.thanghn.bottomnavigation.model.app.database.NewsOff;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;
import com.watermelon.thanghn.bottomnavigation.screen.newsonline.adapter.ListNewsAdapter;

import java.io.File;
import java.util.List;

/**
 * Created by thang on 3/1/2018.
 */

//public class ListNewOfflineAdapter extends RecyclerView.Adapter<ListNewOfflineAdapter.ViewHolder> {
//    private List<NewsOff> mListNews;
//    private Context mContext;
//
//    public ListNewOfflineAdapter(List<NewsOff> listNews, Context context) {
//        this.mListNews = listNews;
//        this.mContext = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View itemView = layoutInflater.inflate(R.layout.item_list_new, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.txtTitle.setText(mListNews.get(position).getTittle());
//        holder.txtTitle.setText(mListNews.get(position).getDate());
//        Picasso.with(App.getAppContext()).load(mListNews.get(position).getLink()).into(new PhotoLoader(mListNews.get(position).getTittle(),holder.img));
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mListNews!=null) {
//            return mListNews.size();
//        }
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView txtTitle, txtPubDate;
//        ImageView img;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            txtTitle = (TextView) itemView.findViewById(R.id.Title);
//            txtPubDate = (TextView) itemView.findViewById(R.id.date);
//            img = (ImageView) itemView.findViewById(R.id.img);
//        }
//    }
//}
public class ListNewOfflineAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<NewsOff> arrayNews;

    public ListNewOfflineAdapter(Context context, int layout, List<NewsOff> newList) {
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
//        ImageView imageViewMore;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListNewOfflineAdapter.ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(myLayout, null);
            holder = new ListNewOfflineAdapter.ViewHolder();

            holder.title = (TextView) view.findViewById(R.id.Title);
            holder.pubDate = (TextView) view.findViewById(R.id.date);
            holder.imageView = (ImageView) view.findViewById(R.id.img);
//            holder.imageViewMore=(ImageView)view.findViewById(R.id.image_more);
            view.setTag(holder);

        } else {
            holder = (ListNewOfflineAdapter.ViewHolder) view.getTag();
        }
        holder.title.setText(arrayNews.get(i).getTittle());
        holder.pubDate.setText(arrayNews.get(i).getDate());
//        Glide.with(myContext)
//                .load(arrayNews.get(i).getImage())
//                .into(holder.imageView);
        File file= new File(android.os.Environment.getExternalStorageDirectory(),arrayNews.get(i).getTittle()+".jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        holder.imageView.setImageBitmap(bitmap);

        return view;
    }
}