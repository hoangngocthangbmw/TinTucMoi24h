package com.watermelon.thanghn.bottomnavigation.screen.detailcategories.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.detailcategories.CategoriItem;

import java.util.List;

/**
 * Created by thang on 3/12/2018.
 */

public class SpinerAdapter extends BaseAdapter {
    Context context;
    int myLayout;
    List<CategoriItem> arraylist;

    public SpinerAdapter(Context context,int myLayout,List<CategoriItem> arraylist){
        this.context=context;
        this.myLayout=myLayout;
        this.arraylist=arraylist;
    }
    @Override
    public int getCount() {
        if (arraylist.size()>0){
            return arraylist.size();
        }
       return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(myLayout,null);
        TextView txtName=view.findViewById(R.id.txtNamee);
        txtName.setText(arraylist.get(i).getName());
        return view;
    }
}
