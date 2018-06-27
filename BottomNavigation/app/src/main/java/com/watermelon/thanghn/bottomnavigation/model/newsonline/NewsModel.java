package com.watermelon.thanghn.bottomnavigation.model.newsonline;

import android.os.AsyncTask;
import android.util.Log;


import com.watermelon.thanghn.bottomnavigation.screen.newsonline.INewsModel;

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
 * Created by thang on 2/9/2018.
 */

public class NewsModel {
    INewsModel callback;
    private List<News> listNews;

    public NewsModel(INewsModel callback) {
        this.callback = callback;
    }

    public void HandleGetRssApi(int position) {
        switch (position) {
            case 0:
                requestRssApi("http://dantri.com.vn/trangchu.rss");
                break;
            case 1:
                requestRssApi("http://dantri.com.vn/suc-khoe.rss");
                break;
            case 2:
                requestRssApi("http://dantri.com.vn/xa-hoi.rss");
                break;
            case 3:
                requestRssApi("http://dantri.com.vn/giai-tri.rss");
                break;
            case 4:
                requestRssApi("http://dantri.com.vn/giao-duc-khuyen-hoc.rss");
                break;
            case 5:
                requestRssApi("http://dantri.com.vn/the-thao.rss");
                break;
            case 6:
                requestRssApi("http://dantri.com.vn/the-gioi.rss");
                break;
            case 7:
                requestRssApi("http://dantri.com.vn/kinh-doanh.rss");
                break;
            case 8:
                requestRssApi("http://dantri.com.vn/o-to-xe-may.rss");
                break;
            case 9:
                requestRssApi("http://dantri.com.vn/suc-manh-so.rss");
                break;
            case 10:
                requestRssApi("http://dantri.com.vn/tinh-yeu-gioi-tinh.rss");
                break;
            case 11:
                requestRssApi("http://dantri.com.vn/chuyen-la.rss");
                break;
            case 12:
                requestRssApi("http://dantri.com.vn/viec-lam.rss");
                break;
            case 13:
                requestRssApi("http://dantri.com.vn/van-hoa.rss");
                break;
            case 14:
                requestRssApi("http://dantri.com.vn/phap-luat.rss");
                break;
            case 15:
                requestRssApi("http://dantri.com.vn/doi-song.rss");
                break;
            case 16:
                requestRssApi("http://dantri.com.vn/du-lich.rss");
                break;
        }
    }

    private void requestRssApi(String url) {
        new OkHttpHendler().execute(url);
//        RequestQueue queue = Volley.newRequestQueue(App.getAppContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        String title = "";
//                        String image = "";
//                        String link = "";
//                        String pubDate = "";
//                        ArrayList<String> a = new ArrayList<>();
//
//                        Document document = Jsoup.parse(response, "", Parser.xmlParser());
//                        Elements links = document.select("item > description");
//                        Elements elements = document.select("item");
//                        Elements elements2 = document.select("link");
//                        Elements elements3 = document.select("pubDate");
//                        listNews=new ArrayList<News>();
//                        for (Element e : elements) {
//                            int dem = elements.indexOf(e);
//
//                            title = elements.get(dem).getElementsByTag("title").text();
//                            link = elements2.get(dem).getElementsByTag("link").text();
//                            pubDate = elements3.get(dem).getElementsByTag("pubDate").text();
//
//                            String ownText = links.get(dem).ownText();
//                            Document frag = Jsoup.parseBodyFragment(ownText);
//                            Elements select = frag.select("img");
//                            image = select.attr("src");
//
//                            News news = new News(title, link, pubDate, image);
//                           listNews.add(news);
//                        }
//                        callback.onGetApiRssSuccess(listNews);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                callback.onGetApiRssFailed(String.valueOf(error));
//                Log.d("ErroRequest", error.toString());
//            }
//        });
//        queue.add(stringRequest);
    }

    class OkHttpHendler extends AsyncTask<String, String, String> {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

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
                    callback.onGetApiRssSuccess(listNews);
                }
            } else {
                callback.onGetApiRssFailed("Vui lòng kiểm tra kết nối !");
            }
            super.onPostExecute(s);
        }
    }

}
