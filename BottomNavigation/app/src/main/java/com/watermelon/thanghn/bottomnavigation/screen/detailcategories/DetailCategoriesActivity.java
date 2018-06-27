package com.watermelon.thanghn.bottomnavigation.screen.detailcategories;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.detailcategories.CategoriItem;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;
import com.watermelon.thanghn.bottomnavigation.screen.categories.CategoriesActivity;
import com.watermelon.thanghn.bottomnavigation.screen.detailcategories.adapter.ListNewsCategoriAdapter;
import com.watermelon.thanghn.bottomnavigation.screen.detailcategories.adapter.SpinerAdapter;
import com.watermelon.thanghn.bottomnavigation.screen.detailcategoriesnews.DetailCategoriesNewsActivity;
import com.watermelon.thanghn.bottomnavigation.screen.detailnewsonline.DetailNewsActivity;
import com.watermelon.thanghn.bottomnavigation.screen.newsonline.adapter.ListNewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailCategoriesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, IDetailCategoriesView {
    private String TAG = "DetailCategoriesActivity";
    private ImageView btnBack;
    private Spinner spinner;
    private ListView listView;
    private ArrayList<CategoriItem> arrayList;
    private DetailCategoriesPresenter detailCategoriesPresenter;//Presenter
    private ListNewsCategoriAdapter listNewsCategoriAdapter;
    private List<News> listNews = new ArrayList<News>();
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_categories);
        initView();
        initData();
        initAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pre = getSharedPreferences("my_data", MODE_PRIVATE);
        String s = pre.getString("trangthai", "");
        if (!TextUtils.isEmpty(s)) {
//            setDataSpinner();
            arrayList = new ArrayList<CategoriItem>();
            SpinerAdapter spinerAdapter;
            switch (s) {
                case "imgSucKhoe":
                    arrayList.add(new CategoriItem("Ung thư", "http://dantri.com.vn/suc-khoe/ung-thu.rss"));
                    arrayList.add(new CategoriItem("Làm đẹp", "http://dantri.com.vn/suc-khoe/lam-dep.rss"));
                    arrayList.add(new CategoriItem("Tư vấn", "http://dantri.com.vn/suc-khoe/tu-van.rss"));
                    arrayList.add(new CategoriItem("Kiến thức giới tính", "http://dantri.com.vn/suc-khoe/kien-thuc-gioi-tinh.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
                case "imgThoiSu":
                    arrayList.add(new CategoriItem("Phóng sự- Ký sự", "http://dantri.com.vn/xa-hoi/phong-su-ky-su.rss"));
                    arrayList.add(new CategoriItem("Môi trường", "http://dantri.com.vn/xa-hoi/moi-truong.rss"));
                    arrayList.add(new CategoriItem("Chính trị", "http://dantri.com.vn/xa-hoi/chinh-tri.rss"));
                    arrayList.add(new CategoriItem("Hồ sơ", "http://dantri.com.vn/xa-hoi/ho-so.rss"));
                    arrayList.add(new CategoriItem("Giao thông", "http://dantri.com.vn/xa-hoi/giao-thong.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
                case "imgGiaiTri":
                    arrayList.add(new CategoriItem("Thời trang", "http://dantri.com.vn/giai-tri/thoi-trang.rss"));
                    arrayList.add(new CategoriItem("Xem - Ăn - Chơi", "http://dantri.com.vn/giai-tri/xem-an-choi.rss"));
                    arrayList.add(new CategoriItem("Sao Việt", "http://dantri.com.vn/giai-tri/sao-viet.rss"));
                    arrayList.add(new CategoriItem("Hollywood", "http://dantri.com.vn/giai-tri/hollywood.rss"));
                    arrayList.add(new CategoriItem("Châu Á", "http://dantri.com.vn/giai-tri/chau-a.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
                case "imgTheThao":
                    arrayList.add(new CategoriItem("Bóng đá Anh", "http://dantri.com.vn/the-thao/bong-da-anh.rss"));
                    arrayList.add(new CategoriItem("Bóng đá TBN", "http://dantri.com.vn/the-thao/bong-da-tbn.rss"));
                    arrayList.add(new CategoriItem("Bóng đá châu Âu", "http://dantri.com.vn/the-thao/bong-da-chau-au.rss"));
                    arrayList.add(new CategoriItem("Bóng đá trong nước", "http://dantri.com.vn/the-thao/bong-da-trong-nuoc.rss"));
                    arrayList.add(new CategoriItem("Thể thao quốc tế", "http://dantri.com.vn/the-thao/the-thao-quoc-te.rss"));
                    arrayList.add(new CategoriItem("Tennis", "http://dantri.com.vn/the-thao/tennis.rss"));
                    arrayList.add(new CategoriItem("Thể thao trong nước", "http://dantri.com.vn/the-thao/the-thao-trong-nuoc.rss"));
                    arrayList.add(new CategoriItem("Cờ vua", "http://dantri.com.vn/the-thao/co-vua.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
                case "imgKhoaHoc":
                    arrayList.add(new CategoriItem("Nghe nhìn", "http://dantri.com.vn/suc-manh-so/nghe-nhin.rss"));
                    arrayList.add(new CategoriItem("Điện tử tiêu dùng", "http://dantri.com.vn/suc-manh-so/dien-may.rss"));
                    arrayList.add(new CategoriItem("Phần mềm - Bảo mật", "http://dantri.com.vn/suc-manh-so/phan-mem-bao-mat.rss"));
                    arrayList.add(new CategoriItem("Thị trường công nghệ", "http://dantri.com.vn/suc-manh-so/thi-truong-cong-nghe.rss"));
                    arrayList.add(new CategoriItem("Thủ thuật - Mẹo vặt", "http://dantri.com.vn/suc-manh-so/thu-thuat.rss"));
                    arrayList.add(new CategoriItem("Máy tính - Mạng", "http://dantri.com.vn/suc-manh-so/vi-tinh.rss"));
                    arrayList.add(new CategoriItem("Di dộng - Viễn thông", "http://dantri.com.vn/suc-manh-so/dien-thoai.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!TextUtils.isEmpty(status)) {
            SharedPreferences pre = getSharedPreferences("my_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = pre.edit();
            editor.putString("trangthai", status);
            editor.commit();
        }
//        else {
//            SharedPreferences pre = getSharedPreferences("my_data", MODE_PRIVATE);
//            SharedPreferences.Editor editor = pre.edit();
//            editor.putString("trangthai", status);
//            editor.commit();
//        }
    }

    private void initAction() {
    }

    private void initData() {
        getListDataSpinner();
    }

    private void getListDataSpinner() {
        Intent intent = getIntent();
        String a = intent.getStringExtra("chude");
        if (!TextUtils.isEmpty(a)) {
            status = a;
            //setDataSpinner();
            arrayList = new ArrayList<CategoriItem>();
            SpinerAdapter spinerAdapter;
            switch (a) {
                case "imgSucKhoe":
                    arrayList.add(new CategoriItem("Ung thư", "http://dantri.com.vn/suc-khoe/ung-thu.rss"));
                    arrayList.add(new CategoriItem("Làm đẹp", "http://dantri.com.vn/suc-khoe/lam-dep.rss"));
                    arrayList.add(new CategoriItem("Tư vấn", "http://dantri.com.vn/suc-khoe/tu-van.rss"));
                    arrayList.add(new CategoriItem("Kiến thức giới tính", "http://dantri.com.vn/suc-khoe/kien-thuc-gioi-tinh.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
                case "imgThoiSu":
                    arrayList.add(new CategoriItem("Phóng sự- Ký sự", "http://dantri.com.vn/xa-hoi/phong-su-ky-su.rss"));
                    arrayList.add(new CategoriItem("Môi trường", "http://dantri.com.vn/xa-hoi/moi-truong.rss"));
                    arrayList.add(new CategoriItem("Chính trị", "http://dantri.com.vn/xa-hoi/chinh-tri.rss"));
                    arrayList.add(new CategoriItem("Hồ sơ", "http://dantri.com.vn/xa-hoi/ho-so.rss"));
                    arrayList.add(new CategoriItem("Giao thông", "http://dantri.com.vn/xa-hoi/giao-thong.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
                case "imgGiaiTri":
                    arrayList.add(new CategoriItem("Thời trang", "http://dantri.com.vn/giai-tri/thoi-trang.rss"));
                    arrayList.add(new CategoriItem("Xem - Ăn - Chơi", "http://dantri.com.vn/giai-tri/xem-an-choi.rss"));
                    arrayList.add(new CategoriItem("Sao Việt", "http://dantri.com.vn/giai-tri/sao-viet.rss"));
                    arrayList.add(new CategoriItem("Hollywood", "http://dantri.com.vn/giai-tri/hollywood.rss"));
                    arrayList.add(new CategoriItem("Châu Á", "http://dantri.com.vn/giai-tri/chau-a.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
                case "imgTheThao":
                    arrayList.add(new CategoriItem("Bóng đá Anh", "http://dantri.com.vn/the-thao/bong-da-anh.rss"));
                    arrayList.add(new CategoriItem("Bóng đá TBN", "http://dantri.com.vn/the-thao/bong-da-tbn.rss"));
                    arrayList.add(new CategoriItem("Bóng đá châu Âu", "http://dantri.com.vn/the-thao/bong-da-chau-au.rss"));
                    arrayList.add(new CategoriItem("Bóng đá trong nước", "http://dantri.com.vn/the-thao/bong-da-trong-nuoc.rss"));
                    arrayList.add(new CategoriItem("Thể thao quốc tế", "http://dantri.com.vn/the-thao/the-thao-quoc-te.rss"));
                    arrayList.add(new CategoriItem("Tennis", "http://dantri.com.vn/the-thao/tennis.rss"));
                    arrayList.add(new CategoriItem("Thể thao trong nước", "http://dantri.com.vn/the-thao/the-thao-trong-nuoc.rss"));
                    arrayList.add(new CategoriItem("Cờ vua", "http://dantri.com.vn/the-thao/co-vua.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
                case "imgKhoaHoc":
                    arrayList.add(new CategoriItem("Nghe nhìn", "http://dantri.com.vn/suc-manh-so/nghe-nhin.rss"));
                    arrayList.add(new CategoriItem("Điện tử tiêu dùng", "http://dantri.com.vn/suc-manh-so/dien-may.rss"));
                    arrayList.add(new CategoriItem("Phần mềm - Bảo mật", "http://dantri.com.vn/suc-manh-so/phan-mem-bao-mat.rss"));
                    arrayList.add(new CategoriItem("Thị trường công nghệ", "http://dantri.com.vn/suc-manh-so/thi-truong-cong-nghe.rss"));
                    arrayList.add(new CategoriItem("Thủ thuật - Mẹo vặt", "http://dantri.com.vn/suc-manh-so/thu-thuat.rss"));
                    arrayList.add(new CategoriItem("Máy tính - Mạng", "http://dantri.com.vn/suc-manh-so/vi-tinh.rss"));
                    arrayList.add(new CategoriItem("Di dộng - Viễn thông", "http://dantri.com.vn/suc-manh-so/dien-thoai.rss"));
                    spinerAdapter = new SpinerAdapter(DetailCategoriesActivity.this, R.layout.item_spiner, arrayList);
                    spinner.setAdapter(spinerAdapter);
                    break;
            }
        }
    }

    private void initView() {
        detailCategoriesPresenter = new DetailCategoriesPresenter(this);
        btnBack = (ImageView) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
                Intent intent = new Intent(DetailCategoriesActivity.this, CategoriesActivity.class);
                startActivity(intent);
//                finish();
                break;
        }
    }

    // Spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        detailCategoriesPresenter.onHandleGetRssNew(arrayList.get(i).getLink());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    // ListView
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (listNews.size() > 0) {
            Intent intent = new Intent(DetailCategoriesActivity.this, DetailCategoriesNewsActivity.class);
            intent.putExtra("linknewcategori", listNews.get(i).getLink());
            intent.putExtra("titlenewcategoti",listNews.get(i).getTitle());
            intent.putExtra("pubdatenewcategori",listNews.get(i).getPubdate());
            intent.putExtra("imagenewcategori",listNews.get(i).getImg());
            startActivity(intent);
            Log.d("testdata", listNews.get(i).getLink());
        }
    }

    @Override
    public void onHandleGetRssNewSuccess(List<News> list) {
        if (list.size() > 0) {
            listNews = list;
            listNewsCategoriAdapter = new ListNewsCategoriAdapter(DetailCategoriesActivity.this, R.layout.item_list_new, list);
            listView.setAdapter(listNewsCategoriAdapter);
            listNewsCategoriAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onHandleGetRssNewFailed(String erroMsg) {
        Log.d(TAG, erroMsg);
    }
}
