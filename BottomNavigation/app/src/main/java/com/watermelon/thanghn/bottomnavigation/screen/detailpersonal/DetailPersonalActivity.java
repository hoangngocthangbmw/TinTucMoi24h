package com.watermelon.thanghn.bottomnavigation.screen.detailpersonal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.screen.personal.PersonalFragment;

public class DetailPersonalActivity extends AppCompatActivity {
    private WebView webView;
    private ImageView buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_personal);
        initView();
        initData();
        initAction();
    }

    private void initAction() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        if (!PersonalFragment.linkNewspaper.equals("")){
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(PersonalFragment.linkNewspaper);
        }
    }

    private void initView() {
        buttonBack=(ImageView)findViewById(R.id.buttonBack);
        webView=(WebView)findViewById(R.id.webView);
    }
}
