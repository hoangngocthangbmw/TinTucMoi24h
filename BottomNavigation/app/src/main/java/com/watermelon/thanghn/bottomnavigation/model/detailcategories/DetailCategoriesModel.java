package com.watermelon.thanghn.bottomnavigation.model.detailcategories;

import android.os.AsyncTask;
import android.util.Log;

import com.watermelon.thanghn.bottomnavigation.model.newsonline.News;
import com.watermelon.thanghn.bottomnavigation.model.newsonline.NewsModel;
import com.watermelon.thanghn.bottomnavigation.screen.detailcategories.IDetailCategoriesModel;

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

/**
 * Created by thang on 3/12/2018.
 */

public class DetailCategoriesModel {
    IDetailCategoriesModel callback;
    private List<News> listNews;

    public DetailCategoriesModel(IDetailCategoriesModel callback) {
        this.callback = callback;
    }

    public void HanldeGetRssNew(String link) {
        requestRssApi(link);
    }

    private void requestRssApi(String link) {
        new OkHttpHendler().execute(link);
    }

    class OkHttpHendler extends AsyncTask<String, String, String> {
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
                callback.onHandleGetRssNewFailed(e.toString());
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
                listNews = new ArrayList<News>();
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
                }
                callback.onHandleGetRssNewSuccess(listNews);
            } else {
                callback.onHandleGetRssNewFailed("Vui lòng kiểm tra kết nối !");
            }
            super.onPostExecute(s);
        }
    }

}
