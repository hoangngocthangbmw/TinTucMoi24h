package com.watermelon.thanghn.bottomnavigation.model.videos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by thang on 2/9/2018.
 */

public class Video {
//    private int idVideo;
//    private String title;
//    private String link;
//    private String image;
//    private int idCategori;
//
//    public Video() {
//    }
//
//    public Video(int idVideo, String title, String link, String image, int idCategori) {
//        this.idVideo = idVideo;
//        this.title = title;
//        this.link = link;
//        this.image = image;
//        this.idCategori = idCategori;
//    }
//
//    public int getIdVideo() {
//        return idVideo;
//    }
//
//    public void setIdVideo(int idVideo) {
//        this.idVideo = idVideo;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public int getIdCategori() {
//        return idCategori;
//    }
//
//    public void setIdCategori(int idCategori) {
//        this.idCategori = idCategori;
//    }

    @SerializedName("idvideo")
    @Expose
    private String idvideo;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("idcategori")
    @Expose
    private String idcategori;

    public Video() {
    }

    public Video(String idvideo, String title, String link, String image, String idcategori) {
        this.idvideo = idvideo;
        this.title = title;
        this.link = link;
        this.image = image;
        this.idcategori = idcategori;
    }

    public String getIdvideo() {
        return idvideo;
    }

    public void setIdvideo(String idvideo) {
        this.idvideo = idvideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdcategori() {
        return idcategori;
    }

    public void setIdcategori(String idcategori) {
        this.idcategori = idcategori;
    }
}
