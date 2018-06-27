package com.watermelon.thanghn.bottomnavigation.screen.detailnewsonline;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.watermelon.thanghn.bottomnavigation.MainActivity;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.screen.comment.CommentActivity;

import java.util.List;

import static com.watermelon.thanghn.bottomnavigation.screen.menu.MenuFragment.EMAIL;
import static com.watermelon.thanghn.bottomnavigation.screen.menu.MenuFragment.SEND;

/**
 * Created by thang on 1/29/2018.
 */

public class DetailNewsActivity extends AppCompatActivity implements View.OnClickListener, IDetailNewsView {
    private static String TAG = "DetailNewsActivity";
    private static final int PERMISSION_REQUEST_CODE = 1000;
    private WebView webView;
    private ImageView btnBack, btnCmt, btnSave, btnShare;
    private TextView edtCmt;
    //    private TextView txtNone;
    private static String linkNew, titleNew, pubDateNew, imgNew, contentNew;
    static DetailNewsActivity fragment;
    private DetailNewsPresenter detailNewsPresenter;//Presenter
    private String link = null;

    public static DetailNewsActivity newInstance(String link, String title, String pubDate, String imgNew) {
        fragment = new DetailNewsActivity();
        fragment.linkNew = link;
        fragment.titleNew = title;
        fragment.pubDateNew = pubDate;
        fragment.imgNew = imgNew;
        return fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_news);
        initView();
        initData();
        initAction();
    }

    private void initAction() {
    }

    private void initData() {
        detailNewsPresenter.onHandleGetHtmlDetailNews(linkNew);
    }

    private void initView() {
        detailNewsPresenter = new DetailNewsPresenter(this);
//        txtNone = (TextView) findViewById(R.id.txtNone);
        webView = (WebView) findViewById(R.id.webView);
        edtCmt = (TextView) findViewById(R.id.editTextCmt);
        btnBack = (ImageView) findViewById(R.id.buttonBack);
        btnCmt = (ImageView) findViewById(R.id.buttonCmt);
        btnSave = (ImageView) findViewById(R.id.buttonSave);
        btnShare = (ImageView) findViewById(R.id.buttonShare);
        btnBack.setOnClickListener(this);
        btnCmt.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
                finish();
                break;
            case R.id.buttonCmt:
                Intent intent1 = new Intent(DetailNewsActivity.this, CommentActivity.class);
                startActivity(intent1);
                break;
            case R.id.buttonSave:
                if (ActivityCompat.checkSelfPermission(DetailNewsActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(DetailNewsActivity.this, "Cấp quyền truy cập bộ nhớ", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSION_REQUEST_CODE);
                    return;
                } else {
                    showDialogSave();
                }
                break;
            case R.id.buttonShare:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(EMAIL + ""));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, titleNew);
                emailIntent.putExtra(Intent.EXTRA_TEXT, linkNew);
                startActivity(Intent.createChooser(emailIntent, SEND));
                break;
        }
    }

    private void showDialogSave() {
        AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle("Thông báo");
        al.setMessage("Bạn có muốn lưu bài báo này không?");
        al.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        al.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                detailNewsPresenter.onHandleSaveNewsInDevice(linkNew, titleNew, pubDateNew, imgNew, contentNew);
                detailNewsPresenter.onHandleSaveImageInStorage(imgNew, titleNew + ".jpg");
                dialogInterface.dismiss();
            }
        });
        al.create();
        al.show();
    }

    @Override
    public void onHandleGetHtmlDetailNewsSuccess(String htmlContext) {
        if (!TextUtils.isEmpty(htmlContext)) {
            webView.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}"
                    + " p {font-family:\"Tangerine\", \"Sans-serif\", \"Serif\" font-size: 48px} </style>"
                    + htmlContext, "text/html", "UTF-8", "");

            contentNew = "<style>img{display: inline;height: auto;max-width: 100%;}"
                    + " p {font-family:\"Tangerine\", \"Sans-serif\", \"Serif\" font-size: 48px} </style>"
                    + htmlContext;
//            txtNone.setVisibility(View.GONE);
        }

    }

    @Override
    public void onHandleGetHtmlDetailNewsFailed(String erroMsg) {
        Log.d(TAG, erroMsg);
    }

    @Override
    public void onHandleSaveNewsInDeviceSuccess(String Msg) {
        Toast.makeText(DetailNewsActivity.this, Msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHandleSaveNewsInDeviceFailed(String erroMsg) {
        Toast.makeText(DetailNewsActivity.this, erroMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHandleSaveImageInStorageSuccess(String Msg) {
        Log.d(TAG, Msg);
    }

    @Override
    public void onHandleSaveImageInStorageFailed(String erroMsg) {
        Log.d(TAG, erroMsg);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Permission Granted");
                else
//                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Permission Denied");
            }
            break;
        }
    }
}
