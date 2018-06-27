package com.watermelon.thanghn.bottomnavigation.model.videos;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.watermelon.thanghn.bottomnavigation.App;
import com.watermelon.thanghn.bottomnavigation.screen.videos.IListVideoModel;
import com.watermelon.thanghn.bottomnavigation.screen.videos.IListVideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thang on 2/9/2018.
 */

public class ListVideosModel {
    IListVideoModel callback;
    private List<Video> list;

    public ListVideosModel(IListVideoModel callback) {
        this.callback = callback;
    }

    public void HandleGetListVideo(int position) {
        switch (position) {
            case 0:
               requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=4");
                break;
            case 1:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=1");
                break;
            case 2:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=2");
                break;
            case 3:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=3");
                break;
            case 4:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=5");
                break;
            case 5:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=6");
                break;
            case 6:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=7");
                break;
            case 7:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=8");
                break;
            case 8:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=9");
                break;
            case 9:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=10");
                break;
            case 10:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=10");
                break;
            case 11:
                requestApiServer("http://172.20.10.2:8080/tintucmoi24h/index.php?id=11");
                break;
        }
    }

    private void requestApiServer(String url) {
        list=new ArrayList<Video>();
        RequestQueue queue = Volley.newRequestQueue(App.getAppContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject object=response.getJSONObject(i);
                        list.add(new Video(
                                object.getString("idvideo"),
                                object.getString("title"),
                                object.getString("link"),
                                object.getString("image"),
                                object.getString("idcategori"))
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                callback.onGetListVideoSuccess(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onGetListVideoFailed(error.toString());
            }
        });
        queue.add(jsonArrayRequest);

    }
}
