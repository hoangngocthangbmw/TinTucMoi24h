package com.watermelon.thanghn.bottomnavigation.screen.videos;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.videos.Video;
import com.watermelon.thanghn.bottomnavigation.screen.detailvideos.DetailVideosActivity;
import com.watermelon.thanghn.bottomnavigation.screen.videos.adapter.ListVideosAdapter;

import java.security.acl.LastOwnerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thang on 2/9/2018.
 */

public class ListVideosFragment extends Fragment implements IListVideoView {
    private String TAG = "ListVideosFragment";
    private View view;
    public int postion;
    private ListVideosPresenter listVideosPresenter;
    private String link = "";
    private RecyclerView recyclerView;
    private ProgressBar mProgressBar;

    public static ListVideosFragment newInstance(int postion) {
        ListVideosFragment fragment = new ListVideosFragment();
        fragment.postion = postion;
        Log.d("positon", postion + "");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_videos, container, false);
        initView();
        initData();
        initAction();
        return view;
    }

    private void initData() {
        listVideosPresenter.onHandleGetListVideo(postion);
    }

    private void initAction() {

    }

    private void initView() {
        mProgressBar = view.findViewById(R.id.progress_loadding);
        listVideosPresenter = new ListVideosPresenter(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getContext(),layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void onGetListVideoSuccess(final List<Video> videoList) {
        if (videoList.size() > 0) {
            Log.d("lissss", videoList.size() + "");
            ListVideosAdapter listVideosAdapter = new ListVideosAdapter(videoList, getContext(), new ListVideosAdapter.ClickCallback() {
                @Override
                public void onItemClick(int position) {
//                    Toast.makeText(getContext(),videoList.get(position).getLink(),Toast.LENGTH_LONG).show();
//                    videoList.get(position).getLink();
                    Intent intent = new Intent(getActivity(), DetailVideosActivity.class);
                    intent.putExtra("linkvideos", videoList.get(position).getLink());
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(listVideosAdapter);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onGetListVideoFailed(String erroMsg) {
        Log.d("TAG", erroMsg.toString());
    }

//    @Override
//    public void onItemClick(int position) {
//
//    }
}
