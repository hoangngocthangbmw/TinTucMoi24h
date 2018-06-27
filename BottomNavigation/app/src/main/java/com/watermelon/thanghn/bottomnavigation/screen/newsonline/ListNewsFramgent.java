package com.watermelon.thanghn.bottomnavigation.screen.newsonline;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.watermelon.thanghn.bottomnavigation.helper.animation.DepthPageTransformer;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.screen.detailnewsonline.DetailNewsActivity;
import com.watermelon.thanghn.bottomnavigation.screen.newsonline.adapter.ListNewsAdapter;
import com.watermelon.thanghn.bottomnavigation.screen.newsonline.adapter.ListNewsRecylerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thang on 1/16/2018.
 */

public class ListNewsFramgent extends Fragment implements INewsView {
    private String TAG="ListNewsFramgent";
    private View view;
    public int postion;
    private ListView listView;
    private List<News> listNews = new ArrayList<News>();
//    private ListNewsAdapter listNewsAdapter;
    private ListNewsPresenter listNewsPresenter;//Presenter
    private ProgressDialog progressDialog;
    private TextView txtNoConnect;
    private RecyclerView recyclerView;

    public static ListNewsFramgent newInstance(int postion) {
        ListNewsFramgent fragment = new ListNewsFramgent();
        fragment.postion = postion;
        Log.d("positon", postion + "");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_news, container, false);
        initView();
        initData();
        initAction();
        return view;
    }

    private void initView() {
        recyclerView=(RecyclerView)view.findViewById(R.id.recyler);
        listNewsPresenter = new ListNewsPresenter(this);
//        listView = view.findViewById(R.id.listView);
        txtNoConnect=view.findViewById(R.id.txtNone);
    }

    private void setProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Data is loading ...");
        progressDialog.setIcon(R.drawable.ic_public_black_24dp);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    private void initData() {
        listNewsPresenter.onHandleGetRssApi(postion);
    }

    private void initAction() {
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                DetailNewsActivity.newInstance(listNews.get(i).getLink(),listNews.get(i).getTitle(),listNews.get(i).getPubdate(),listNews.get(i).getImg());
//                Intent intent = new Intent(getActivity(), DetailNewsActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onGetApiRssSuccess(final List<News> listNews) {
        if (listNews.size()>0) {
//            this.listNews = listNews;
//            listNewsAdapter = new ListNewsAdapter(getContext(), R.layout.item_list_new, listNews);
//            listView.setAdapter(listNewsAdapter);
//            listNewsAdapter.notifyDataSetChanged();
            ListNewsRecylerAdapter adapter = new ListNewsRecylerAdapter(getContext(), listNews, new ListNewsRecylerAdapter.ClickRecylerCallback() {
                @Override
                public void onItemClick(int position) {
                DetailNewsActivity.newInstance(listNews.get(position).getLink(),listNews.get(position).getTitle(),listNews.get(position).getPubdate(),listNews.get(position).getImg());
                Intent intent = new Intent(getActivity(), DetailNewsActivity.class);
                startActivity(intent);
                }
            });
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            //xet phan cach
//            DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getContext(),layoutManager.getOrientation());
//            recyclerView.addItemDecoration(dividerItemDecoration);
//            LayoutAnimationController controller=null;

//            LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_fall_down);
            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutAnimation(controller);

            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
            adapter.notifyDataSetChanged();
            txtNoConnect.setVisibility(View.GONE);
        }
    }


    @Override
    public void onGetApiRssFailed(String erroMsg) {
        Log.d(TAG,erroMsg);
        txtNoConnect.setText("Không có kết nối Internet");
    }


}
