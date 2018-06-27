package com.watermelon.thanghn.bottomnavigation.screen.personal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.watermelon.thanghn.bottomnavigation.R;


import java.util.List;

/**
 * Created by thang on 4/28/2018.
 */

public class NewspaperRecylerAdapter extends RecyclerView.Adapter<NewspaperRecylerAdapter.ViewHolder> {

    private List list;
    private Context context;
    int []image={R.drawable.haituh,R.drawable.vnexpress,
            R.drawable.vietnamnet,R.drawable.tuoitre,
            R.drawable.laodong, R.drawable.thanhnien,
            R.drawable.tienphong,R.drawable.nhandan};

    private ClickRecylerCallback callback;
    public interface ClickRecylerCallback {
        void onItemClick(int position);
    }

    public NewspaperRecylerAdapter(Context context, List itemList,int []imgs,ClickRecylerCallback clickRecylerCallback) {
        this.list = itemList;
        this.context = context;
        this.image=imgs;
        this.callback=clickRecylerCallback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View container;
        private TextView txtName;
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtNameNews);
            img=itemView.findViewById(R.id.imgNews);
            container = itemView.findViewById(R.id.card_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemClick(getAdapterPosition());
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_news, null, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Newspaper newspaper= (Newspaper) list.get(position);
        holder.txtName.setText(newspaper.getName());
        holder.img.setImageResource(image[position]);
//        holder.container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(context, ((Newspaper) list.get(position)).getLink(), Toast.LENGTH_SHORT).show();
////                    callback.onItemClick();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

}