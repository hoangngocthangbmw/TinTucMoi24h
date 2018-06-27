package com.watermelon.thanghn.bottomnavigation.model.newsonline;

/**
 * Created by thang on 7/25/2017.
 */


public class News {
    private String title;
    private String link;
    private String description;
    private String pubdate;
    private String img;
    public News() {
    }

    public News(String title, String link, String description, String pubdate, String img) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubdate = pubdate;
        this.img = img;
    }

    public News(String title, String link, String pubdate, String img) {
        this.title = title;
        this.link = link;
        this.pubdate = pubdate;
        this.img = img;
    }

    public News(String title) {
        this.title=title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
