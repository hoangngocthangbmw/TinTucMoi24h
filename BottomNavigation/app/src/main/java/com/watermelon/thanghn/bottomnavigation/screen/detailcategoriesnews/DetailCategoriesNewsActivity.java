package com.watermelon.thanghn.bottomnavigation.screen.detailcategoriesnews;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.screen.categories.CategoriesActivity;
import com.watermelon.thanghn.bottomnavigation.screen.detailcategories.DetailCategoriesActivity;

import static com.watermelon.thanghn.bottomnavigation.screen.menu.MenuFragment.EMAIL;
import static com.watermelon.thanghn.bottomnavigation.screen.menu.MenuFragment.SEND;

public class DetailCategoriesNewsActivity extends AppCompatActivity
        implements View.OnClickListener, IDetailCategoriesNewsView, PopupMenu.OnMenuItemClickListener {
    private ImageView btnBack, btnMore;
    private WebView webView;
    private DetailCategoriesNewsPresenter detailCategoriesNewsPresenter;//Presenter
    private String link, pubdate, image, title, contentNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_categories_news);
        initView();
        initData();
        initAction();
    }

    private void initAction() {

    }

    private void initData() {
        Intent intent = getIntent();
        link = intent.getStringExtra("linknewcategori");
        pubdate = intent.getStringExtra("pubdatenewcategori");
        image = intent.getStringExtra("imagenewcategori");
        title = intent.getStringExtra("titlenewcategoti");
        if (!TextUtils.isEmpty(link)) {
            detailCategoriesNewsPresenter.onHandleGetHtmlByLink(link);
        }

    }

    private void initView() {
        detailCategoriesNewsPresenter = new DetailCategoriesNewsPresenter(this);
        btnMore = (ImageView) findViewById(R.id.buttonMore);
        btnBack = (ImageView) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);
        btnMore.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.webView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
                Intent intent = new Intent(DetailCategoriesNewsActivity.this, DetailCategoriesActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonMore:
                showPopupMenu();
                break;
        }
    }

    private void showPopupMenu() {
        PopupMenu popup = new PopupMenu(DetailCategoriesNewsActivity.this, btnMore);
        popup.getMenuInflater().inflate(R.menu.popup_menu_favorite, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(this);
    }

    @Override
    public void onHandleGetHtmlByLinkSuccess(String content) {
        if (!TextUtils.isEmpty(content)) {
            webView.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}"
                    + " p {font-family:\"Tangerine\", \"Sans-serif\", \"Serif\" font-size: 48px} </style>"
                    + content, "text/html", "UTF-8", "");

            contentNew = "<style>img{display: inline;height: auto;max-width: 100%;}"
                    + " p {font-family:\"Tangerine\", \"Sans-serif\", \"Serif\" font-size: 48px} </style>"
                    + content;
//            txtNone.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHandleGetHtmlByLinkFailed(String erroMsg) {

    }

    @Override
    public void onHandleSaveNewsInDeviceSuccess(String Msg) {

    }

    @Override
    public void onHandleSaveNewsInDeviceFailed(String erroMsg) {

    }

    @Override
    public void onHandleSaveImageInStorageSuccess(String Msg) {

    }

    @Override
    public void onHandleSaveImageInStorageFailed(String erroMsg) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_save:
                detailCategoriesNewsPresenter.onHandleSaveNewsInDevice(link, title, pubdate, image, contentNew);
                detailCategoriesNewsPresenter.onHandleSaveImageInStorage(image, title + ".jpg");
                Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(EMAIL + ""));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, title);
                emailIntent.putExtra(Intent.EXTRA_TEXT, link);
                startActivity(Intent.createChooser(emailIntent, SEND));
                break;
        }
        return true;
    }
}
