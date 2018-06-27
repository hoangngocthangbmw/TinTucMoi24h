package com.watermelon.thanghn.bottomnavigation.screen.search;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;
import com.watermelon.thanghn.bottomnavigation.screen.detailcategoriesnews.DetailCategoriesNewsActivity;
import com.watermelon.thanghn.bottomnavigation.screen.detailsearch.DetailSearchActivity;
import com.watermelon.thanghn.bottomnavigation.utils.ConfigApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private List<News> listNews = new ArrayList<News>();
    private EditText mEditTextSearch;
    private Button mButtonSearch;
    private RecyclerView mRecyclerViewSearch;
    private SearchAdapter mSearchAdapter;
    private ImageView mImageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
    }

    private void initData() {
        for (String params : ConfigApi.API) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new SearchActivity.OkHttpHendler().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
            } else {
                new SearchActivity.OkHttpHendler().execute(params);
            }
        }
    }

    private void initView() {
        mEditTextSearch = (EditText) findViewById(R.id.edit_text_search);
        mButtonSearch = (Button) findViewById(R.id.button_search);
        mRecyclerViewSearch = (RecyclerView) findViewById(R.id.recyler_search);
        mImageViewBack = (ImageView) findViewById(R.id.image_back);
        mImageViewBack.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
        initReyclerView();
    }

    private void initReyclerView() {
        mRecyclerViewSearch.setHasFixedSize(true);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewSearch.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration
                = new DividerItemDecoration(SearchActivity.this, layoutManager.getOrientation());
        mRecyclerViewSearch.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.button_search:
//                searchNews(mEditTextSearch.getText().toString());
                break;
        }
    }

    public void addTextListener() {

        mEditTextSearch.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();
                final List<News> filteredList = new ArrayList<>();
                for (int i = 0; i < listNews.size(); i++) {
                    final String text = listNews.get(i).getTitle();
                    if (text.contains(query)) {
                        filteredList.add(listNews.get(i));
                    }
                }
                mRecyclerViewSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                mSearchAdapter = new SearchAdapter(filteredList, SearchActivity.this);
                mRecyclerViewSearch.setAdapter(mSearchAdapter);
                mSearchAdapter.notifyDataSetChanged();
                mSearchAdapter.setOnClickItemRecyclerView(new SearchAdapter.OnClickItemReyclerView() {
                    @Override
                    public void onItemClicked(int position, News song) {
                        openDetailCategoriesNewsActivity(song);
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void openDetailCategoriesNewsActivity(News song) {
        Intent intent = new Intent(SearchActivity.this, DetailSearchActivity.class);
        intent.putExtra("linknewcategori", song.getLink());
        intent.putExtra("pubdatenewcategori", song.getPubdate());
        intent.putExtra("imagenewcategori", song.getImg());
        intent.putExtra("titlenewcategoti", song.getTitle());
        startActivity(intent);
    }

    class OkHttpHendler extends AsyncTask<String, String, String> implements SearchAdapter.OnClickItemReyclerView {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true).build();

        @Override
        protected String doInBackground(String... strings) {
            Request.Builder builder = new Request.Builder();
            builder.url(strings[0]);
            Request request = builder.build();
            try {
                okhttp3.Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            if (!s.equals("")) {
                String title = "";
                String image = "";
                String link = "";
                String pubDate = "";
                Document document = Jsoup.parse(s, "", Parser.xmlParser());
                Elements links = document.select("item > description");
                Elements elements = document.select("item");
                Elements elements2 = document.select("item > link");
                Elements elements3 = document.select("pubDate");
                for (Element e : elements) {
                    int dem = elements.indexOf(e);
                    title = elements.get(dem).getElementsByTag("title").text();
                    link = elements2.get(dem).getElementsByTag("link").text();
                    pubDate = elements3.get(dem).getElementsByTag("pubDate").text();
                    Log.d("title", title);
                    Log.d("link", link);
                    Log.d("pubDate", pubDate);

                    String ownText = links.get(dem).ownText();
                    Document frag = Jsoup.parseBodyFragment(ownText);
                    Elements select = frag.select("img");
                    image = select.attr("src");
                    Log.d("image", image);
                    News news = new News(title, link, pubDate, image);
                    listNews.add(news);
//                    callback.onGetApiRssSuccess(listNews);
                }
                Log.d("datasearch", listNews.size() + "");
                if (listNews.size() > 0) {

                    mSearchAdapter = new SearchAdapter(listNews, SearchActivity.this);
                    mRecyclerViewSearch.setAdapter(mSearchAdapter);
                    mSearchAdapter.setOnClickItemRecyclerView(this);
                    addTextListener();

                }
            } else {
//                callback.onGetApiRssFailed("Vui lòng kiểm tra kết nối !");
            }
            super.onPostExecute(s);
        }

        @Override
        public void onItemClicked(int position, News song) {
            openDetailCategoriesNewsActivity(song);
        }
    }
}
/////*/*/*/