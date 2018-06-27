package com.watermelon.thanghn.bottomnavigation.screen.categories;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.watermelon.thanghn.bottomnavigation.MainActivity;
import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.screen.detailcategories.DetailCategoriesActivity;

/**
 * Created by thang on 2/22/2018.
 */

public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnBack;
    private ImageView imgSucKhoe, imgThoiSu, imgGiaiTri, imgTheThao, imgKhoaHoc;
    private int fab = 0;
    public static final String trangthai = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_categories);
        initView();
        initData();
        initAction();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (fab != 0) {
            SharedPreferences pre = getSharedPreferences("my_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = pre.edit();
            switch (fab) {
                case 1:
                    editor.putString("trangthai", "imgSucKhoe");
                    editor.commit();
                    break;
                case 2:
                    editor.putString("trangthai", "imgThoiSu");
                    editor.commit();
                    break;
                case 3:
                    editor.putString("trangthai", "imgGiaiTri");
                    editor.commit();
                    break;
                case 4:
                    editor.putString("trangthai", "imgTheThao");
                    editor.commit();
                    break;
                case 5:
                    editor.putString("trangthai", "imgKhoaHoc");
                    editor.commit();
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent1 = new Intent(CategoriesActivity.this, DetailCategoriesActivity.class);
        switch (view.getId()) {
            case R.id.buttonBack:
                Intent intent = new Intent(CategoriesActivity.this, MainActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.imgSucKhoe:
                fab = 1;
                intent1.putExtra("chude", "imgSucKhoe");
                startActivity(intent1);
                break;
            case R.id.imgThoiSu:
                fab = 2;
                intent1.putExtra("chude", "imgThoiSu");
                startActivity(intent1);
                break;
            case R.id.imgGiaiTri:
                fab = 3;
                intent1.putExtra("chude", "imgGiaiTri");
                startActivity(intent1);
                break;
            case R.id.imgTheThao:
                fab = 4;
                intent1.putExtra("chude", "imgTheThao");
                startActivity(intent1);
                break;
            case R.id.imgKhoaHoc:
                fab = 5;
                intent1.putExtra("chude", "imgKhoaHoc");
                startActivity(intent1);
                break;
        }
    }

    private void initAction() {
    }

    private void initData() {
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);

        imgGiaiTri = (ImageView) findViewById(R.id.imgGiaiTri);
        imgSucKhoe = (ImageView) findViewById(R.id.imgSucKhoe);
        imgKhoaHoc = (ImageView) findViewById(R.id.imgKhoaHoc);
        imgTheThao = (ImageView) findViewById(R.id.imgTheThao);
        imgThoiSu = (ImageView) findViewById(R.id.imgThoiSu);
        imgGiaiTri.setOnClickListener(this);
        imgSucKhoe.setOnClickListener(this);
        imgKhoaHoc.setOnClickListener(this);
        imgTheThao.setOnClickListener(this);
        imgThoiSu.setOnClickListener(this);
    }

}
