package com.watermelon.thanghn.bottomnavigation.screen.newsoffline;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.watermelon.thanghn.bottomnavigation.MainActivity;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.app.database.NewsOff;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;
import com.watermelon.thanghn.bottomnavigation.screen.detailnewoffline.DetailNewsOfflineActivity;
import com.watermelon.thanghn.bottomnavigation.screen.newsoffline.adapter.ListNewOfflineAdapter;
import com.watermelon.thanghn.bottomnavigation.screen.newsonline.adapter.ListNewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListNewsOfflineActivity extends AppCompatActivity implements View.OnClickListener, IListNewsOfflineView {
    private String TAG = "ListNewsOfflineActivity";
    private ImageView buttonBack;
    private ListView listView;
    private RecyclerView recyclerView;
    private ListNewOfflineAdapter adapter;
    private List<NewsOff> listNewsOffline;
    private ListNewsOfflinePresenter listNewsOfflinePresenter;//Presenter
    private TextView txtNone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list_news_offline);
        initView();
        initData();
        initAction();
    }

    private void initAction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListNewsOfflineActivity.this, DetailNewsOfflineActivity.class);
                if (listNewsOffline.size() > 0) {
                    intent.putExtra("content", listNewsOffline.get(i).getContent());
                }
                Log.d("content", listNewsOffline.get(i).getContent());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogDelete(i);
                return false;
            }
        });

    }

    private void showDialogDelete(final int position) {
        AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle("Thông báo");
        al.setMessage("Bạn có muốn xóa bài báo này không?");
        al.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        al.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listNewsOfflinePresenter.onHandleDeleteNew(listNewsOffline.get(position));
                adapter.notifyDataSetChanged();
            }
        });
        al.create();
        al.show();
    }

    private void initData() {
        listNewsOfflinePresenter.onHandleGetNewsInDatabase();
    }

    private void initView() {
        listNewsOfflinePresenter = new ListNewsOfflinePresenter(this);
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listView = (ListView) findViewById(R.id.listView);
        buttonBack = (ImageView) findViewById(R.id.buttonBack);
        txtNone = (TextView) findViewById(R.id.txtNone);
        buttonBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
//                Intent intent = new Intent(ListNewsOfflineActivity.this, MainActivity.class);
//                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onHandleGetNewsInDatabaseSuccess(List<NewsOff> listNews) {
        if (listNews.size() > 0) {
            txtNone.setVisibility(View.GONE);
            listNewsOffline = new ArrayList<NewsOff>();
            listNewsOffline = listNews;
            adapter = new ListNewOfflineAdapter(ListNewsOfflineActivity.this, R.layout.item_list_new, listNews);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
//            recyclerView.setHasFixedSize(true);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(ListNewsOfflineActivity.this, LinearLayoutManager.VERTICAL, false);
//            recyclerView.setLayoutManager(layoutManager);
//            adapter=new ListNewOfflineAdapter(listNews,ListNewsOfflineActivity.this);
//            recyclerView.setAdapter(adapter);
        }
        txtNone.setText("Không có mục nào được lưu trữ");

    }

    @Override
    public void onHandleGetNewsInDatabaseFailed(String erroMsg) {
        Log.d(TAG, erroMsg);
    }

    @Override
    public void onHandleDeleteNewSuccess(String msg) {
        Toast.makeText(ListNewsOfflineActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHandleDeleteNewsFailed(String erroMsg) {

    }
}
