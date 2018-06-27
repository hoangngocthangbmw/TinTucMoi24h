package com.watermelon.thanghn.bottomnavigation.screen.detailnewoffline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.app.database.NewsOff;
import com.watermelon.thanghn.bottomnavigation.screen.newsoffline.ListNewsOfflineActivity;

import java.util.List;

public class DetailNewsOfflineActivity extends AppCompatActivity implements View.OnClickListener, IDetailNewsOfflineView {
    private ImageView btnBack;
    private WebView webView;
    private DetailNewsOfflinePresenter detailNewsOfflinePresenter;//Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_news_offline);
        initView();
        initData();
        initAction();
    }

    private void initAction() {

    }

    private void initData() {
        detailNewsOfflinePresenter.onHandleGetNewsInDatabase();
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
//        Toast.makeText(DetailNewsOfflineActivity.this, content, Toast.LENGTH_LONG).show();
        webView.loadDataWithBaseURL("", content, "text/html", "UTF-8", "");
    }

    private void initView() {
        detailNewsOfflinePresenter = new DetailNewsOfflinePresenter(this);
        btnBack = (ImageView) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.webView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
//                Intent intent = new Intent(DetailNewsOfflineActivity.this, ListNewsOfflineActivity.class);
//                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onHandleGetNewsInDatabaseSuccess(List<NewsOff> listNews) {
        Log.d("c", listNews.size() + "");
    }

    @Override
    public void onHandleGetNewsInDatabaseFailed(String erroMsg) {

    }
}
