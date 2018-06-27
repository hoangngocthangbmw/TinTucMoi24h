package com.watermelon.thanghn.bottomnavigation.screen.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;

import java.util.List;

/**
 * Created by thang on 5/29/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<News> mListSong;
    private SearchAdapter.OnClickItemReyclerView mOnClickItemReyclerView;
    private Context mContext;

    public SearchAdapter(List<News> listSong, Context context) {
        this.mListSong = listSong;
        mContext = context;
    }

    public void setOnClickItemRecyclerView(SearchAdapter.OnClickItemReyclerView onClickItemReyclerView) {
        mOnClickItemReyclerView = onClickItemReyclerView;

    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.item_list_new, parent, false);
        return new SearchAdapter.ViewHolder(itemview, mOnClickItemReyclerView,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.bindData(mListSong.get(position));
    }

    @Override
    public int getItemCount() {
        return mListSong != null ? mListSong.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private ImageView mAvatarNews;
        private TextView mTitle;
        private TextView mPubData;
        private RelativeLayout mConstraintLayout;
        private SearchAdapter.OnClickItemReyclerView mOnClickItemReyclerView;
        private News mSong;
        private Context mContext;


        public ViewHolder(View itemView, SearchAdapter.OnClickItemReyclerView onClickItemReyclerView,Context context) {
            super(itemView);
            mAvatarNews = itemView.findViewById(R.id.img);
            mTitle = itemView.findViewById(R.id.Title);
            mPubData = itemView.findViewById(R.id.date);
            mConstraintLayout = itemView.findViewById(R.id.constrain_item);
            mOnClickItemReyclerView = onClickItemReyclerView;
            mConstraintLayout.setOnClickListener(this);
            mContext=context;
        }

        public void bindData(News news) {
            if (news != null) {
                mSong = news;
//               Picasso.with(mContext).load(news.getImg()).into(mAvatarNews);
                Glide.with(mContext).load(news.getImg()).into(mAvatarNews);
                mTitle.setText(news.getTitle());
                mPubData.setText(news.getPubdate());
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.constrain_item:
                    if (mOnClickItemReyclerView != null) {
                        mOnClickItemReyclerView.onItemClicked(getAdapterPosition(), mSong);
                    }
                    break;
            }
        }
    }

    interface OnClickItemReyclerView {
        void onItemClicked(int position, News song);
    }
}

