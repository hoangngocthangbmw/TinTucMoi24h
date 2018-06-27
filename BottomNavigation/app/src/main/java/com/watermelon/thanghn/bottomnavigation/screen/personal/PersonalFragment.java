package com.watermelon.thanghn.bottomnavigation.screen.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.screen.detailpersonal.DetailPersonalActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thang on 1/28/2018.
 */

public class PersonalFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView1;
    private GridLayoutManager layoutManager2;
    private List<Newspaper>  list;
    public static String linkNewspaper;
    int []image={R.drawable.haituh,R.drawable.vnexpress,
            R.drawable.vietnamnet,R.drawable.tuoitre,
            R.drawable.laodong, R.drawable.thanhnien,
            R.drawable.tienphong,R.drawable.nhandan};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_personal,container,false);
        initView();
        initData();
        initAction();
        return view;
    }

    private void initAction() {
    }

    private void initData() {
    }

    private void initView() {
        recyclerView1=view.findViewById(R.id.recyclerView1);
        list=new ArrayList<Newspaper>();
        list.add(new Newspaper("https://www.24h.com.vn","24h"));
        list.add(new Newspaper("https://vnexpress.net","VnExpress"));
        list.add(new Newspaper("http://vietnamnet.vn","Vietnamnet"));
        list.add(new Newspaper("https://tuoitre.vn","Tuổi Trẻ"));
        list.add(new Newspaper("https://laodong.vn","Lao Động"));
        list.add(new Newspaper("https://thanhnien.vn","Thanh Niên"));
        list.add(new Newspaper("https://www.tienphong.vn","Tiền Phong"));
        list.add(new Newspaper("http://www.nhandan.com.vn","Nhân Dân"));
        recyclerView1.setHasFixedSize(true);
        layoutManager2=new GridLayoutManager(getActivity(),2);
        recyclerView1.setLayoutManager(layoutManager2);
        NewspaperRecylerAdapter adapter=new NewspaperRecylerAdapter(getActivity(), list, image, new NewspaperRecylerAdapter.ClickRecylerCallback() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(getActivity(), list.get(position).getLink(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),DetailPersonalActivity.class);
                linkNewspaper=list.get(position).getLink();
                startActivity(intent);
            }
        });
        recyclerView1.setAdapter(adapter);
    }
}
